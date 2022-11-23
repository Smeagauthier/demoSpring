package be.condorcet.demospring;

import be.condorcet.demospring.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {
    @Value("${server.mode}")
    private String mode;

    @Bean
    InterfClientService clientServiceImpl() {
        System.out.println("création du service client en mode : "+mode);
        switch (mode){
            case "PROD" : return new ClientServiceImpl();
            case "STUB" : return new ClientServiceStub();
            case "MOCK" : return new ClientServiceMock();
            default: return new ClientServiceStub();
        }
    }
    @Bean
    InterfComfactService comfactServiceImpl() {
        System.out.println("création du service client en mode : "+mode);
        switch (mode){
            case "PROD" : return new ComfactServiceImpl();
            case "STUB" : return new ComfactServiceStub();
            case "MOCK" : return new ComfactServiceMock();
            default: return new ComfactServiceStub();
        }
    }
}
