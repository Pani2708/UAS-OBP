package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.DetailMenu;

public interface DetailMenuRepository extends JpaRepository<DetailMenu, Long> {
}