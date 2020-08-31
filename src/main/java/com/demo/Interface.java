package com.demo;

import com.demo.model.Graph;
import com.demo.model.Vertex;

import java.util.Scanner;

public class Interface {

    PopulatingTheModels populating = new PopulatingTheModels();
    DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();

    public void definedTrip(){
        CsvHandler csvHandler = new CsvHandler();
        Graph graph = csvHandler.loadObjectList("input-routes.csv");
        System.out.println(graph);

        Scanner sc = new Scanner(System.in);
        System.out.print("please enter the route: ");
        String trip = sc.next();
        String [] input = trip.split("-");
        String departure = input[0];
        String arrival = input[1];

        System.out.println("Origem: " + departure + " Destino: " + arrival);

        Vertex source = findCity(graph, departure);
        Vertex destination = findCity(graph, arrival);

        dijkstraAlgorithm.callingDijkstraAlgorithm(graph, source, destination);
    }

    public void showResponse(){
        System.out.print("best route: ");

    }

    public Vertex findCity(Graph graph, String city){
        if(graph.getVertexes() != null) {
            return graph.getVertexes().stream().filter(vertex -> city.equals(vertex.getCity())).findAny().orElse(null);
        }
        return null;
    }
}
