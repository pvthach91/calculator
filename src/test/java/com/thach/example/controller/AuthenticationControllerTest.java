package com.thach.example.controller;

import com.thach.example.CalculatorApplication;
import com.thach.example.model.CalculationUser;
import com.thach.example.service.UserService;
import com.thach.example.util.MD5;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.junit.Assert.assertNotNull;

/**
 * Created by THACH-PC
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

        String userJson = "{\"username\": \"thach\", \"password\": \"pass\"}";

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

        String userJson = "{\"username\": \"thach\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testSignupFailWithNoUsernamePassword() throws Exception {
        // Username and password are empty
        CalculationUser user = new CalculationUser("", "");
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        String userJson = "{\"username\": \"thach\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testSignupFailWithInvalidLength() throws Exception {
        // Username's length is 2, password's length is 3
        CalculationUser user = new CalculationUser("th", "pas");

        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        String userJson = "{\"username\": \"thach\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test()
    public void testLoginSuccess() throws Exception {
        CalculationUser user = new CalculationUser("thach", "pass");
        String md5Password = MD5.getMD5("pass");
        user.setPassword(md5Password);
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        String userJson = "{\"username\": \"thach\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test(expected = Exception.class)
    public void testLoginFailWithNoUsernamePassword() throws Exception {
        // Username and password are empty
        CalculationUser user = new CalculationUser("", "");
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        String userJson = "{\"username\": \"thach\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testLoginFailWithWrongPassword() throws Exception {
        String inputPassword = "pass";
        CalculationUser user = new CalculationUser("thach", inputPassword);
        String md5Password = MD5.getMD5(inputPassword);
        user.setPassword(md5Password);
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        // 'pass2' is a wrong password, the actual is 'pass'
        String userJson = "{\"username\": \"thach\", \"password\": \"pass2\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }
}
