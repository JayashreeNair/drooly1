package com.example.drooly1.service;

import com.example.drooly1.model.*;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class TaxiFareCalculatorService {

    @Autowired
    @Qualifier("singleSessionBean")
    private KieContainer kContainerSingle;

    @Autowired
    @Qualifier("multipleSessionBean")
    private KieContainer kContainerMultiple;


    public Department budgetWithSingleSession(Department department, SalesGoal salesGoal) {
        long startTime = System.nanoTime();
        KieSession kieSession = kContainerSingle.newKieSession();
        kieSession.setGlobal("goal", salesGoal);
        kieSession.insert(department);
        kieSession.fireAllRules();
        kieSession.dispose();
        long endTime = System.nanoTime();

        System.out.println("********************************** Single Session Statistics Start **********************************************");
        System.out.println("Sales Goal : "+salesGoal.getGoal());
        department.getCategories().forEach(
                category ->
                        System.out.println("Category "+ category.getName()+" has "+ category.getItems().size())
        );
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


        department.getCategories().forEach(category -> {
            System.out.println("Category : "+category.getName() +" gets "+ category.getAllocated());
            category.getItems().stream().limit(10).forEach(item ->
                            System.out.println("Item "+item.getName()+" gets "+item.getAlloction()));
                }
        );

        System.out.println("*********************************** Single Session Statistics End *********************************************");
        return department;
    }

    public Department budgetWithMultipleSession(Department department, SalesGoal salesGoal) {
        long startTime = System.nanoTime();

        KieSession kieSession = kContainerMultiple.newKieSession("departmentRuleSession");
        kieSession.setGlobal("goal", salesGoal);
        kieSession.insert(department);
        kieSession.fireAllRules();
        kieSession.dispose();

//        kieSession.addEventListener();
        for(Category category: department.getCategories()){
            kieSession = kContainerMultiple.newKieSession(category.getRuleSessionName());
            kieSession.insert(category);
            kieSession.setGlobal("itemCount",category.getItems().size());
            kieSession.fireAllRules();
            kieSession.dispose();
        }
//        kieSession.insert(department);
        long endTime = System.nanoTime();

        System.out.println("********************************** Multiple Session Statistics Start **********************************************");
        System.out.println("Sales Goal : "+salesGoal.getGoal());
        department.getCategories().forEach(
                category ->
            System.out.println("Category "+ category.getName()+" has "+ category.getItems().size())
        );
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


        department.getCategories().forEach(category -> {
            System.out.println("Category : "+category.getName() +" gets "+ category.getAllocated());
            category.getItems().stream().limit(10).forEach(item ->
                            System.out.println("Item "+item.getName()+" gets "+item.getAlloction()));
                }
        );

        System.out.println("***********************************Multiple Session Statistics End *********************************************");
        return department;
    }

}
