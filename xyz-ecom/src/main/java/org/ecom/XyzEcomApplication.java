package org.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XyzEcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyzEcomApplication.class, args);
	}

}
