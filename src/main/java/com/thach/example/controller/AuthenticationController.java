package com.thach.example.controller;

import com.thach.example.error.EnumError;
import com.thach.example.model.CalculationUser;
import com.thach.example.service.UserService;
import com.thach.example.util.CalculatorPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public Principal login(Principal user) throws Exception {
        if ( user == null) {
            throw new Exception(EnumError.USER_PASS_INVALID.getDescription());
        }
        return user;
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
            String hashedPassword = passwordEncoder().encode(user.getPassword());
            user.setPassword(hashedPassword);
            userService.createUser(user);
            return user;
        } else {
            throw new Exception(EnumError.USER_EXIST.getDescription());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new CalculatorPasswordEncoder();
    }
}
