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
public class SquareTest {

    @Before
    public void setup() {
    }

    @Test
    public void testAddition() throws Exception {
        Square square = new Square("thach", 2);
        double actual = square.calculate();
        assertEquals(4.0, actual, 0);
    }

    @Test
    public void testWrongAddition() throws Exception {
        Square square = new Square("thach", 2);
        double actual = square.calculate();
        assertNotEquals(5.0, actual, 0);
    }
}
