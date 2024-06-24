package com.example.RestAssuredMatheus.E2E;

import com.example.RestAssuredMatheus.Util.BaseTeste;
import com.example.RestAssuredMatheus.model.Livro;
import com.example.RestAssuredMatheus.model.Autor;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteE2EParaLivro extends BaseTeste {

    private static Long livroId;
    private static Long autorId;
   

    
    @Test
    @Order(1)
    public void deveRetornar201AoCriarUmAutor() {
        Autor autor = criarAutor();

        autorId = given()
                .contentType("application/json")
                .body(autor)
                .when()
                .post("/v1/autor")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        Assertions.assertNotNull(autorId, "O ID do autor criado não pode ser nulo");
    }

    @Test
    @Order(2)
    public void deveRetornar201AoCriarUmLivro() {
        Livro cadastroLivro = criarCadastroLivro();



        livroId = given()
                .contentType("application/json")
                .body(cadastroLivro)
                .when()
                .post("/v1/livro")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        Assertions.assertNotNull(livroId, "O ID do livro criado não pode ser nulo");
    }

    @Test
    @Order(3)
    public void deveRetornar200AoBuscarUmLivroPeloId() {
        Assertions.assertNotNull(livroId, "O ID do livro não pode ser nulo");

        given()
                .pathParam("id", livroId)
                .when()
                .get("/v1/livro/{id}")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Nome do Livro"))
                .body("isbn", equalTo("9788525044297"))
                .body("dataPublicacao", equalTo("18/06/2020"))
                .body("alugado", equalTo(false))
                .body("autores.size()", equalTo(1))
                .body("autores[0].nome", equalTo("Lucca"));
    }

    @Test
    @Order(4)
    public void deveRetornar200AoListarLivros() {
        given()
                .when()
                .get("/v1/livro")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(5)
    public void deveRetornar200AoListarLivrosAlugados() {
        given()
                .when()
                .get("/v1/livro/alugado")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(6)
    public void deveRetornar200AoDeletarUmLivro() {
        Assertions.assertNotNull(livroId, "O ID do livro não pode ser nulo");

        given()
                .pathParam("id", livroId)
                .when()
                .delete("/v1/livro/{id}")
                .then()
                .statusCode(200);

        given()
                .pathParam("id", livroId)
                .when()
                .get("/v1/livro/{id}")
                .then()
                .statusCode(404);
    }

    private static Livro criarCadastroLivro() {
        return Livro.builder()
                .nome("Nome do Livro")
                .isbn("9788525044297")
                .dataPublicacao(LocalDate.of(2020, 6, 18))
                .alugado(false)
                .autoresId(List.of(1L)) 
                .build();
    }

    private Autor criarAutor() {
        return Autor.builder()
                .id(1L)
                .nome("Lucca")
                .genero("Masculino")
                .anoNascimento(Year.of(2010))
                .cpf("53494105049")
                .build();
    }

}
