package pl.sages.training.restassured.junitExamples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParametrizedExampleTest {

    private String lastLetter;

    void addLetterToSystem(String letter) {
        // jakies API, ktore wysyla POST /letters z JSON { "letter": "<letter>" }
        if (null == letter) {
            throw new RuntimeException("Cannot use null as letter");
        } else if (letter.isEmpty()) {
            throw new RuntimeException("Cannot use empty as letter");
        }
        lastLetter = letter;
    }

    String getLastLetterFromSystem() {
        // jakies API, ktore wysyla GET /letters/last i dostaje JSON { "lastLetter": "<letter>" }
        return lastLetter;
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void testFromValueSource(String letter) {
        addLetterToSystem(letter);
        Assertions.assertEquals(letter, getLastLetterFromSystem(), "Last letter is incorrect");
    }

    @ParameterizedTest
//    @NullSource
//    @EmptySource
    @NullAndEmptySource
    public void testFromNullOrEmptySources(String letter) {
        addLetterToSystem(letter);
        Assertions.assertEquals(letter, getLastLetterFromSystem(), "Last letter is incorrect");
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void nullOrEmptyList(List list) {
        System.out.println(list);
    }

    enum Alphabet {A, B, C, D, E, F, G}

    @ParameterizedTest
    @EnumSource(Alphabet.class)
    public void testFromEnumSource(Alphabet letter) {
        addLetterToSystem(letter.toString());
        Assertions.assertEquals(letter.toString(), getLastLetterFromSystem(), "Last letter is incorrect");
    }

    @ParameterizedTest
    @EnumSource(names = {"A", "B", "G"})
    public void testFromEnumSourcePartial(Alphabet letter) {
        addLetterToSystem(letter.toString());
        Assertions.assertEquals(letter.toString(), getLastLetterFromSystem(), "Last letter is incorrect");
    }

    @ParameterizedTest
//    @CsvFileSource(files = {"users.csv"})
    @CsvFileSource(files = {"users_with_headers.csv"}, numLinesToSkip = 1)
    public void testFromCsv(String firstName, String lastName) {
        System.out.println(firstName + " " + lastName);
    }

    public static Object[][] getTestData() {
        return new Object[][]{
                {"Piotr", 100, Arrays.asList("a", "b", "c")},
                {"Stefan", 50, List.of("g")},
        };
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void testFromMethodDataSource(String name, Integer age, List<String> privileges) {
        System.out.println("Name="+name+", age="+age+", priv="+privileges);
    }

    public static List<Arguments> cartesianData() {
        List<String> urls = Arrays.asList("http://127.0.0.1", "https://prod.environment");
        List<Arguments> data = new ArrayList<>();
        for (String url : urls) {
            for (Alphabet letter : Alphabet.values()) {
                data.add(Arguments.of(url, letter));
            }
        }
        return data;
    }

    @ParameterizedTest
    @MethodSource("cartesianData")
    public void testFromCartesianData(String url, Alphabet letter) {
        System.out.println(url + " -> " + letter);
    }





}
