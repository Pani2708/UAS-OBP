package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}