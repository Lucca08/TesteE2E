package com.example.RestAssuredMatheus.E2E;

import com.example.RestAssuredMatheus.Util.BaseTeste;
import com.example.RestAssuredMatheus.model.Autor;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.time.Year;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteE2EParaAutor extends BaseTeste {

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
                .statusCode(HttpStatus.SC_CREATED)
                .extract().jsonPath().getLong("id");

        Assertions.assertNotNull(autorId, "O ID do autor criado não pode ser nulo");
    }

    @Test
    @Order(2)
    public void deveRetornar200AoBuscarUmAutorPeloNome() {
        Assertions.assertNotNull(autorId, "O ID do autor não pode ser nulo");

        given()
                .queryParam("nome", "Nome do Autor")
                .when()
                .get("/v1/autor")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", equalTo("Nome do Autor"))
                .body("cpf", equalTo("56321500038"));       
            
   }


    @Test
    @Order(3)
    public void deveRetornar200AoDeletarUmAutor() {
        Assertions.assertNotNull(autorId, "O ID do autor não pode ser nulo");

        given()
                .pathParam("id", autorId)
                .when()
                .delete("/v1/autor/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    private Autor criarAutor() {
        return Autor.builder()
                .nome("Nome do Autor")
                .genero("Masculino")
                .anoNascimento(Year.of(2010))
                .cpf("56321500038")
                .build();
    }
}
