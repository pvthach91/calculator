package com.thach.example.calculation;

/**
 * Created by Thach
 */
public class Square extends OneParamCalculation {

    public Square(){
    }
    public Square(double firstParam) {
        super(firstParam);
    }

    @Override
    public String generateHistory() {
        return (this.param + " square " + " = " + this.calculate());
    }

    @Override
    public double calculate() {
        return this.param * this.param;
    }
}
