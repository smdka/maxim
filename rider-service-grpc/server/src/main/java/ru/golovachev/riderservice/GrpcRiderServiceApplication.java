package ru.golovachev.riderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GrpcRiderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrpcRiderServiceApplication.class, args);
    }
}
