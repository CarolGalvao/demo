package com.demo.business;

import com.demo.model.Graph;
import com.demo.model.Vertex;
import com.demo.response.Route;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

public class RoutesService {

    public Graph graph(String fileName){
        CsvHandler csvHandler = new CsvHandler();
        return  csvHandler.readingCsvFile(fileName);
    }

    public Pair<LinkedList<Vertex>, Integer> path (Graph graph, Vertex source, Vertex destination) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        PopulatingTheModels populating = new PopulatingTheModels();
        if(populating.findCity(graph,source.getCity()) == null ||
                populating.findCity(graph,destination.getCity()) == null){
            return null;
        }
        return dijkstraAlgorithm.callingDijkstraAlgorithm(graph, source, destination);
    }

    public Route routeBuilder (Pair<LinkedList<Vertex>, Integer>  path, String destination){
        StringBuilder route = new StringBuilder();
        if(path == null || path.getKey() == null){
            return new Route("There is no path");
        }
        route.append("Route: ");
        for (Vertex vertex : path.getKey()) {
            route.append(" ").append(vertex.getCity()).append(" ");
            if(vertex.getCity().equals(destination)){
                route.append(" > $").append(path.getValue());
            }else{
                route.append("-");
            }
        }
        return Route.builder().routeResponse(route.toString()).build();

    }
}
