package pl.sages.training.restassured.framework.models;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

public class ErrorResponse {

    private String error;
    private int status;

    public ErrorResponse() {
    }

    public ErrorResponse(String error, int status) {
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public void verify(ValidatableResponse response) {
        ErrorResponse error = response
                .statusCode(this.status)
                .extract().as(ErrorResponse.class);
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(error.getStatus(), Matchers.equalTo(this.status)),
                () -> MatcherAssert.assertThat(error.getError(), Matchers.equalTo(this.error))
        );
    }

}