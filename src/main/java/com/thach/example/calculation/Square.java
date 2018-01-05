package com.thach.example.calculation;

import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@NoArgsConstructor
public class Square extends OneParamCalculation {

    public Square(double firstParam) {
        super(firstParam);
    }

    @Override
    public String generateHistory() {
        return (this.param + " square " + "= " + this.calculate());
    }

    @Override
    public double calculate() {
        return this.param * this.param;
    }
}
