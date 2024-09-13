package pl.sages.training.restassured.apiExamplesWithTemplate;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;
import pl.sages.training.restassured.framework.models.users.UserAddressData;
import pl.sages.training.restassured.framework.models.users.UserDetailsResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class F_ExtractResponseToCustomObject extends ApiTestTemplate {

    /*
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
    public void extractWholeResponse() {
        UserDetailsResponse user = given()
                .pathParam("userId", 1)
                .when().get("/user/{userId}")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("", UserDetailsResponse.class);

        System.out.println(user);

//        Assertions.assertEquals("Piotrr", user.getFirstName());
//        Assertions.assertEquals(11, user.getId());
//        Assertions.assertEquals("Warszawaa", user.getAddress().getCity());

        // Soft Assertion
        Assertions.assertAll(
                // Hamcrest Matcher
                () -> MatcherAssert.assertThat(user.getLastName(), Matchers.equalTo("Nowak")),
                // JUnit 5 default Assertion
                () -> Assertions.assertEquals("Piotr", user.getFirstName()),
                () -> Assertions.assertEquals(1, user.getId()),
                () -> Assertions.assertEquals("Warszawa", user.getAddress().getCity())
        );

    }

    @Test
    public void extractOnlyAddress() {
        Integer userId = 1;
        UserAddressData address = given()
                .pathParam("userId", userId)
                .when().get("/user/{userId}")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("address", UserAddressData.class);
        // Soft Assertion
        Assertions.assertAll(
                () -> Assertions.assertEquals("Główna 8", address.getStreet()),
                () -> Assertions.assertEquals("Warszawa", address.getCity()),
                () -> Assertions.assertEquals("00-300", address.getZipCode())
        );

    }

    public static class DatabaseUtils {

        public static UserDetailsResponse getRandomUser() {
            // Logic to get random user address from database;
            return new UserDetailsResponse(); // TODO: implement
        }

        public static UserAddressData getUserAddress(Integer userId) {
            // Logic to get user address from database and move it into UserAddressData object;
            return new UserAddressData(); // TODO: implement
        }
    }

    @Test
    public void extractOnlyAddressAndValidateWithDB() {
        Integer userId = 1;
        UserAddressData expectedAddress = DatabaseUtils.getUserAddress(userId);
        UserAddressData address = given()
                .pathParam("userId", userId)
                .when().get("/user/{userId}")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("address", UserAddressData.class);
        // Soft Assertion
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedAddress.getStreet(), address.getStreet()),
                () -> Assertions.assertEquals(expectedAddress.getCity(), address.getCity()),
                () -> Assertions.assertEquals(expectedAddress.getZipCode(), address.getZipCode())
        );

    }

    @Test
    public void extractOnlyAddressAndValidateWithRandomUser() {
        UserDetailsResponse user = DatabaseUtils.getRandomUser();
        UserAddressData address = given()
                .pathParam("userId", user.getId())
                .when().get("/user/{userId}")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("address", UserAddressData.class);
        // Soft Assertion
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getAddress().getStreet(), address.getStreet()),
                () -> Assertions.assertEquals(user.getAddress().getCity(), address.getCity()),
                () -> Assertions.assertEquals(user.getAddress().getZipCode(), address.getZipCode())
        );
    }

    @Test
    public void extractListOfObjects() {
        List<UserDetailsResponse> users = given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("", UserDetailsResponse.class);
        // Soft Assertion
        Assertions.assertEquals(20, users.size());
        for (UserDetailsResponse user : users) {
            System.out.println("Checking user = " + user);
            Assertions.assertAll(
                    () -> MatcherAssert.assertThat(user.getId(), Matchers.greaterThan(0)),
                    () -> MatcherAssert.assertThat(user.getFirstName(), Matchers.not(Matchers.emptyOrNullString())),
                    () -> MatcherAssert.assertThat(user.getLastName(), Matchers.not(Matchers.emptyOrNullString())),
                    () -> MatcherAssert.assertThat(user.getAddress(), Matchers.nullValue())
            );
        }
    }

    /**
     * Zadanie #1
     *
     * Wyślij request GET /user/999
     * Sprawdź w odpowiedzi:
     * czy zwrócony został kod HTTP 404
     * Wykonaj serializację ciała odpowiedzi na obiekt typu ErrorResponse
     * Wykonaj asercje sprawdzające, czy dla obiektu ErrorResponse:
     * pole error ma wartość User not found.
     * pole status ma wartość 404
     */

}
