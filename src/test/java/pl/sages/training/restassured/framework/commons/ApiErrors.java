package pl.sages.training.restassured.framework.commons;

import pl.sages.training.restassured.framework.models.ErrorResponse;

public class ApiErrors {

    public static final ErrorResponse USER_NOT_FOUND = new ErrorResponse("User not found.", 404);


}
