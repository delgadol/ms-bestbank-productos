package com.bestbank.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsBestbankProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBestbankProductosApplication.class, args);
	}
	

}
