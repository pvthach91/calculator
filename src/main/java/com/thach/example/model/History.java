package com.thach.example.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by THACH-PC on 12/27/2017.
 */

@Entity
public class History {

    @Id
    @GeneratedValue
    private Integer id;

    private String history;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private CalculationUser createdBy;

    public History() {
    }

    public History(String history, Date date, CalculationUser createdBy) {
        this.history = history;
        this.date = date;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CalculationUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CalculationUser createdBy) {
        this.createdBy = createdBy;
    }
}
