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
public class SubtractionTest {

    @Before
    public void setup() {
    }

    @Test
    public void testSubtraction() throws Exception {
        Subtraction subtraction = new Subtraction(10, 3);
        double actual = subtraction.calculate();
        assertEquals(7.0, actual, 0);
    }

    @Test
    public void testWrongSubtraction() throws Exception {
        Subtraction subtraction = new Subtraction(10, 3);
        double actual = subtraction.calculate();
        assertNotEquals(6.0, actual, 0);
    }

    @Test
    public void testGenerateHistory() throws Exception {
        double first = 12;
        double second = 4;
        Subtraction subtraction = new Subtraction(first, second);
        String expect = first + " - " + second + " = " + subtraction.calculate();
        assertEquals(expect, subtraction.generateHistory());
    }
}
