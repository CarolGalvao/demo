package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaclemdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaclemdApplication.class, args);
		Interface definingTravel = new Interface();
		definingTravel.definedTrip();

	}


}