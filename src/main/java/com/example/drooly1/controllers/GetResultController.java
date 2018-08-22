package com.example.drooly1.controllers;

import com.example.drooly1.model.*;
import com.example.drooly1.service.TaxiFareCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
        category1.setRuleSessionName("frozenRuleSession");

        for (int i = 0; i < noOfFrozenItems; i++) {
            Item item = new Item();
            item.setName("Item" + i);
            category1.getItems().add(item);
        }
        Category category2 = new Category();
        category2.setName("MEAT");
        category2.setItems(new ArrayList<>( ));
        category2.setRuleSessionName("meatRuleSession");
        for (int i = 0; i < noOfFrozenItems; i++) {
            Item item = new Item();
            item.setName("Meat" + i);
            category2.getItems().add(item);
        }
        category2.setAllocationPortion(Double.valueOf(60));
        department.setCategories(Arrays.asList(category1, category2));

        SalesGoal salesGoal = new SalesGoal();
        salesGoal.setGoal(new BigDecimal(3500000));
        taxiFareCalculatorService.budgetWithMultipleSession(department, salesGoal);
        taxiFareCalculatorService.budgetWithSingleSession(department,salesGoal);
    }
}
