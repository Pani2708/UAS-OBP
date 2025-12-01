package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demospringboot.entity.Menu;
import com.example.demospringboot.repository.MenuRepository;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menu")
    public String menuPage() {
        return "menu";
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    public Menu addMenu(Menu obj) {
        Long id = null;
        obj.setId(id);
        return menuRepository.save(obj);
    }

    public Menu getMenuById(long id) {
        return menuRepository.findById(id).orElse(null);
    }

    public Menu updateMenu(Long id, Menu obj) {
        obj.setId(id);
        return menuRepository.save(obj);
    }

    public void deleteMenu(long id) {
        menuRepository.deleteById(id);
    }

}