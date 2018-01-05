package com.thach.example.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

@Entity(name = "HISTORY")
public class History implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private CalculationUser createdBy;

    public History() {
    }

    public History(String content, Date date, CalculationUser createdBy) {
        this.content = content;
        this.date = date;
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCreatedBy(CalculationUser createdBy) {
        this.createdBy = createdBy;
    }
}
