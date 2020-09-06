package com.demo.business;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerHandler {

    public String [] scanningRoute(Scanner sc){
        System.out.print("please enter the route: ");
        String trip = sc.next();
        return trip.split("-");
    }


}
