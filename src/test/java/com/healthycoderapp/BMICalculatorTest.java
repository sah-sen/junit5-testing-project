package com.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("Setup for databases would happen here. but this is a simple before all ");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Used to close databases but this is a simple after all");
    }

//the number in the curly brackets refers to the order. so 0th value is weight(89.0) and 1st is height (172)
    @ParameterizedTest(name = "weight={0}, height={1}")
    //csv stands for comma separated values
    @CsvSource(value = {"89.0, 1.72","95.0, 1.75", "110, 1.78" })
    @DisplayName("Testing isDietRecommented is true")
    public void testingIsDietRecommentedIsTrueCSV1(Double coderWeight, Double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;

        //when
        boolean recomended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recomended);
       // assertTrue(BMICalculator.isDietRecommended(89.0,1.65));
        }




    //file version

    //the number in the curly brackets refers to the order. so 0th value is weight(89.0) and 1st is height (172)
    @ParameterizedTest(name = "weight={0}, height={1}")
    //the num lines to skip will skip the first row, which contains the headings
    @CsvFileSource(resources ="/diet-recommended-input-data.csv", numLinesToSkip =1)
    @DisplayName("Testing isDietRecommented is true")
    public void testingIsDietRecommentedIsTrueCSVFile(Double coderWeight, Double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;

        //when
        boolean recomended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recomended);
        // assertTrue(BMICalculator.isDietRecommended(89.0,1.65));
    }


    //Value Source version
    @ParameterizedTest
    //Value source contains values that are to be injected into the test. works for one veriable
    @ValueSource (doubles = {70.0, 89.0,95.0,110.0})
    @DisplayName("Testing isDietRecommented is true")
    public void testingIsDietRecommentedIsTrueVS(Double coderWeight) {
        //given
        double weight = coderWeight;
        double height = 1.7;

        //when
        boolean recomended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recomended);
        // assertTrue(BMICalculator.isDietRecommended(89.0,1.65));
    }



    @Test
    @DisplayName("Returns false for isDiedRecommended")
    public void returnsFalseForIsDiedRecommended() {
        //given
        double weight = 50.0;
        double height = 1.92;
        //when
        boolean recomended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertFalse(recomended);
        }

    @Test
    @DisplayName("Should throw arithmetic exception when height is zero")
    public void shouldThrowArithmeticExceptionWhenHeightIsZero() {
        //given
        double weight = 50.0;
        double height = 0.0;
        //when
        Executable executable = () -> BMICalculator.isDietRecommended(weight,height);

        //then
        assertThrows(ArithmeticException.class,executable );

        }


    @Test
    @DisplayName("should return coder with the worst bmi when list is not empty")
    public void shouldReturnCoderWithTheWorstBmiWhenListIsNotEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.8, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));

        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        //then
        //Both tests will run even if the first fails
        assertAll(
                () ->assertEquals(1.82, coderWorstBMI.getHeight()),
                () ->assertEquals(98.0, coderWorstBMI.getWeight())
        );
    }
    @Test
    @DisplayName("return null when coder list is empty")
    public void returnNullWhenCoderListIsEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        //then
        assertNull(coderWorstBMI);
        }

    @Test
    @DisplayName("should return the correct BMO score array when not empty")
    public void shouldReturnTheCorrectBmoScoreArrayWhenNotEmpty() {
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.8, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};

        //when
        double[] bmiScores =BMICalculator.getBMIScores(coders);

        //then
        assertArrayEquals(expected, bmiScores);
    }


}