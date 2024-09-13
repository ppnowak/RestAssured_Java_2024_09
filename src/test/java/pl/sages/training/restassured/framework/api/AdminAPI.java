package pl.sages.training.restassured.framework.api;

import org.hamcrest.Matchers;
import pl.sages.training.restassured.framework.commons.Config;
import pl.sages.training.restassured.framework.commons.RestAssuredWrapper;

import static io.restassured.RestAssured.given;

public class AdminAPI {

    // We are skipping the "Raw" method creation, just to simplify the example
    public String login(String login, String password) {
        return RestAssuredWrapper.given()
                .auth().preemptive().basic(login, password)
                .when().post("/login")
                .then().statusCode(200)
                .body("token", Matchers.any(String.class))
                .extract().body().jsonPath().get("token");
    }

    public String login() {
        return login(Config.getAdminLogin(), Config.getAdminPassword());
    }

    public String getSecret(String token) {
        return RestAssuredWrapper.given()
                .auth().oauth2(token)
                .when().get("/secret")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("secretData");
    }

    public String getSecret() {
        String token = login(Config.getAdminLogin(), Config.getAdminPassword());
        return getSecret(token);
    }

}
