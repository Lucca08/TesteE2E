package com.example.RestAssuredMatheus.E2E;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.Description;

import com.example.RestAssuredMatheus.Util.BaseTeste;
import com.example.RestAssuredMatheus.model.Locatario;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteE2EParaLocatorio extends BaseTeste {

    private static String locatarioId;

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
                .statusCode(201)
                .extract().response();

        locatarioId = createResponse.jsonPath().getString("id");
        Assertions.assertNotNull(locatarioId, "O ID do locatário criado não pode ser nulo");
    }

    @Test
    @Description("Deve buscar um locatário e retornar 200")
    @Order(2)
    public void deveRetornar200AoBuscarUmLocatario() {
        given()
                .pathParam("id", locatarioId)
                .when()
                .get("/v1/locatario/{id}")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Matheus"))
                .body("genero", equalTo("Masculino"))
                .body("telefone", equalTo("1122223333"))
                .body("email", equalTo("lucca@gmail.com"))
                .body("dataNascimento", equalTo("10/10/1999"))
                .body("cpf", equalTo("50068883005"));
    }

    @Test
    @Description("Deve atualizar um locatário e retornar 200")
    @Order(3)
    public void deveRetornar200AoAtualizarUmLocatario() {
        Locatario locatarioAtualizado = Locatario.builder()
                .nome("Matheus Silva")
                .genero("Masculino")
                .telefone("1199998888")
                .email("matheus.silva@gmail.com")
                .dataNascimento(LocalDate.of(1999, 10, 10))
                .cpf("50068883005")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(locatarioAtualizado)
                .pathParam("id", locatarioId)
                .when()
                .put("/v1/locatario/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    @Description("Deve deletar um locatário e retornar 200")
    @Order(4)
    public void deveRetornar200AoDeletarUmLocatario() {
        given()
                .pathParam("id", locatarioId)
                .when()
                .delete("/v1/locatario/{id}")
                .then()
                .statusCode(200);
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
}
