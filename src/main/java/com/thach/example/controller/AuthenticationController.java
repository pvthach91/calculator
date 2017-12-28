package com.thach.example.controller;

import com.thach.example.service.HistoryService;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.thach.example.service.UserService;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by THACH-PC
 */

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@RequestBody CalculationUser calculationUser){
        CalculationUser savedCalculationUser = userService.findUser(calculationUser.getUsername());
        if (savedCalculationUser != null && savedCalculationUser.getPassword().equals(calculationUser.getPassword())){
            History history = new History();
            history.setHistory("History Test");
            history.setDate(new Date());
            history.setCreatedBy(savedCalculationUser);
            historyService.createHistory(history);
            return "login successfully";
        } else {
            return "login fail";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public String signin(@RequestBody CalculationUser calculationUser){
        CalculationUser savedCalculationUser = userService.findUser(calculationUser.getUsername());
        if (savedCalculationUser == null){
            userService.createUser(calculationUser);
            return calculationUser.getUsername();
        } else {
            return "calculationUser already exists";
        }
    }
}
