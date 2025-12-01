package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class DetailMenu extends Menu {
    private String description;
    private Integer price;
    private String status;

    public DetailMenu() {
    }

    public DetailMenu(String idMenu, String menuList, String category, String description, Integer price, String status) {
        super(idMenu, menuList, category);
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}