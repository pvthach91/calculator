package com.thach.example.service;

import com.thach.example.CalculatorApplication;
import com.thach.example.dao.HistoryDAO;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CalculatorApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HistoryServiceTest {

//    @MockBean
    @Autowired
    private HistoryService historyService;

    @MockBean
    private HistoryDAO historyDAO;

    private List<History> histories = new ArrayList<History>();

    @Before
    public void setup() {
        History history = new History("1 + 1 = 2", new Date(), new CalculationUser("thach", "thach"));
        histories.add(history);
    }

    @Test
    public void getHistories() throws Exception {
        Mockito.when(historyDAO.getHistoriesByUser(Mockito.any(CalculationUser.class))).thenReturn(histories);
        CalculationUser user = new CalculationUser("thach", "pass");
        List<History> result = historyService.getHistoriesByUser(user);

        assertEquals(result.size(), histories.size());
    }
}
