package pl.sages.training.restassured.framework.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pl.sages.training.restassured.framework.commons.RestAssuredWrapper;
import pl.sages.training.restassured.framework.models.users.AddUserRequest;
import pl.sages.training.restassured.framework.models.users.UserDetailsResponse;

public class UsersAPI {

    public Response addUserRaw(Object user) {
        return RestAssuredWrapper.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/user");
    }

    public Integer addUser(AddUserRequest user) {
        return addUserRaw(user).then().statusCode(201).extract().jsonPath().getInt("userId");
    }

    public ValidatableResponse getUserRaw(Object userId) {
        return RestAssuredWrapper
                .given()
                .pathParam("userId", userId)
                .when().get("/user/{userId}")
                .then();
    }

    public UserDetailsResponse getUser(Integer userId) {
        return getUserRaw(userId).statusCode(200).extract().as(UserDetailsResponse.class);
    }

}
