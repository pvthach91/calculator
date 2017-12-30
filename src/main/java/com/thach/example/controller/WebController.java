package com.thach.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	@RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/signup",method = RequestMethod.GET)
    public String signup(){
        return "signup";
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String calculate(){
        return "calculator";
    }

    @RequestMapping(value="/history",method = RequestMethod.GET)
    public String history(){
        return "history";
    }
}