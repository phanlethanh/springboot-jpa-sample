package com.thanhpl.oracle.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class SpringbootOracleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootOracleApplication.class, args);
	}
}
