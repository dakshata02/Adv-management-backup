package com.capgemini.advertisement.adv.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.capgemini.advertisement")
@EntityScan(basePackages = "com.capgemini.advertisement.entity")
@EnableJpaRepositories(basePackages = "com.capgemini.advertisement.dao")
public class AdvRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvRestApplication.class, args);
	}

}
