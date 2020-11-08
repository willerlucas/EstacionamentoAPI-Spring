package io.github.willerlucas.estacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EstacionamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoApplication.class, args);
	}

}
