package com.thach.example.controller;

import com.thach.example.calculation.OneParamCalculation;
import com.thach.example.calculation.TwoParamCalculation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thach
 */

@RestController
public class CalculationController {

    @RequestMapping(method = RequestMethod.POST, value = "/calculateOneParam")
    public double calculateOneParam(@RequestBody OneParamCalculation calculation){
         return calculation.calculate();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/calculateTwoParam")
    public double calculateTwoParam(@RequestBody TwoParamCalculation calculation){
        return calculation.calculate();
    }
}
