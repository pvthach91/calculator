package com.thach.example.error;

/**
 * Created by THACH-PC
 */
public enum EnumError {
    NEED_LOG_IN("Please login"),
    USER_PASS_INVALID("Username and password invalid"),
    USER_EXIST("User already exists, please login");

    private String description;

    private EnumError(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
