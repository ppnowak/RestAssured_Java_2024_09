package pl.sages.training.restassured.apiExamplesWithTemplate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;

import static io.restassured.RestAssured.given;

public class D_LoginExampleTest extends ApiTestTemplate {

//    POST /login
//    Simulates login action, returns JWT key as the response.
//
//    Implemented authorization types:
//
//    Basic HTTP login
//    x-username & x-password headers
//    Query parameters username & password

    @Test
    public void basicLoginPreemptive() {
        // preemptive -> we don't see this box with login/password when we get to the endpoint
        // default -> we see this box with login/password when we get to the endpoint
        given()
                .auth().preemptive().basic("admin", "admin1")
                .when().post("/login")

                .then()
                .statusCode(200)
                .body("token", Matchers.any(String.class))
        ;
    }

    @Test
    public void basicLoginDefault() {
        given()
                .auth().basic("admin", "admin1")
                .when().get("/secret")

                .then()
                .statusCode(200)
                .body("secretData", Matchers.equalTo("This is the secret data!"))
        ;
    }

    @Test
    public void loginWithCustomHeader() {
        given()
                .header("x-username", "admin")
                .header("x-password", "admin1")
                .when().post("/login")

                .then()
                .statusCode(200)
                .body("token", Matchers.any(String.class))
        ;
    }

    @Test
    public void twoStepAuthorization() {
        // Step 1 - send POST /login and obtain token
        String token = given()
                .auth().preemptive().basic("admin", "admin1")
                .when().post("/login")
                .then().statusCode(200)
                .body("token", Matchers.any(String.class))
        // now we have to extract token as a string
                .extract().body().jsonPath().get("token");
        ;

        System.out.println("Token from POST /login:\n" + token);

        setupRestAssured();
        // Step 2 - send GET /secret with the token and see the secret message
        given()
                .auth().oauth2(token)
                .when().get("/secret")
                .then().statusCode(200);

    }


}
