package com.thach.example.calculation;

import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@NoArgsConstructor
public abstract class Calculator implements Historical {

    public abstract double calculate();
}
