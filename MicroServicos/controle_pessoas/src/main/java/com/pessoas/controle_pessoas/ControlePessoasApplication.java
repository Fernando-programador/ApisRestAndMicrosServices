package com.pessoas.controle_pessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ControlePessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlePessoasApplication.class, args);
	}

}
