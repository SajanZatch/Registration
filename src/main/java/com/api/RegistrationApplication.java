package com.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegistrationApplication {

	public static void main(String[] args) {

		SpringApplication.run(RegistrationApplication.class, args);
	}

	//when dependency injection is not happening or Spring IOC don't know which object to create then we can use this annotion @Bean
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}


}
