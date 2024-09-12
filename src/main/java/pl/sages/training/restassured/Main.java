package pl.sages.training.restassured;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello and welcome!");
//        for (int i = 1; i <= 5; i++) {
//            System.out.println("i = " + i);
//        }

        boolean statement = false;
        System.out.println("value is=" + (statement = true));
        if (statement) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        int num = 10;
        System.out.println("Number is " + num);
        System.out.println("New number is " + (num = 1000));

    }

}