package pl.sages.training.restassured.apiExamples;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ExampleGetMirrorTest {

    /*
    Zadanie #1
        Wyślij request GET /mirror zawierający:
            parametr typu query z kluczem firstName i swoim imieniem jako wartość
            nagłówek X-Random-Number zawierający liczbę
            nagłówek Content-Type odpowiedni dla pliku JSON

        Sprawdź w odpowiedzi:
            czy zwrócony został kod HTTP 200
            czy serwer zwraca nagłówek X-RateLimit-Limit z wartością 60
            czy serwer zwraca pod ścieżką queryParams.firstName imię podane w requeście
     */

//    enum HttpResponseCodes {
//        OK(200),
//        NOT_FOUND(404),
//        INTERNAL_ERROR(500);
//
//        public int status;
//        HttpResponseCodes(int i) {
//            this.status = i;
//        }
//    }

    @Test
    public void test() {
//        Integer HTTP_OK = 200;

        Integer defaultServerRateLimit = 60;
        String firstName = "Piotr";

        RestAssured.given()
                .baseUri("https://api.breathtaking.website")
                .queryParam("firstName", firstName)
                .header("X-Random-Number", 546455)
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .log().all()

                .when()
                .get("/mirror")
//                .get("/mirror?firstName=Piotr")

                .then().assertThat()
                .statusCode(200)
                .header("X-RateLimit-Limit", defaultServerRateLimit.toString())
                .body("queryParams.firstName", Matchers.equalTo(firstName))
                .log().all()
//                .log().body()

        ;
    }

}
