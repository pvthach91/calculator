package com.thach.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by THACH-PC
 */

@Entity
public class CalculationUser implements Serializable{

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
