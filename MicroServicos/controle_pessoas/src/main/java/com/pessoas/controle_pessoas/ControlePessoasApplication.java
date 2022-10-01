package com.pessoas.controle_pessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class ControlePessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlePessoasApplication.class, args);
	}

}
