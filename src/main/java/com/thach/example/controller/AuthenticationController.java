package com.thach.example.controller;

import com.thach.example.error.EnumError;
import com.thach.example.model.CalculationUser;
import com.thach.example.service.UserService;
import com.thach.example.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THACH-PC
 */

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public CalculationUser login(@RequestBody CalculationUser user) throws Exception {
        CalculationUser savedUser = userService.findUser(user.getUsername());
        if (savedUser != null && savedUser.getPassword().equals(MD5.getMD5(user.getPassword()))){
            return savedUser;
        } else {
            throw new Exception(EnumError.USER_PASS_INVALID.getDescription());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public CalculationUser signup(@RequestBody CalculationUser user) throws Exception {
        CalculationUser savedUser = userService.findUser(user.getUsername());
        if (savedUser == null){
            String password = MD5.getMD5(user.getPassword());
            user.setPassword(password);
            userService.createUser(user);
            return user;
        } else {
            throw new Exception(EnumError.USER_EXIST.getDescription());
        }
    }
}
