package com.example.RestAssuredMatheus.E2E;

import com.example.RestAssuredMatheus.Util.BaseTeste;
import com.example.RestAssuredMatheus.model.Autor;
import com.example.RestAssuredMatheus.model.CadastroAluguel;
import com.example.RestAssuredMatheus.model.Locatario;
import com.example.RestAssuredMatheus.model.Livro;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.Description;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteE2EParaAlugar extends BaseTeste {

    private static Long aluguelId;
    private static Long locatarioId;
    private static Long livroId;
    private static Long autorId;

    @Test
    @Description("Deve criar um locatário e retornar 201")
    @Order(1)
    public void deveRetornar201AoCriarUmLocatario() {
        Locatario locatario = criarLocatario();

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(locatario)
                .when()
                .post("/v1/locatario")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();

        locatarioId = createResponse.jsonPath().getLong("id");

        System.out.println("Locatario ID criado: " + locatarioId);
        Assertions.assertNotNull(locatarioId, "O ID do locatário criado não pode ser nulo");
    }

    @Test
    @Order(2)
    public void deveRetornar201AoCriarUmAutor() {
        Autor autor = criarAutor();

        Response createResponse = given()
                .contentType("application/json")
                .body(autor)
                .when()
                .post("/v1/autor")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();

        autorId = createResponse.jsonPath().getLong("id");

        System.out.println("Autor ID criado: " + autorId);
        Assertions.assertNotNull(autorId, "O ID do autor criado não pode ser nulo");
    }

    @Test
    @Order(3)
    public void deveRetornar201AoCriarUmLivro() {
        Assertions.assertNotNull(autorId, "O ID do autor não pode ser nulo");

        Livro livro = criarLivro();

        Response createResponse = given()
                .contentType("application/json")
                .body(livro)
                .when()
                .post("/v1/livro")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();

        livroId = createResponse.jsonPath().getLong("id");

        System.out.println("Livro ID criado: " + livroId);
        Assertions.assertNotNull(livroId, "O ID do livro criado não pode ser nulo");
    }

    @Test
    @Order(4)
    public void deveRetornar201AoCriarUmAluguel() {
     
        CadastroAluguel Aluguel = criarAluguel();

        Response createResponse = given()
                .contentType("application/json")
                .body(Aluguel)
                .when()
                .post("/v1/aluguel")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .extract().response();
        
        aluguelId = createResponse.jsonPath().getLong("id");

        System.out.println("Resposta da criação do aluguel: " + createResponse.asString());
        System.out.println("Aluguel ID criado: " + aluguelId);
        Assertions.assertNotNull(aluguelId, "O ID do aluguel criado não pode ser nulo");
    }


    @Test
    @Order(5)
    public void deveRetornar200AoBuscarUmAluguelPeloId() {
        Assertions.assertNotNull(aluguelId, "O ID do aluguel não pode ser nulo");



        given()
                .pathParam("id", aluguelId)
                .when()
                .get("/v1/aluguel/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("locatario.nome", equalTo("Matheus"))
                .body("livros.size()", greaterThan(0));
    }


    @Test
    @Order(6)
    public void deveRetornar200AoListarAlugueis() {
        given()
                
                .when()
                .get("/v1/aluguel?nome=Matheus")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(7)
    public void deveRetornar200AoDeletarUmAluguel() {
        Assertions.assertNotNull(aluguelId, "O ID do aluguel não pode ser nulo");

        given()
                .pathParam("id", aluguelId)
                .when()
                .delete("/v1/aluguel/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK);

        given()
                .pathParam("id", aluguelId)
                .when()
                .get("/v1/aluguel/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private CadastroAluguel criarAluguel() {
        Assertions.assertNotNull(locatarioId, "O ID do locatário não pode ser nulo");
        Assertions.assertNotNull(livroId, "O ID do livro não pode ser nulo");

        return CadastroAluguel.builder()
                .idLocatario(1L)
                .idLivros(List.of(1L))
                .build();
    }

    private Locatario criarLocatario() {
        return Locatario.builder()
                .nome("Matheus")
                .genero("Masculino")
                .telefone("1122223333")
                .email("lucca@gmail.com")
                .dataNascimento(LocalDate.of(1999, 10, 10))
                .cpf("50068883005")
                .build();
    }

    private Autor criarAutor() {
        return Autor.builder()
                .nome("Nome do Autor")
                .genero("Masculino")
                .anoNascimento(Year.of(2010))
                .cpf("38016197027")
                .build();
    }

    private Livro criarLivro() {
        Assertions.assertNotNull(autorId, "O ID do autor não pode ser nulo");

        return Livro.builder()
                .nome("Nome do Livro")
                .isbn("9788525044297")
                .dataPublicacao(LocalDate.of(2020, 6, 18))
                .alugado(false)
                .autoresId(List.of(autorId))
                .build();
    }
}
