package com.thach.example.calculation;

import com.thach.example.CalculatorApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdditionTest {

    @Before
    public void setup() {
    }

    @Test
    public void testAddition() throws Exception {
        Addition addition = new Addition("thach", 2, 4);
        double actual = addition.calculate();
        assertEquals(6.0, actual, 0);
    }

    @Test
    public void testWrongAddition() throws Exception {
        Addition addition = new Addition("thach", 2, 4);
        double actual = addition.calculate();
        assertNotEquals(7.0, actual, 0);
    }

    @Test
    public void testGenerateHistory() throws Exception {
        double first = 2;
        double second = 4;
        Addition addition = new Addition("thach", first, second);
        String expect = first + " + " + second + " = " + addition.calculate();
        assertEquals(expect, addition.generateHistory());
    }
}
