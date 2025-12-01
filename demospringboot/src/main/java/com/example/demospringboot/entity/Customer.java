package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Person implements ReservationInterface {

    private String phoneNo;
    private String address;

    public Customer() {
    }

    public Customer(String kode, String personName, String email, String password, String phoneNo, String address) {
        super(kode, personName, email, password);
        this.phoneNo = phoneNo;
        this.address = address;
    }

    @Override
    public void reservation() {
        System.out.println("Customer melakukan reservasi!");
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
