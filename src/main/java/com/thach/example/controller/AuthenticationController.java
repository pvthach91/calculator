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

    private final int USERNAME_MIN_LENGTH = 3;
    private final int PASSWORD_MIN_LENGTH = 4;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public CalculationUser login(@RequestBody CalculationUser user) throws Exception {
        //Check if user inputs username and password
        boolean isUsernameValid = user.getUsername() != null && !user.getUsername().isEmpty();
        boolean isPasswordValid = user.getPassword() != null && !user.getPassword().isEmpty();
        if (!isUsernameValid || !isPasswordValid){
            throw new Exception(EnumError.USER_PASSWORD_MISSING.getDescription());
        }

        CalculationUser savedUser = userService.findUser(user.getUsername());
        if (savedUser != null && savedUser.getPassword().equals(MD5.getMD5(user.getPassword()))){
            return savedUser;
        } else {
            throw new Exception(EnumError.USER_PASS_INVALID.getDescription());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public CalculationUser signup(@RequestBody CalculationUser user) throws Exception {
        String  username = user.getUsername();
        String password = user.getPassword();

        //Check if user inputs username and password
        boolean isUsernameValid = username != null && !username.isEmpty();
        boolean isPasswordValid = password != null && !password.isEmpty();
        if (!isUsernameValid || !isPasswordValid){
            throw new Exception(EnumError.USER_PASSWORD_MISSING.getDescription());
        }

        // Check min length for username and password
        if (username.length() < USERNAME_MIN_LENGTH || password.length() < PASSWORD_MIN_LENGTH){
            throw new Exception(EnumError.USER_PASSWORD_MIN_LENGTH.getDescription());
        }

        CalculationUser savedUser = userService.findUser(user.getUsername());
        if (savedUser == null){
            String md5Password = MD5.getMD5(user.getPassword());
            user.setPassword(md5Password);
            userService.createUser(user);
            return user;
        } else {
            throw new Exception(EnumError.USER_EXIST.getDescription());
        }
    }
}
