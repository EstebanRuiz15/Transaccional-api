package com.emazon.transaccional_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.emazon.transaccional_api.infraestructure.feign")
public class TransaccionalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransaccionalApiApplication.class, args);
	}

}
