package com.example.drooly1.model;

import java.math.BigDecimal;

public class Item {
    Long id;
    String name;
    BigDecimal alloction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAlloction() {
        return alloction;
    }

    public void setAlloction(BigDecimal alloction) {
        this.alloction = alloction;
    }


}
