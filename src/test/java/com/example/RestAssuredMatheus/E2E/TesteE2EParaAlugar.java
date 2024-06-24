    package com.example.RestAssuredMatheus.E2E;

    import com.example.RestAssuredMatheus.Util.BaseTeste;
    import com.example.RestAssuredMatheus.model.Aluguel;
    import com.example.RestAssuredMatheus.model.Autor;
    import com.example.RestAssuredMatheus.model.Locatario;
    import com.example.RestAssuredMatheus.model.Livro;
    import io.restassured.http.ContentType;
    import io.restassured.response.Response;
    import org.junit.jupiter.api.*;
    import org.springframework.context.annotation.Description;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
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
                    .statusCode(201)
                    .extract().response();

            locatarioId = createResponse.jsonPath().getLong("id");
            
            Assertions.assertNotNull(locatarioId, "O ID do locatário criado não pode ser nulo");
        }

        @Test
        @Order(2)
        public void deveRetornar201AoCriarUmAutor() {
            Autor autor = criarAutor();

            Response createResponse= given()
                    .contentType("application/json")
                    .body(autor)
                    .when()
                    .post("/v1/autor")
                    .then()
                    .statusCode(201)
                    .extract().response();

            autorId = createResponse.jsonPath().getLong("id");

            Assertions.assertNotNull(autorId, "O ID do autor criado não pode ser nulo");
        }

        // impedimento para poder testar pelo banco de dados
        // trocar de banco 
        @Test
        @Order(3)
        public void deveRetornar201AoCriarUmLivro() {
            Assertions.assertNotNull(autorId, "O ID do autor não pode ser nulo");

            Livro livro = criarLivro();

            livroId = given()
                    .contentType("application/json")
                    .body(livro)
                    .when()
                    .post("/v1/livro")
                    .then()
                    .statusCode(201)
                    .extract().jsonPath().getLong("id");

            Assertions.assertNotNull(livroId, "O ID do livro criado não pode ser nulo");
        }

        @Test
        @Order(4)
        public void deveRetornar201AoCriarUmAluguel() {

            System.out.println("locatarioId: " + locatarioId);
            System.out.println("livroId: " + livroId);
            Assertions.assertNotNull(locatarioId, "O ID do locatário não pode ser nulo");
            Assertions.assertNotNull(livroId, "O ID do livro não pode ser nulo");

            Aluguel aluguel = criarAluguel();

            aluguelId = given()
                    .contentType("application/json")
                    .body(aluguel)
                    .when()
                    .post("/v1/aluguel")
                    .then()
                    .statusCode(201)
                    .extract().jsonPath().getLong("id");

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
                    .statusCode(200)
                    .body("id", equalTo(aluguelId.intValue()))
                    .body("locatario.id", equalTo(locatarioId.intValue()))
                    .body("livros.size()", greaterThan(0));
        }

        @Test
        @Order(6)
        public void deveRetornar200AoListarAlugueis() {
            given()
                    .when()
                    .get("/v1/aluguel")
                    .then()
                    .statusCode(200)
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
                    .statusCode(200);

            given()
                    .pathParam("id", aluguelId)
                    .when()
                    .get("/v1/aluguel/{id}")
                    .then()
                    .statusCode(404);
        }

        private Aluguel criarAluguel() {
    

            //sql injection

            // Constrói o objeto Aluguel com base nos IDs fornecidos
            return Aluguel.builder()
                    .locatario(Locatario.builder().id(locatarioId).build())
                    .livros(List.of(Livro.builder().id(livroId).build()))
                    .dataRetirada(LocalDateTime.now())
                    .dataDevolucao(LocalDateTime.now().plusDays(2))
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
                    .cpf("53494105049")
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
