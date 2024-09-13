package pl.sages.training.restassured.apiExamples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RestAssuredConfigInBeforeEachTest {

    @BeforeEach
    public void setupRestAssured() {
        RestAssured.reset();
        // DONE: move baseUri outside, so we don't have to remember about filling this
        RestAssured.baseURI = "https://api.breathtaking.website";
        RestAssured.filters(
        // DONE: move request logs outside, so we can decide whether to show logs or not
                new RequestLoggingFilter(),
        // DONE: move response logs outside, so we can decide whether to show logs or not
                new ResponseLoggingFilter()
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Piotr", "Pawel"})
    public void test(String firstName) {
        Integer defaultServerRateLimit = 60;
        RestAssured.given()
//                .baseUri("https://api.breathtaking.website")
                .queryParam("firstName", firstName)
                .header("X-Random-Number", 546455)
                .contentType(ContentType.JSON)
//                .log().all()

                .when()
                .get("/mirror")

                .then().assertThat()
                .statusCode(200)
                .header("X-RateLimit-Limit", defaultServerRateLimit.toString())
                .body("queryParams.firstName", Matchers.equalTo(firstName))
//                .log().all()

        ;
    }

}
