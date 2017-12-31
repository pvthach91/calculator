package com.thach.example.service;

import com.thach.example.CalculatorApplication;
import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    private CalculationUser user;

    @Before
    public void setup() {
        user = new CalculationUser("thach", "pass");
    }

    @Test
    public void findUser() throws Exception {
        Mockito.when(userDAO.find(Mockito.anyString())).thenReturn(user);
        CalculationUser user = new CalculationUser("thach", "pass");
        CalculationUser result = userService.findUser("thach");

        assertEquals(result.getUsername(), result.getUsername());
    }
}
