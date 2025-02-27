package com.demo.controller;

import com.demo.business.PopulatingTheModels;
import com.demo.business.RoutesService;
import com.demo.business.ScannerHandler;
import com.demo.model.Graph;
import com.demo.model.Vertex;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.Scanner;

@Slf4j
@Controller
@Configurable
public class ConsoleController {

    @Autowired
    ScannerHandler scannerHandler;

    public void callConsoleController(String nameFile){
        RoutesService routesService = new RoutesService();
        Graph graph = routesService.graph(nameFile);
        Scanner sc = new Scanner(System.in);
        wantToInsertRoute(sc, graph, routesService);
    }

    public void wantToInsertRoute(Scanner sc, Graph graph, RoutesService routesService){
        boolean wantInsert = true;
        while (wantInsert) {
            System.out.print("Do you want to insert a new route? (Y/N): ");
            String wantInsertAnswer = sc.next();
            if (wantInsertAnswer.contains("Y") || wantInsertAnswer.contains("N")) {
                if(wantInsertAnswer.contains("N")){
                    System.out.println("OK, If you want you can use the RestController");
                    wantInsert=false;
                }else{
                    String [] input = scannerHandler.scanningRoute(sc);
                    Pair<Boolean, String []>  isValid = scannerHandler.isValidInput(input, graph);
                    if(isValid.getKey().equals(true)){
                        dealingWithTheRoute(isValid.getValue(), graph, routesService);
                    }else{System.out.println("invalid route, try again");}
                }
            }else{System.out.println("invalid response, try again");}

        }
    }

    public void dealingWithTheRoute(String [] input, Graph graph, RoutesService routesService){
        PopulatingTheModels populating = new PopulatingTheModels();
        Vertex source = populating.findCity(graph, input[0]);
        Vertex destination = populating.findCity(graph, input[1]);

        Pair<LinkedList<Vertex>, Integer> path = routesService.path(graph,source,destination);

        if(path == null || destination.getCity() == null || path.getKey() == null){
            System.out.println("there is no way.");
            return;
        }
        showResponse(path,destination.getCity());
    }

    public void showResponse( Pair<LinkedList<Vertex>, Integer>  path, String destination){
        System.out.print("best route: ");
        for (Vertex vertex : path.getKey()) {
            System.out.print(" " + vertex.getCity() + " ");
            if(vertex.getCity().equals(destination)){
                System.out.print(" > $");
                System.out.println(path.getValue());
            }else{
                System.out.print("-");
            }
        }
    }
}
