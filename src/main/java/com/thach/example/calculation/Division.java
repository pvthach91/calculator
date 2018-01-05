package com.thach.example.calculation;

/**
 * Created by THACH-PC
 */
public class Division extends TwoParamCalculation {

    public Division() {
    }

//    public Division(String user, double firstParam, double secondParam) {
    public Division(double firstParam, double secondParam) {
//        super(user, firstParam, secondParam);
        super(firstParam, secondParam);
    }

    @Override
    public String generateHistory() {
        return (this.firstParam + " / " + this.secondParam + " = " + this.calculate());
    }

    @Override
    public double calculate() {
        return this.firstParam / this.secondParam;
    }
}
