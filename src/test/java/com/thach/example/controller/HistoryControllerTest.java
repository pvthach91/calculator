package com.thach.example.controller;

import com.thach.example.CalculatorApplication;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import com.thach.example.service.HistoryService;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class HistoryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private HistoryService historyService;

    @MockBean
    private UserService userService;

    private List<History> histories = new ArrayList<History>();

    private CalculationUser user;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        History history = new History("1 + 1 = 2", new Date(), new CalculationUser("testUser", "pass"));
        histories.add(history);
        user = new CalculationUser("testUser", "pass");
    }

    @Test
    public void getHistories() throws Exception {
        Mockito.when(historyService.getHistoriesByUser(Mockito.any(CalculationUser.class))).thenReturn(histories);
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/history")
                .accept(MediaType.APPLICATION_JSON).content("testUser")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
    }
}
