package com.thach.example.controller;

import com.thach.example.CalculatorApplication;
import com.thach.example.calculation.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testSquare() throws Exception {
        String calculation = "{\"user\": \"thach\", \"type\": \"square\", \"param\": \"2\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateOneParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Square square = new Square("thach", 2);
        Double expect = square.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    public void testAddition() throws Exception {
        String calculation = "{\"user\": \"thach\", \"type\": \"addition\", \"firstParam\": \"2\", \"secondParam\": \"4\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Addition addition = new Addition("thach", 2, 4);
        Double expect = addition.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    public void testSubtraction() throws Exception {
        String calculation = "{\"user\": \"thach\", \"type\": \"subtraction\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Subtraction subtraction = new Subtraction("thach", 10, 3);
        Double expect = subtraction.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    public void testMultiplication() throws Exception {
        String calculation = "{\"user\": \"thach\", \"type\": \"multiplication\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Multiplication multiplication = new Multiplication("thach", 10, 3);
        Double expect = multiplication.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    public void testDivision() throws Exception {
        String calculation = "{\"user\": \"thach\", \"type\": \"division\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Division division = new Division("thach", 10, 3);
        Double expect = division.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }
}
