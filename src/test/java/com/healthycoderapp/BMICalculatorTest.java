package com.healthycoderapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {


    @Test
    @DisplayName("Testing isDietRecommented is true")
    public void testingIsDietRecommentedIsTrue() {
        //given
        double weight = 89.0;
        double height = 1.72;
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



}