package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Reservation;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findTopByCustomer_IdOrderByTanggalDesc(Long customerId);

    List<Reservation> findByCustomerId(Long customerId);
}