package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demospringboot.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmailAndPassword(String email, String password);
}