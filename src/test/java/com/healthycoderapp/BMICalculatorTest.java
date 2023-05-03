package com.healthycoderapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}