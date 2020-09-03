package com.demo.utils;

import com.demo.model.Graph;
import com.demo.model.Line;
import com.demo.model.Vertex;
import javafx.util.Pair;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Stream;

@AllArgsConstructor
public class DijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Line>  lines;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> prices;

    public DijkstraAlgorithm(Graph graph) {
    }

    public Pair<LinkedList<Vertex>, Integer> callingDijkstraAlgorithm(Graph graph, Vertex source, Vertex destination ){
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.lines = new ArrayList<Line>(graph.getLines());
        execute(source);
        LinkedList<Vertex> path = getPath(destination);
        Integer pathPrice = getRoutePrice(path);
        return new Pair<>(path,pathPrice);

    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        prices = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        prices.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinPrice(node);
        }
    }

    private void findMinPrice(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getLowestPrice(target) > getLowestPrice(node)
                    + getPrices(node, target)) {
                prices.put(target, getLowestPrice(node)
                        + getPrices(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getPrices(Vertex node, Vertex target) {
        for (Line line : lines) {
            if (line.getSource().equals(node)
                    && line.getDestination().equals(target)) {
                return line.getPrice();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Line line : lines) {
            if (line.getSource().equals(node)
                    && !isSettled(line.getDestination())) {
                neighbors.add(line.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getLowestPrice(vertex) < getLowestPrice(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getLowestPrice(Vertex destination) {
        Integer d = prices.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public int getRoutePrice (LinkedList<Vertex> path){
        int bestRoutePrice = 0;
        for(int i = 0 ; i< path.size() - 1; i++){
            Vertex current = path.get(i);
            Vertex next = path.get(i+1);
            Stream<Line> lineRoutes = lines.stream().filter(line -> current.equals(line.getSource()) && next.equals(line.getDestination()));
            bestRoutePrice += lineRoutes.min(Comparator.comparing(Line::getPrice)).get().getPrice();
        }
        return bestRoutePrice;
    }
}
