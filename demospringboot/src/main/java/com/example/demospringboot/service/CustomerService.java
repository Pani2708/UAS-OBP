package com.example.demospringboot.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Customer;
import com.example.demospringboot.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer obj) {
        return customerRepository.save(obj);
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer updateCustomer(long id, Customer obj) {
        return customerRepository.save(obj);
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomer(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
}