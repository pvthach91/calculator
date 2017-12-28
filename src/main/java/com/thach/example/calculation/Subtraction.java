package com.thach.example.calculation;

/**
 * Created by Thach
 */
public class Subtraction extends TwoParamCalculation {

    public Subtraction() {
    }

    public Subtraction(double firstParam, double secondParam) {
        super(firstParam, secondParam);
    }

    @Override
    public String generateHistory() {
        return (this.firstParam + " - " + this.secondParam + " = " + this.calculate());
    }

    @Override
    public double calculate() {
        return this.firstParam - this.secondParam;
    }
}
