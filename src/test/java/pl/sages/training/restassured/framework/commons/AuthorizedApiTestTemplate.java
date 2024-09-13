package pl.sages.training.restassured.framework.commons;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public class AuthorizedApiTestTemplate extends ApiTestTemplate {

    @BeforeEach
    @Override // Java annotation to show that we override method from extended class
    public void setupRestAssured() {
        super.setupRestAssured();
        // Get Token from API
        String token = getToken();
        // Set Request Specification with the token
        RestAssured.requestSpecification = RestAssured.given().auth().oauth2(token);
    }

    private String generateAccessToken() {
        // Get token from POST /login
        String token = given()
                .auth().preemptive().basic("admin", "admin1")
                .when().post("/login")
                .then().statusCode(200)
                .body("token", Matchers.any(String.class))
                // now we have to extract token as a string
                .extract().body().jsonPath().get("token");
        // Clean up RestAssured
        super.setupRestAssured();
        // Return Token
        return token;
    }

    private static String TOKEN;

    public String getToken() {
        if (TOKEN == null) {
            TOKEN = generateAccessToken();
        }
        return TOKEN;
    }

}
