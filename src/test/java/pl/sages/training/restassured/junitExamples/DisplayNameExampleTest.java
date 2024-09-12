package pl.sages.training.restassured.junitExamples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GET /users/:userId")
public class DisplayNameExampleTest {

    @Test
    @DisplayName("send request with existing userId")
    public void success() {

    }

    @Test
    @DisplayName("send request with non-existing userId")
    public void error_404() {
        Assertions.fail();
    }

    @Test
    @DisplayName("send request with text as userId")
    public void error_404_invalidType() {

    }

}
