package com.example.drooly1.service;

import com.example.drooly1.model.Fare;
import com.example.drooly1.model.TaxiRide;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;


public class TaxiFareCalculatorServiceTest {

    TaxiFareCalculatorService service;

    @Before
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DroolsMultipleSessionConfiguration.class);
        service = context.getBean(TaxiFareCalculatorService.class);
    }

    @Test
    public void calculateFare_forOnlyOneTaxiRide() {
        service.calculateFare(Arrays.asList(new TaxiRide()), new Fare());
    }

    @Test
    public void calculateFare_forOnlyMultipleTaxiRides() {
        TaxiRide taxiRide1 = new TaxiRide();
        taxiRide1.setDistanceInMile(5L);
        taxiRide1.setIsNightSurcharge(true);

        TaxiRide taxiRide2 = new TaxiRide();
        taxiRide2.setDistanceInMile(15L);
        taxiRide2.setIsNightSurcharge(true);

        List<TaxiRide> rides = Arrays.asList(
//                taxiRide1,
                taxiRide2);
        service.calculateFare(rides,new Fare());
    }

}