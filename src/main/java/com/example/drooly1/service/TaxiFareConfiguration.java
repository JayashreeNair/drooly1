package com.example.drooly1.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.drooly1.service")
public class TaxiFareConfiguration {

//    public static final String drlFile = "SALES_GOAL_ALLOCATION.drl";

    @Bean
    public KieContainer kieContainer() {
//        // Register the module into a new KIE container in the KIEServices
//        KieServices kieServices = kieServices();
//        kieServices.getRepository().addKieModule(kieModule());
//
//        return kieServices().newKieContainer(kieModule().getReleaseId());

        return KieServices.Factory.get().getKieClasspathContainer();
    }


//    @Bean
//    public KieServices kieServices(){
//        // Get the KieServices
//        return KieServices.Factory.get();
//    }

//    @Bean
//    public KieModule kieModule() {
//        //Configure the KieFileSystem
//        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
//        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
//        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem);
//
//
//        kieBuilder.buildAll();
//
//        //Create a Kie Module
//        KieModule kieModule = kieBuilder.getKieModule();
//
//        // Register the module into a new KIE container in the KIEServices
//        return kieModule;
//    }

}
