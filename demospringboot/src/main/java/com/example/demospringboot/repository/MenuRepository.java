package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByIdMenu(String idMenu);
}