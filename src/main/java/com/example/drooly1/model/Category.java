package com.example.drooly1.model;

import java.math.BigDecimal;
import java.util.List;

public class Category {
    Long id;
    String name;
    Employee manager;
    List<Item> items;
    BigDecimal allocated;
    Double allocationPortion;

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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getAllocated() {
        return allocated;
    }

    public void setAllocated(BigDecimal allocated) {
        this.allocated = allocated;
    }

    public Double getAllocationPortion() {
        return allocationPortion;
    }

    public void setAllocationPortion(Double allocationPortion) {
        this.allocationPortion = allocationPortion;
    }
}
