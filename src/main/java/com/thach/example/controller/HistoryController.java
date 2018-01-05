package com.thach.example.controller;

import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import com.thach.example.service.HistoryService;
import com.thach.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by THACH-PC
 */

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    @RequestMapping("/histories")
    public List<History> getHistories() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CalculationUser user = userService.findUser(username);
        return historyService.getHistoriesByUser(user);
    }
}
