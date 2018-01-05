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
        @JsonSubTypes.Type(value = Square.class, name = "square")
})
@NoArgsConstructor
public abstract class OneParamCalculation extends Calculator {

    @Getter
    @Setter
    protected double param;

    protected OneParamCalculation(double param) {
        this.param = param;
    }
}
