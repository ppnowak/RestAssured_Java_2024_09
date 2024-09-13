package pl.sages.training.restassured.framework.commons;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RestAssuredWrapper {

    public static RequestSpecification given() {
        RestAssured.reset();
        RestAssured.baseURI = Config.getBaseUri();
        if (Config.getIsApiLoggingEnabled()) {
            RestAssured.filters(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter()
            );
        }
        RestAssured.filters(new AllureRestAssured());
        return RestAssured.given();
    }

}
