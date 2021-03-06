package com.thach.example.error;

/**
 * Created by THACH-PC
 */
public enum EnumError {
    USERNAME_NOT_FOUND("Username not found"),
    USER_PASS_INVALID("Username and password invalid"),
    USER_EXIST("User already exists, please login"),
    USER_PASSWORD_MISSING("Please check to input username and password"),
    USER_PASSWORD_MIN_LENGTH("Please check length for username and password");

    private String description;

    private EnumError(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
