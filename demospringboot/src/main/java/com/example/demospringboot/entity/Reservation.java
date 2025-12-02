package com.example.demospringboot.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personName;
    private LocalDate tanggal;
    private LocalTime jam;
    private int jumlah;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Reservation() {
    }

    public Reservation(Long id, String personName, LocalDate tanggal, LocalTime jam, Integer jumlah) {
        this.id = id;
        this.personName = personName;
        this.tanggal = tanggal;
        this.jam = jam;
        this.jumlah = jumlah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonName(String personName) {
    this.personName = personName;
    }

    public String getPersonName() {
    return personName;
    }

    public LocalDate getTanggal() {
    return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
    this.tanggal = tanggal;
    }

    public LocalTime getJam() {
    return jam;
    }

    public void setJam(LocalTime jam) {
    this.jam = jam;
    }

    public int getJumlah() {
    return jumlah;
    }

    public void setJumlah(int jumlah) {
    this.jumlah = jumlah;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
