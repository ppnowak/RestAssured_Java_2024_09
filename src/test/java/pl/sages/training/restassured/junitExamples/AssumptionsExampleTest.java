package pl.sages.training.restassured.junitExamples;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class AssumptionsExampleTest {

    @Test
    @Disabled
    public void exampleDisabled() {

    }

    @Test
    public void assumptionExample() {
        Assumptions.assumeTrue(CommonsExample.isUAT());
    }

    @Test
    public void noRestrictions() {

    }

    @Test
    @EnabledOnOs(OS.LINUX)
    public void conditionalTest() {

    }

}
