package com.thach.example.calculation;

/**
 * Created by THACH-PC
 */

public class Subtraction extends TwoParamCalculation {

    public Subtraction() {
    }

//    public Subtraction(String user, double firstParam, double secondParam) {
    public Subtraction(double firstParam, double secondParam) {
//        super(user, firstParam, secondParam);
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
