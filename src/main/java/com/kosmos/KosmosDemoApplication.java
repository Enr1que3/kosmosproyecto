package com.kosmos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com.kosmos")
@EntityScan(basePackages = "com.kosmos.entity")
//s@SpringBootApplication
public class KosmosDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KosmosDemoApplication.class, args);
	}

}
