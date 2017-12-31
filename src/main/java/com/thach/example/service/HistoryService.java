package com.thach.example.service;

import com.thach.example.calculation.Historical;
import com.thach.example.dao.HistoryDAO;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Transactional
@Service
public class HistoryService {

    @Autowired
    private HistoryDAO historyDAO;

    @Autowired
    private UserService userService;

    public List<History> getHistoriesByUser(CalculationUser user){
        List<History> result = historyDAO.getHistoriesByUser(user);
        return result;
    }

    public void createHistory(Historical historical){
        CalculationUser user = userService.findUser(historical.getUser());
        if (user != null){
            History history = new History();
            history.setContent(historical.generateHistory());
            history.setDate(new Date());
            history.setCreatedBy(user);
            historyDAO.createHistory(history);
        }
    }
}
