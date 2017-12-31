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
public class DivisionTest {

    @Before
    public void setup() {
    }

    @Test
    public void testAddition() throws Exception {
        Division division = new Division("testUser", 2, 4);
        double actual = division.calculate();
        assertEquals(0.5, actual, 0);
    }

    @Test
    public void testWrongAddition() throws Exception {
        Division division = new Division("testUser", 4, 2);
        double actual = division.calculate();
        assertNotEquals(3.0, actual, 0);
    }

    @Test
    public void testGenerateHistory() throws Exception {
        double first = 12;
        double second = 4;
        Division division = new Division("testUser", first, second);
        String expect = first + " / " + second + " = " + division.calculate();
        assertEquals(expect, division.generateHistory());
    }
}
