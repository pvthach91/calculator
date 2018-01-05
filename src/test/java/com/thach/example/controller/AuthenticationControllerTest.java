package com.thach.example.controller;

import com.thach.example.CalculatorApplication;
import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import com.thach.example.service.MyUserDetailsService;
import com.thach.example.service.UserService;
import com.thach.example.util.MD5;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

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

    @MockBean
    private MyUserDetailsService detailsService;

    @MockBean
    private UserDAO userDAO;

    private User userDetails;
    private CalculationUser calUser;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        userDetails = new User("testUser", "pass", Arrays.asList(authority));

        calUser = new CalculationUser("testUser", "pass");
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
        CalculationUser user = new CalculationUser("", "");
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        String userJson = "{\"username\": \"testUser\", \"password\": \"pass\"}";

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

        String userJson = "{\"username\": \"testUser\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test()
//    @WithMockUser(username="testUser",password = "pass", roles={"USER"})
    public void testLoginSuccess() throws Exception {
        Mockito.when(userDAO.find(Mockito.anyString())).thenReturn(calUser);
//        Mockito.when(detailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);
        CalculationUser user = new CalculationUser("thach", "pass");
        String md5Password = MD5.getMD5("pass");
        user.setPassword(md5Password);
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

//        String userJson = "{\"username\": \"testUser\", \"password\": \"pass\"}";

        User user2 = new User("screen011","screen011", AuthorityUtils.createAuthorityList("USER"));
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user2,null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);



        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login").principal(testingAuthenticationToken )
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/login")
//                .accept(MediaType.APPLICATION_JSON).content(userJson)
//                .contentType(MediaType.APPLICATION_JSON);

//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/login")
//                .accept(MediaType.APPLICATION_JSON);

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

        String userJson = "{\"username\": \"testUser\", \"password\": \"pass\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = Exception.class)
    public void testLoginFailWithWrongPassword() throws Exception {
        String inputPassword = "pass";
        CalculationUser user = new CalculationUser("testUser", inputPassword);
        String md5Password = MD5.getMD5(inputPassword);
        user.setPassword(md5Password);
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        // 'pass2' is a wrong password, the actual is 'pass'
        String userJson = "{\"username\": \"testUser\", \"password\": \"pass2\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }
}
