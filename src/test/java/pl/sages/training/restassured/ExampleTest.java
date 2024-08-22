package pl.sages.training.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class ExampleTest {

    @Test
    public void test() {
        RestAssured.given().log().all().get("https://api.restful-api.dev/objects/7").then().log().all();
    }


}
