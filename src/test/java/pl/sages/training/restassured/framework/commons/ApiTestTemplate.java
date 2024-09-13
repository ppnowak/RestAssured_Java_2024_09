package pl.sages.training.restassured.framework.commons;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public class ApiTestTemplate {

//    @BeforeEach
//    public void setupRestAssured() {
//        RestAssured.reset();
//        // DONE: move baseUri outside, so we don't have to remember about filling this
//        RestAssured.baseURI = "https://api.breathtaking.website";
//        RestAssured.filters(
//                // DONE: move request logs outside, so we can decide whether to show logs or not
//                new RequestLoggingFilter(),
//                // DONE: move response logs outside, so we can decide whether to show logs or not
//                new ResponseLoggingFilter()
//        );
//    }

//    @BeforeEach
//    public void setupRestAssured() {
//        RestAssured.reset();
//        RestAssured.baseURI = Config.getBaseUri();
//        if (Config.getIsRequestLoggingEnabled()) {
//            RestAssured.filters(new RequestLoggingFilter());
//        }
//        if (Config.getIsResponseLoggingEnabled()) {
//            RestAssured.filters(new ResponseLoggingFilter());
//        }
//    }

//    abstract String getEnvUrl();

    @BeforeEach
    public void setupRestAssured() {
        RestAssured.reset();
        RestAssured.baseURI = Config.getBaseUri();
        if (Config.getIsApiLoggingEnabled()) {
            RestAssured.filters(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter()
            );
        }
    }

}
