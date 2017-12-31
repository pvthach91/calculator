package com.thach.example.service;

import com.thach.example.CalculatorApplication;
import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    private CalculationUser user;

    @Before
    public void setup() {
        user = new CalculationUser("testUser", "pass");
    }

    @Test
    public void findUser() throws Exception {
        Mockito.when(userDAO.find(Mockito.anyString())).thenReturn(user);
        CalculationUser user = new CalculationUser("testUser", "pass");
        CalculationUser result = userService.findUser("testUser");

        assertEquals(result.getUsername(), result.getUsername());
    }
}
