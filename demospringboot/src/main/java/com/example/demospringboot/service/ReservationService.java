package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Customer;
import com.example.demospringboot.entity.Reservation;
import com.example.demospringboot.repository.ReservationRepository;
import com.example.demospringboot.repository.CustomerRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repo;
    @Autowired
    private CustomerRepository customerRepository;

    public void saveReservation(Reservation r) {
        repo.save(r);
    }

    public List<Reservation> getHistoryByCustomer(Long customerId) {
        return repo.findByCustomerId(customerId);
    }

    public void saveProfile(Customer c) {
        customerRepository.save(c);
    }

}
