package pl.sages.training.restassured.junitExamples;

import org.junit.jupiter.api.*;
import pl.sages.training.restassured.framework.commons.TestTags;

public class TagsExampleTest {

    @Test
    @Tag(TestTags.SMOKE_TESTS)
//    @Tag("smoke-tests")
//    @Tag("smokeTests")
//    @Tag("smoke-TESTS")
//    @Tag("smkoe-tests")
    public void test1() {

    }

    @Test
    @Tag(TestTags.SMOKE_TESTS)
    public void test2() {

    }

    @Test
    public void test3() {

    }

    @Test
    public void test4() {

    }

    @Test
    public void test5() {

    }

}
