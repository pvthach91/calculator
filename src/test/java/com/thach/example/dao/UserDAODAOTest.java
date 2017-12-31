package com.thach.example.dao;

import com.thach.example.CalculatorApplication;
import com.thach.example.model.CalculationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class UserDAODAOTest {

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setup() {
    }

    @Test
    @Transactional
    public void testUserDAO() throws Exception {
        CalculationUser user = new CalculationUser("test", "pass");
        userDAO.create(user);

        CalculationUser savedUser = userDAO.find(user.getUsername());

        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }
}
