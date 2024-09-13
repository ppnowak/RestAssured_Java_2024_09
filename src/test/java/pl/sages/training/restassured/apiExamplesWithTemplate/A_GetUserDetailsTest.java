package pl.sages.training.restassured.apiExamplesWithTemplate;

import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;

import static io.restassured.RestAssured.given;

public class A_GetUserDetailsTest extends ApiTestTemplate {

    /*
    GET /user/:userId
    Shows user details.
        {
            "id": 1,
            "firstName": "Piotr",
            "lastName": "Nowak",
            "registrationDate": "2024-01-05T03:40:33.486Z",
            "status": "Active",
            "address": {
                "street": "Główna 8",
                "city": "Warszawa",
                "zipCode": "00-300"
            }
        }
     */

    @Test
    public void success() {

//        String someText = "abcd \\ ghi";

        given()
                .pathParam("userId", 1)
                .when().get("/user/{userId}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("firstName", Matchers.equalTo("Piotr"))
                .body("registrationDate", Matchers.matchesPattern("2024-01-05T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$"))
//                .body("street", Matchers.equalTo("Główna 8"))
                .body("address.street", Matchers.equalTo("Główna 8"))
                .body("address.zipCode", Matchers.equalTo("00-300"))
                // https://regex101.com/
                .body("address.zipCode", Matchers.matchesPattern("^\\d{2}-\\d{3}$"))

        ;

//        MatcherAssert.assertThat("Actual First Name", Matchers.equalTo("Expected First Name"));
//        Assertions.assertEquals("Expected First Name", "Actual First Name");
//        MatcherAssert.assertThat("Actual First Name", Matchers.allOf(
//                Matchers.equalTo("Expected First Name"),
//                Matchers.hasLength(10),
//                Matchers.
//        ));

    }

    @Test
    public void notReallyATest() {
        String json = """
               {
                   "method": "GET"
               }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/mirror")
                .then()
                .statusCode(200)
                .body("method", Matchers.equalTo("POST"))
                .body("payload.method", Matchers.equalTo("GET"))
        ;
    }

}
