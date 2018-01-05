package com.thach.example.calculation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
public abstract class TwoParamCalculation extends Calculator {

    @Getter
    @Setter
    protected double firstParam;

    @Getter
    @Setter
    protected double secondParam;

    protected TwoParamCalculation(double firstParam, double secondParam) {
        this.firstParam = firstParam;
        this.secondParam = secondParam;
    }
}
