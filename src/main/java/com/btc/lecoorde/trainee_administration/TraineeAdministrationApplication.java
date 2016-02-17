package com.btc.lecoorde.trainee_administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@ImportResource(value = "beans.xml")
@ComponentScan({"com.btc.lecoorde.trainee_administration"})
public class TraineeAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeAdministrationApplication.class, args);
	}
}
