package com.thach.example.calculation;

/**
 * Created by THACH-PC
 */
public abstract class Calculator implements Historical {

    protected String user;

    public Calculator() {
    }

    public Calculator(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public abstract double calculate();
}
