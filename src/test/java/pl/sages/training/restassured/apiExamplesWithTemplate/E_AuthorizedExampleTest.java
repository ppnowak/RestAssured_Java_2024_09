package pl.sages.training.restassured.apiExamplesWithTemplate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;
import pl.sages.training.restassured.framework.commons.AuthorizedApiTestTemplate;

import static io.restassured.RestAssured.given;

public class E_AuthorizedExampleTest extends AuthorizedApiTestTemplate {

    @Test
    public void twoStepAuthorization() {
        given()
                // .auth().oauth2(token) - from @BeforeEach
                .when().get("/secret")
                .then()
                .statusCode(200)
                .body("secretData", Matchers.equalTo("This is the secret data!"));
    }

    @Test
    public void mirror() {
        given()
                .when().get("/mirror")
                .then().statusCode(200);
    }


}
