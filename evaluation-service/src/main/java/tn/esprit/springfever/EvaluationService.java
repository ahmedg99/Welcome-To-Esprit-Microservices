package tn.esprit.springfever;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.springfever.Services.Implementation.ServiceClaimsImpl;
import tn.esprit.springfever.Services.Interfaces.IServiceClaims;

@SpringBootApplication // ( @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan )
@EnableSpringConfigured
@EntityScan (basePackages = {"tn.esprit.springfever.entities"})
@EnableEurekaClient
@EnableCaching
@EnableScheduling
@EnableBatchProcessing


public class EvaluationService {
    public static void main(String[] args) {
        SpringApplication.run(EvaluationService.class, args);
    }
}