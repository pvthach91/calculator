package com.thach.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@Entity
public class CalculationUser {

    @Id
    private String username;

    private String password;

    public CalculationUser() {
    }

    public CalculationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
