package com.thach.example.controller;

import com.thach.example.com.thach.example.service.HistoryService;
import com.thach.example.com.thach.example.service.UserService;
import com.thach.example.model.CalculationUser;
import com.thach.example.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(method = RequestMethod.POST, value = "/history")
    public List<History> getHistory(@RequestBody String username){
        List<History> result = historyService.getHistory(username);
        return result;
    }

}
