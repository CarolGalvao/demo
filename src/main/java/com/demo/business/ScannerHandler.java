package com.demo.business;

import com.demo.model.Graph;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerHandler {

    public String [] scanningRoute(Scanner sc){
        System.out.print("please enter the route: ");
        String trip = sc.next();
        return trip.split("-");
    }

    public Pair<Boolean, String []> isValidInput(String [] input, Graph graph){
        if(input!= null && input.length == 2 ){
            PopulatingTheModels populating = new PopulatingTheModels();
            input[0] = input[0].toUpperCase();
            input[1] = input[1].toUpperCase();
            if(populating.findCity(graph, input[0]) != null && populating.findCity(graph, input[1]) != null){
                return new Pair<>(true, input);
            }
        }
        return new Pair<>(false, null);
    }


}
