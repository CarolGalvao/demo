package com.demo.utils;

import com.demo.model.Graph;
import com.demo.model.Vertex;
import javafx.util.Pair;

import java.util.LinkedList;

public class RoutesService {

    public Graph graph(String fileName){
        CsvHandler csvHandler = new CsvHandler();
        return  csvHandler.readingCsvFile(fileName);
    }

    public Pair<LinkedList<Vertex>, Integer> path (Graph graph, Vertex source, Vertex destination) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
       return dijkstraAlgorithm.callingDijkstraAlgorithm(graph, source, destination);
    }
}
