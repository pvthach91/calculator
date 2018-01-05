package com.thach.example.calculation;

import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@NoArgsConstructor
public class Multiplication extends TwoParamCalculation {

    public Multiplication(double firstParam, double secondParam) {
        super(firstParam, secondParam);
    }

    @Override
    public String generateHistory() {
        return (this.firstParam + " * " + this.secondParam + " = " + this.calculate());
    }

    @Override
    public double calculate() {
        return this.firstParam * this.secondParam;
    }
}
