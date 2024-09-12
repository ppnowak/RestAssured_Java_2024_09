package pl.sages.training.restassured.apiExamples;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GivenWhenThenExample {

    @Test
    public void test() {

        var json = """
                {
                    "firstName": "Piotr",
                    "lastName": "Nowak"
                }
                """;

        given()
                .log().all()
                .baseUri("https://api.breathtaking.website")
                .queryParam("login", "user_login")
                .queryParams("password", "admin", "source", "api")
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(json)

                .when().get("/mirror")

                .then()
                .log().all()
                .statusCode(200);


    }


}
