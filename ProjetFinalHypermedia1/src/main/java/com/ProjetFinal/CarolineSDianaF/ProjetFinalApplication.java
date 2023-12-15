package com.ProjetFinal.CarolineSDianaF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.ProjetFinal.CarolineSDianaF.Models"})
public class ProjetFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFinalApplication.class, args);
	}
}
