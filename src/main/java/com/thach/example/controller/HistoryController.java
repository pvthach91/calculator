package com.thach.example.controller;

import com.thach.example.error.EnumError;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import com.thach.example.service.HistoryService;
import com.thach.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.POST, value = "/history")
    public List<History> getHistories(@RequestBody String username) throws Exception {
        if (username == null || username.isEmpty()){
            throw new Exception(EnumError.NEED_LOG_IN.getDescription());
        }

        CalculationUser user = userService.findUser(username);
        if (user == null){
            throw new Exception(EnumError.NEED_LOG_IN.getDescription());
        }

        return historyService.getHistoriesByUser(user);
    }
}
