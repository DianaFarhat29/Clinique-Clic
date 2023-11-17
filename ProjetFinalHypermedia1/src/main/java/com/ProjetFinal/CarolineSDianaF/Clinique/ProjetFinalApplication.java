package com.ProjetFinal.CarolineSDianaF.Clinique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.ProjetFinal.CarolineSDianaF.Models"})
public class ProjetFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFinalApplication.class, args);
	}

}
