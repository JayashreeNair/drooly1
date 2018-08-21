package com.example.drooly1.service;

import com.example.drooly1.model.*;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaxiFareCalculatorService {

    @Autowired
    private KieContainer kContainer;

    public Long calculateFare(List<TaxiRide> taxiRides, Fare rideFare) {
        KieSession kieSession = kContainer.newKieSession();

        kieSession.setGlobal("rideFare", rideFare);
        for(TaxiRide taxiRide: taxiRides){
            kieSession.insert(taxiRide);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("!! RIDE FARE !! " + rideFare.getTotalFare());
        return rideFare.getTotalFare();
    }

    public Department budget(Department department, SalesGoal salesGoal) {
        KieSession kieSession = kContainer.newKieSession("departmentRuleSession");
        kieSession.setGlobal("goal", salesGoal);
        kieSession.insert(department);
        kieSession.fireAllRules();
        kieSession.dispose();

        kieSession = kContainer.newKieSession("categoryRuleSession");
//        kieSession.addEventListener();
        for(Category category: department.getCategories()){
            kieSession.insert(category);
            kieSession.setGlobal("itemCount",category.getItems().size());
        }
//        kieSession.insert(department);
        kieSession.fireAllRules();
        kieSession.dispose();
        return department;
    }

    public KieSession getKieSession(){
        return kContainer.newKieSession();
    }
}
