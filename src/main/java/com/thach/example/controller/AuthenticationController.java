package com.thach.example.controller;

import com.thach.example.error.EnumError;
import com.thach.example.service.HistoryService;
import com.thach.example.model.CalculationUser;
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
    public String login(@RequestBody CalculationUser user) throws Exception {
        CalculationUser savedUser = userService.findUser(user.getUsername());
        if (savedUser != null && savedUser.getPassword().equals(user.getPassword())){
            return savedUser.getUsername();
        } else {
            throw new Exception(EnumError.USER_PASS_INVALID.getDescription());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public String signin(@RequestBody CalculationUser user) throws Exception {
        CalculationUser savedUser = userService.findUser(user.getUsername());
        if (savedUser == null){
            userService.createUser(user);
            return user.getUsername();
        } else {
            throw new Exception(EnumError.USER_EXIST.getDescription());
        }
    }
}
