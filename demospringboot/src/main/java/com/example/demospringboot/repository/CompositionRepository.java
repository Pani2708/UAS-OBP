package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Composition;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
}