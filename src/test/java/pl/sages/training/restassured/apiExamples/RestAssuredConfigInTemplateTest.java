package pl.sages.training.restassured.apiExamples;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;

public class RestAssuredConfigInTemplateTest extends ApiTestTemplate {

    @ParameterizedTest
    @ValueSource(strings = {"Piotr", "Pawel"})
    public void test(String firstName) {
        Integer defaultServerRateLimit = 60;
        RestAssured.given()
                .queryParam("firstName", firstName)
                .header("X-Random-Number", 546455)
                .contentType(ContentType.JSON)

                .when()
                .get("/mirror")

                .then().assertThat()
                .statusCode(200)
                .header("X-RateLimit-Limit", defaultServerRateLimit.toString())
                .body("queryParams.firstName", Matchers.equalTo(firstName))

        ;
    }

}
