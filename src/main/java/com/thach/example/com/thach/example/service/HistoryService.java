package com.thach.example.com.thach.example.service;

import com.thach.example.dao.HistoryDAO;
import com.thach.example.dao.UserDAO;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@Transactional
@Service
public class HistoryService {

    @Autowired
    private HistoryDAO historyDAO;

    public List<History> getHistory(String username){
        List<History> result = historyDAO.getHistory(username);
        return result;
    }

    public void create(History history){
        historyDAO.create(history);
    }
}
