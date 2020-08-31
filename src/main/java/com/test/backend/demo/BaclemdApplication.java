package com.test.backend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BaclemdApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(BaclemdApplication.class, args);
		Interface definingTravel = new Interface();
		definingTravel.definedTrip();

	}


}
