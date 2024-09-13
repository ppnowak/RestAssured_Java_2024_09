package pl.sages.training.restassured.apiExamplesWithTemplate;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pl.sages.training.restassured.framework.commons.ApiTestTemplate;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;

public class C_PostExamples extends ApiTestTemplate {

    @Test
    public void urlencoded() {

        // send POST /mirror with:
        // x-www-form-urlencoded content type
        // username = admin
        // password = admin1

        given()
                .formParam("username", "admin")
                .formParam("password", "admin1łłł")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                // we don't have to specify content type, as it's a default for formParam(s)
                // by default charset=ISO-8... (without polish letters)

                .when().post("/mirror")

                .then().statusCode(200)

        ;

    }

    @Test
    public void multiPart() {

        // send POST /mirror with:
        // text file to upload
        File textFile = new File("users.csv");
        File imageFile = new File("image.png");

        given()
                .multiPart("userFile", textFile)
                .multiPart(imageFile)

                .when().post("/mirror")

                .then().statusCode(200)

        ;

    }

    @Test
    public void text() {

        given()
                .body("Input text")
                .when().post("/mirror")
                .then().statusCode(200)
        ;

        given()
                .contentType(ContentType.JSON)
                .body("{\"firstName\": \"Piotr\"}")
                .when().post("/mirror")
                .then().statusCode(200)
        ;
    }

    @Test
    public void map() {
        Map<String, String> address = Map.of(
                "street", "Ulica",
                "zipCode", "00-000"
        );

        Map<String, Object> jsonRequest = new HashMap<>();
        jsonRequest.put("firstName", "Piotr");
        jsonRequest.put("id", 1234);
        jsonRequest.put("address", address);

        given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when().post("/mirror")
                .then().statusCode(200)
        ;
    }

    @Test
    public void list() {
        List<Object> jsonRequest = Arrays.asList(
                "Piotr",
                100,
                true,
                false,
                new Date()
        );
        given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when().post("/mirror")
                .then().statusCode(200)
        ;
    }

}
