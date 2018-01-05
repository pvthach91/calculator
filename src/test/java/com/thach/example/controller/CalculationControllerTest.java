package com.thach.example.controller;

import com.thach.example.CalculatorApplication;
import com.thach.example.calculation.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class CalculationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @WithMockUser(username="testUser",password = "pass", roles={"USER"})
    public void testSquare() throws Exception {
        String calculation = "{\"type\": \"square\", \"param\": \"2\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateOneParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Square square = new Square(2);
        Double expect = square.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    @WithMockUser(username="testUser",password = "pass", roles={"USER"})
    public void testAddition() throws Exception {
        String calculation = "{\"type\": \"addition\", \"firstParam\": \"2\", \"secondParam\": \"4\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Addition addition = new Addition(2, 4);
        Double expect = addition.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    @WithMockUser(username="testUser",password = "pass", roles={"USER"})
    public void testSubtraction() throws Exception {
        String calculation = "{\"type\": \"subtraction\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Subtraction subtraction = new Subtraction(10, 3);
        Double expect = subtraction.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    @WithMockUser(username="testUser",password = "pass", roles={"USER"})
    public void testMultiplication() throws Exception {
        String calculation = "{\"type\": \"multiplication\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Multiplication multiplication = new Multiplication(10, 3);
        Double expect = multiplication.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test
    @WithMockUser(username="testUser",password = "pass", roles={"USER"})
    public void testDivision() throws Exception {
        String calculation = "{\"type\": \"division\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Division division = new Division(10, 3);
        Double expect = division.calculate();
        assertEquals(expect.toString(), response.getContentAsString());
    }

    @Test(expected = Exception.class)
    public void testTwoParamCalculateFailWithNoAuthentication() throws Exception {
        String calculation = "{\"type\": \"division\", \"firstParam\": \"10\", \"secondParam\": \"3\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateTwoParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testOneParamCalculateFailWithNoAuthentication() throws Exception {
        String calculation = "{\"type\": \"square\", \"param\": \"2\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/calculateOneParam")
                .accept(MediaType.APPLICATION_JSON).content(calculation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }
}
