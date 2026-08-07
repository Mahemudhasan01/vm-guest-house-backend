package com.vm.guesthouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuesthouseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuesthouseManagementApplication.class, args);
	}

}
