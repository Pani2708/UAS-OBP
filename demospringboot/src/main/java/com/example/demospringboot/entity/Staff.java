package com.example.demospringboot.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Entity
public class Staff extends Person {
    @NotNull
    @Size(max = 30)
    private String position;

    @Column(precision = 15, scale = 2)
    private BigDecimal salary;

    public Staff() {
    }

    public Staff(String kode, String personName, String email, String password, String position, BigDecimal salary) {
        super(kode, personName, email, password);
        this.position = position;
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}