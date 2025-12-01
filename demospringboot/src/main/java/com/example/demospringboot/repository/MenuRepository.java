package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.example.demospringboot.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByIdMenu(String idMenu);
    @Query("SELECT DISTINCT m FROM Menu m")
    List<Menu> findAllDistinct();

}