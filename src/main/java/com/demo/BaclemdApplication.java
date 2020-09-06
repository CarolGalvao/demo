package com.demo;

import com.demo.controller.ConsoleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Scanner;

@SpringBootApplication
public class BaclemdApplication {

	public static void main(String[] args){
		ConsoleController console = new ConsoleController();
		SpringApplication sa = new SpringApplication(BaclemdApplication.class);
		Properties properties = new Properties();
		Scanner sc = new Scanner(System.in);
		System.out.print("File name:");
		String fileInput = sc.next();
		properties.setProperty("fileName", fileInput);
		sa.setDefaultProperties(properties);
		sa.run(args);
		console.callConsoleController(fileInput);
	}


}
