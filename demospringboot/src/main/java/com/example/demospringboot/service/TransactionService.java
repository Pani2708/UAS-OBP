package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Transaction;
import com.example.demospringboot.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(Transaction transaction) {
        transaction.updateTotalHarga();
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String idTransaksi) {
        return transactionRepository.findById(idTransaksi);
    }

    public void deleteTransaction(String idTransaksi) {
        transactionRepository.deleteById(idTransaksi);
    }

    public List<Transaction> getHistoryByCustomer(Long customerId) {
        return transactionRepository
                .findAll()
                .stream()
                .filter(t -> t.getCustomer() != null && t.getCustomer().getId().equals(customerId))
                .toList();
    }
}