package com.thach.example.calculation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by THACH-PC
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Addition.class, name = "addition"),
        @JsonSubTypes.Type(value = Subtraction.class, name = "subtraction"),
        @JsonSubTypes.Type(value = Division.class, name = "division"),
        @JsonSubTypes.Type(value = Multiplication.class, name = "multiplication")
})
public abstract class TwoParamCalculation extends Calculator {

    protected double firstParam;

    protected double secondParam;

    protected TwoParamCalculation() {
    }

    protected TwoParamCalculation(String user, double firstParam, double secondParam) {
        super(user);
        this.firstParam = firstParam;
        this.secondParam = secondParam;
    }

    public double getFirstParam() {
        return firstParam;
    }

    public void setFirstParam(double firstParam) {
        this.firstParam = firstParam;
    }

    public double getSecondParam() {
        return secondParam;
    }

    public void setSecondParam(double secondParam) {
        this.secondParam = secondParam;
    }
}
