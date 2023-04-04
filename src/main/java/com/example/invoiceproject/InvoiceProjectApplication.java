package com.example.invoiceproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
public class InvoiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceProjectApplication.class, args);
	}

}
