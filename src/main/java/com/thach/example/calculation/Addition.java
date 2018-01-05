package com.thach.example.calculation;

/**
 * Created by THACH-PC
 */
public class Addition extends TwoParamCalculation {

    public Addition() {
    }

//    public Addition(String user, double firstParam, double secondParam) {
    public Addition(double firstParam, double secondParam) {
//        super(user, firstParam, secondParam);
        super(firstParam, secondParam);
    }

    @Override
    public String generateHistory() {
        return (this.firstParam + " + " + this.secondParam + " = " + this.calculate());
    }

    @Override
    public double calculate() {
        return this.firstParam + this.secondParam;
    }
}
