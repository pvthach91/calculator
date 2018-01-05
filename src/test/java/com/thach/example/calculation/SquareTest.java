package com.thach.example.calculation;

import com.thach.example.CalculatorApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class SquareTest {

    @Before
    public void setup() {
    }

    @Test
    public void testSquare() throws Exception {
        Square square = new Square(2);
        double actual = square.calculate();
        assertEquals(4.0, actual, 0);
    }

    @Test
    public void testWrongSquare() throws Exception {
        Square square = new Square(2);
        double actual = square.calculate();
        assertNotEquals(5.0, actual, 0);
    }

    @Test
    public void testGenerateHistory() throws Exception {
        double number = 12;
        Square square = new Square(number);
        String expect = number + " square " + "= " + square.calculate();
        assertEquals(expect, square.generateHistory());
    }
}
