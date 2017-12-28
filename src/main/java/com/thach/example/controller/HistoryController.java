package com.thach.example.controller;

import com.thach.example.model.CalculationUser;
import com.thach.example.service.HistoryService;
import com.thach.example.model.History;
import com.thach.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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

    @RequestMapping(method = RequestMethod.POST, value = "/history")
    public List<History> getHistories(@RequestBody String username){
        CalculationUser user = userService.findUser(username);
        if (user == null){
            // throw exception
            return Collections.EMPTY_LIST;
        } else {
            List<History> result = historyService.getHistoriesByUser(user);
            return result;
        }
    }
}
