package com.thach.example.dao;

import com.thach.example.CalculatorApplication;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
public class HistoryDAOTest {

    @Autowired
    private HistoryDAO historyDAO;

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setup() {
    }

    @Test
    @Transactional
    public void getHistories() throws Exception {
        CalculationUser user = new CalculationUser("testUser", "pass");
        userDAO.create(user);

        CalculationUser savedUser = userDAO.find(user.getUsername());

        History history = new History("1 + 1 = 2", new Date(), savedUser);
        historyDAO.createHistory(history);

        List<History> savedHistories = historyDAO.getHistoriesByUser(user);
        History savedHistory = savedHistories.get(0);

        assertEquals(history.getContent(), savedHistory.getContent());
        assertEquals(history.getDate(), savedHistory.getDate());
    }
}
