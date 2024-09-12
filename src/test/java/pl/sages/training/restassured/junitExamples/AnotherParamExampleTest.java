package pl.sages.training.restassured.junitExamples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AnotherParamExampleTest {

    /**
     * Istnieje metoda API, ktora dla zadanego zapytania (query) zwraca różne wyniki
     *
     * Za każdym razem wysyłamy na to API ten sam JSON:
     * { equation: "<string>" }
     *
     * Dla każdego equation mamy z góry określony oczekiwany wynik, np:
     * { result: "<result>" }
     */

    Integer sendSolveEquation(String equation) {
        // jakies kroki zeby wyslac to po API
        return 0;
    }

//    @Test
//    public void testSum() {
//        Integer result = sendSolveEquation("5+10");
//        Assertions.assertEquals(15, result);
//    }
//
//    @Test
//    public void testSub() {
//        Integer result = sendSolveEquation("5-10");
//        Assertions.assertEquals(-5, result);
//    }

    public static Object[][] getTestData() {
        return new Object[][]{
                {"5+10", 15},
                {"5-10", -5}
        };
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void test(String equation, Integer expectedResult) {
        Integer actualResult = sendSolveEquation(equation);
        Assertions.assertEquals(expectedResult, actualResult);
    }


}
