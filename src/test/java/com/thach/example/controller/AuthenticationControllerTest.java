package com.thach.example.controller;

import com.thach.example.CalculatorApplication;
import com.thach.example.model.CalculationUser;
import com.thach.example.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testSignupSuccess() throws Exception {
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(null);

        String userJson = "{\"username\": \"testUser\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test(expected = Exception.class)
    public void testSignupFailWithExistUser() throws Exception {
        CalculationUser user = new CalculationUser("thach", "pass");
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        String userJson = "{\"username\": \"testUser\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testSignupFailWithNoUsernamePassword() throws Exception {
        // Username and password are empty
        String userJson = "{\"username\": \"\", \"password\": \"\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testSignupFailWithInvalidLength() throws Exception {
        // Username's length is 2, password's length is 3
        String userJson = "{\"username\": \"te\", \"password\": \"pas\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test()
    public void testLoginSuccess() throws Exception {
        CalculationUser user = new CalculationUser("testUser", "pass");
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login").principal(testingAuthenticationToken )
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test(expected = Exception.class)
    public void testLoginFailWithNoUsernamePassword() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }
}