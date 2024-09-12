package pl.sages.training.restassured.apiExamples;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class GetUserNotFoundTest {

    @Test
    public void test() {
//        Zadanie #2
//        Wyślij request GET https://api.breathtaking.website/user/999
//        Sprawdź w odpowiedzi:
//        czy zwrócony został kod HTTP 404

        RestAssured.given()
                .log().all()

                .when()
                .get("https://api.breathtaking.website/user/999")

                .then()
                .log().all()
                .statusCode(404);
        ;

    }

    @Test
    public void test2() {
        RestAssured.given()
                .log().all()
                .baseUri("https://api.breathtaking.website")
                .pathParam("userId", 999)

                .when()
                .get("/user/{userId}")

                .then()
                .log().all()
                .statusCode(404)
                .body("", Matchers.hasKey("status"))
                .body("", Matchers.hasKey("error"))
                .body("error", Matchers.equalTo("User not found."))
                .body("status", Matchers.equalTo(404))
        ;

//        {
//            "status": 404,
//            "error": "User not found."
//        }

    }

}
