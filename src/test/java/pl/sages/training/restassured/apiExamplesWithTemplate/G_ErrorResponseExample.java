package pl.sages.training.restassured.apiExamplesWithTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiErrors;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;
import pl.sages.training.restassured.framework.models.ErrorResponse;

import static io.restassured.RestAssured.given;

public class G_ErrorResponseExample extends ApiTestTemplate {

    @Test
    public void test() {
        ErrorResponse error = given()
                .pathParam("userId", 999)
                .when().get("/user/{userId}")
                .then()
                .statusCode(404)
                .extract()
                    //.jsonPath().getObject("", ErrorResponse.class);
                    .as(ErrorResponse.class);
        Assertions.assertEquals("User not found.", error.getError());
        Assertions.assertEquals(404, error.getStatus());
//        assert error.getError().equals("User not found.") : "Błąd: Pole 'error' nie ma wartości 'User not found'";
//        assert error.getStatus() == 404 : "Błąd: Pole 'status' nie ma wartości 404";
    }

    @Test
    public void commonError() {
        ApiErrors.USER_NOT_FOUND.verify(
                given()
                .pathParam("userId", 999)
                .when().get("/user/{userId}")
                .then()
        );
    }



}

