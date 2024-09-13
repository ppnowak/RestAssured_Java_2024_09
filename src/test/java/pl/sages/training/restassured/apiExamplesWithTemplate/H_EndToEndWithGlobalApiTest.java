package pl.sages.training.restassured.apiExamplesWithTemplate;

import com.github.javafaker.Faker;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sages.training.restassured.framework.api.API;
import pl.sages.training.restassured.framework.commons.ApiErrors;
import pl.sages.training.restassured.framework.models.users.AddUserRequest;
import pl.sages.training.restassured.framework.models.users.UserAddressData;
import pl.sages.training.restassured.framework.models.users.UserDetailsResponse;

public class H_EndToEndWithGlobalApiTest {

    @Test
    public void succes() {

        // Prepare test data
        UserAddressData address = new UserAddressData("Street", "City", "ZIP");
        AddUserRequest expectedUser = new AddUserRequest("Piotr", "Nowak", address);

        // POST /users - add new user
        Integer userId = API.users.addUser(expectedUser);

        // GET /user/:id - check if user data is correct
        UserDetailsResponse actualUser = API.users.getUser(userId);

        // Check if data is OK
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(actualUser.getId(), Matchers.equalTo(userId)),
                () -> MatcherAssert.assertThat(actualUser.getFirstName(), Matchers.equalTo(expectedUser.getFirstName()))
        );

    }

    @ParameterizedTest
    @ValueSource(strings = {"999", "xyz"})
    public void error_USER_NOT_FOUND(String userId) {
//        API.users.getUser(9999);
//        API.users.getUser("xyz");
        ApiErrors.USER_NOT_FOUND.verify(
                API.users.getUserRaw(userId)
        );
    }

    @Test
    public void loggedInTest() {
        Assertions.assertEquals("This is the secret data!", API.admin.getSecret());
    }

    @Test
    public void succesWithJavaFaker() {

        Faker faker = Faker.instance();

        // Prepare test data
        UserAddressData address = new UserAddressData(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().zipCode()
        );

        AddUserRequest expectedUser = new AddUserRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                address);

        // POST /users - add new user
        Integer userId = API.users.addUser(expectedUser);

        // GET /user/:id - check if user data is correct
        UserDetailsResponse actualUser = API.users.getUser(userId);

        // Check if data is OK
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(actualUser.getId(), Matchers.equalTo(userId)),
                () -> MatcherAssert.assertThat(actualUser.getFirstName(), Matchers.equalTo(expectedUser.getFirstName()))
        );

    }

}
