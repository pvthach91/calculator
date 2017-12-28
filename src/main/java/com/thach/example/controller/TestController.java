package com.thach.example.controller;

import com.thach.example.model.CalculationUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THACH-PC
 */

@RestController
public class TestController {

    @RequestMapping("/")
    public String homepage() {
        return "index";

    }
    @RequestMapping("/hello")
    public CalculationUser sayHello() throws Exception {
        return new CalculationUser("thach", "pass");
//        throw new Exception("Failed to say hello");

    }

    @RequestMapping(method =  RequestMethod.POST, value = "/test-post")
    public String testPost(@RequestBody String user){
//        CalculationUser result =  user;
//        result.setPassword("pass updated");
//
//        return result;
        user += "updated";
        return user;
    }


//    @RequestMapping("/users/{}")
//    public CalculationUser getUser() throws Exception {
//        return new CalculationUser("thach", "pass");
//
//    }
}
