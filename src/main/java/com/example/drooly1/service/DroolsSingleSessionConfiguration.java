package com.example.drooly1.service;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.drooly1.service")
public class DroolsSingleSessionConfiguration {

    public static final String drlFile = "SALES_GOAL_ALLOCATION.drl";

    @Bean
    @Qualifier("singleSessionBean")
    public KieContainer kieContainerSingle() {
        // Register the module into a new KIE container in the KIEServices
        return kieServices().newKieContainer(kieModule().getReleaseId());

    }


    @Bean
    public KieServices kieServices(){
        return KieServices.Factory.get();
    }

    @Bean
    public KieModule kieModule() {
        //Configure the KieFileSystem
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem);

        kieBuilder.buildAll();

        //Create a Kie Module
        KieModule kieModule = kieBuilder.getKieModule();

        return kieModule;
    }

}
