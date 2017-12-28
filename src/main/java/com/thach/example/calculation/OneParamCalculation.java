package com.thach.example.calculation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by THACH-PC
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Square.class, name = "square")
})
public abstract class OneParamCalculation extends Calculator {

    protected double param;

    protected OneParamCalculation() {
    }

    protected OneParamCalculation(String user, double param) {
        super(user);
        this.param = param;
    }

    public double getParam() {
        return param;
    }

    public void setParam(double param) {
        this.param = param;
    }
}
