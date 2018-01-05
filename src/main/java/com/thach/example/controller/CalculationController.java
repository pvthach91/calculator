package com.thach.example.controller;

import com.thach.example.calculation.OneParamCalculation;
import com.thach.example.calculation.TwoParamCalculation;
import com.thach.example.error.EnumError;
import com.thach.example.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THACH-PC
 */

@RestController
public class CalculationController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(method = RequestMethod.POST, value = "/calculateOneParam")
    public double calculateOneParam(@RequestBody OneParamCalculation calculation) throws Exception {
//        String user = calculation.getUser();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (user == null || user.isEmpty()){
//            throw new Exception(EnumError.USER_EMPTY_NEED_LOG_IN.getDescription());
//        }
        historyService.createHistory(calculation);

         return calculation.calculate();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/calculateTwoParam")
    public double calculateTwoParam(@RequestBody TwoParamCalculation calculation) throws Exception {
//        String user = calculation.getUser();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (user == null || user.isEmpty()){
//            throw new Exception(EnumError.USER_EMPTY_NEED_LOG_IN.getDescription());
//        }
        historyService.createHistory(calculation);

        return calculation.calculate();
    }
}
