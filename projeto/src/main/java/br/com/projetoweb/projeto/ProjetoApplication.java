package br.com.projetoweb.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//Anotação para desatiar o bloqueio dos endpoints - trocar depois
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

}
