package pl.sages.training.restassured.apiExamplesWithTemplate;

import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;

import java.util.Arrays;
import java.util.List;

public class B_SearchUsersTest extends ApiTestTemplate {

    // GET https://api.breathtaking.website/users?id=1
    /*
        [
          {
            "id": 1,
            "firstName": "Piotr",
            "lastName": "Nowak",
            "registrationDate": "2024-01-05T03:40:33.486Z",
            "status": "Active"
          }
        ]
     */

    @Test
    public void filterById() {
        RestAssured.given()
                .queryParam("id", 1)

                .when().get("/users")

                .then().assertThat()
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].firstName", Matchers.equalTo("Piotr"))
        ;
    }

    @Test
    public void noFilter() {

//        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
//        MatcherAssert.assertThat(numbers, Matchers.hasItem(5));

        RestAssured.given()
                .when().get("/users")
                .then()
                .statusCode(200)
                // fail positive - we match that the ID=5 exists anywhere
                // + firstName=Piotr exists anywhere
                .body("id", Matchers.hasItem(5))
                .body("firstName", Matchers.hasItem("Piotr"))

                // we check that the response has 20 objects
                .body("size()", Matchers.equalTo(20))

                // we check that 20 objects has property ID in the array
                .body("id", Matchers.hasSize(20))


        ;
    }

}
