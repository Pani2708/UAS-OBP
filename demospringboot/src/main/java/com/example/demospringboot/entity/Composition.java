package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class Composition extends Menu {
    private String ingredient;
    
    

    public Composition() {
    }

    public Composition(String idMenu, String menuList, String category, String ingredient) {
        super(idMenu, menuList, category);
        this.ingredient = ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

}