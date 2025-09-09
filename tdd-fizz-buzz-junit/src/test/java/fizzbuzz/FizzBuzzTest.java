package fizzbuzz;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    // If number is divisible by 3 -> print Fizz
    // If number is divisible by 5 -> print Buzz
    // If number is divisible by 3 and 5 -> print FizzBuzz
    // If number is NOT divisible by 3 and 5 -> print the number

@Test
@DisplayName("Divisible by Three")
@Order(1)
void testForDivisibleByThree(){
    String expected="Fizz";
    assertEquals(expected,FizzBuzz.compute(3),"Should return Fizz");
}

    @Test
    @DisplayName("Divisible by Five")
    @Order(2)
    void testForDivisibleByFive(){
        String expected="Buzz";
        assertEquals(expected,FizzBuzz.compute(5),"Should return Buzz");
    }

    @Test
    @DisplayName("Divisible by Five and Three")
    @Order(3)
    void testForDivisibleByFiveAndThree(){
        String expected="FizzBuzz";
        assertEquals(expected,FizzBuzz.compute(15),"Should return FizzBuzz");
    }

    @Test
    @DisplayName("Non Divisible by Five and Three")
    @Order(4)
    void testForNonDivisible(){
        String expected="4";
        assertEquals(expected,FizzBuzz.compute(4),"Should return 4");
    }

    @ParameterizedTest(name="value{0}, expected{1}")
    @CsvFileSource(resources = "/test-data.csv")
    @DisplayName("Test with test-data.csv ")
    @Order(5)
    void testByCsvFile(int value, String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }

    @ParameterizedTest(name="value{0}, expected{1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @DisplayName("Test with medium-test-data.csv ")
    @Order(6)
    void testByMediumCsvFile(int value, String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }

    @ParameterizedTest(name="value{0}, expected{1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    @DisplayName("Test with large-test-data.csv ")
    @Order(7)
    void testByLargeCsvFile(int value, String expected){
        assertEquals(expected,FizzBuzz.compute(value));
    }



}
