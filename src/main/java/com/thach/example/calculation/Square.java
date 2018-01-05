package com.thach.example.calculation;

/**
 * Created by THACH-PC
 */

public class Square extends OneParamCalculation {

    public Square(){
    }
//    public Square(String user, double firstParam) {
    public Square(double firstParam) {
//        super(user, firstParam);
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
