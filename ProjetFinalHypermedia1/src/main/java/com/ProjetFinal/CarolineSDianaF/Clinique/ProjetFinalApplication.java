package com.ProjetFinal.CarolineSDianaF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.ProjetFinal.CarolineSDianaF.Modeles"})
public class ProjetFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFinalApplication.class, args);
	}

}
