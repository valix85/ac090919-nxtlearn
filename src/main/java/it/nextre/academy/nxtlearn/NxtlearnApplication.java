package it.nextre.academy.nxtlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// https://www.javacodegeeks.com/2019/09/springbootconfiguration-annotation-spring-boot.html
@SpringBootApplication
public class NxtlearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(NxtlearnApplication.class, args);
	}

}

// todo prevedere il caricamento dei dati di prova se il repository è vuoto
