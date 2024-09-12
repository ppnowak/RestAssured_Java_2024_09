package pl.sages.training.restassured.junitExamples;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // + @Order
//@TestMethodOrder(MethodOrderer.Random.class) // random, czyli @Order nie jest potrzebny
public class ExecutionOrderExample {

    @Test
    @Order(5)
    public void test1() {

    }

    @Test
    @Order(4)
    public void test2() {

    }

    @Test
    @Order(3)
    public void test3() {

    }

    @Test
    public void test4() {

    }

    @Test
    public void test5() {

    }

}
