package com.jokenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JokenpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokenpoApplication.class, args);
	}

}
