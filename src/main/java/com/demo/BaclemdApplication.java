package com.demo;

import com.demo.controller.ConsoleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaclemdApplication {

	public static void main(String[] args){
		SpringApplication.run(BaclemdApplication.class, args);
		ConsoleController definingTravel = new ConsoleController();
		definingTravel.choseRoute();

	}


}
