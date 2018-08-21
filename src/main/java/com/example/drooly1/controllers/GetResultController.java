package com.example.drooly1.controllers;

import com.example.drooly1.model.*;
import com.example.drooly1.service.TaxiFareCalculatorService;
import com.example.drooly1.service.TaxiFareConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


@RestController
public class GetResultController {

    @Autowired
    TaxiFareCalculatorService taxiFareCalculatorService;

    @GetMapping("drools")
    public void getDroolsResult() {
        long noOfFrozenItems = 1400000;

//        ApplicationContext context = new AnnotationConfigApplicationContext(TaxiFareConfiguration.class);
//        TaxiFareCalculatorService taxiFareCalculatorService = context.getBean(TaxiFareCalculatorService.class);
        Department department = new Department();
        department.setName("GROCERY");

        Category category1 = new Category();
        category1.setName("FROZEN");
        category1.setAllocationPortion(Double.valueOf(30));
        category1.setItems(new ArrayList<>());
        for (int i = 0; i < noOfFrozenItems; i++) {
            Item item = new Item();
            item.setName("Item" + i);
            category1.getItems().add(item);
        }
        Category category2 = new Category();
        category2.setName("MEAT");
        category2.setItems(new ArrayList<>( ));
        for (int i = 0; i < noOfFrozenItems; i++) {
            Item item = new Item();
            item.setName("Meat" + i);
            category2.getItems().add(item);
        }
        category2.setAllocationPortion(Double.valueOf(60));
        department.setCategories(Arrays.asList(category1, category2));

        SalesGoal salesGoal = new SalesGoal();
        salesGoal.setGoal(new BigDecimal(140000));
        long startTime = System.nanoTime();

        taxiFareCalculatorService.budget(department, salesGoal);

        long endTime = System.nanoTime();
        System.out.println("********************************************************************************");
        System.out.println("Number of items in Category FROZEN "+noOfFrozenItems);
        System.out.println("Time taken " + TimeUnit.NANOSECONDS.toSeconds(endTime - startTime) +" seconds");
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb +" MB");

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb +" MB");

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb +" MB");

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb +" MB");

        System.out.println("********************************************************************************");

        department.getCategories().forEach(category -> {
            System.out.println("Category : "+category.getName() +" gets "+ category.getAllocated());
            category.getItems().stream().limit(10).forEach(item ->
                System.out.println("Item "+item.getName()+" gets "+item.getAlloction()));
            }
        );




    }
}
