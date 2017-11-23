package edu.mum.cs544.wind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ImportResource({"classpath:springsecurity.xml","classpath:springemailconfig.xml"})
@EnableAsync
public class ProofOfConceptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProofOfConceptApplication.class, args);
    }
}
