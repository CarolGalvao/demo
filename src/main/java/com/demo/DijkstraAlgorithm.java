package com.demo;

import com.demo.model.Graph;
import com.demo.model.Line;
import com.demo.model.Vertex;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class DijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Line>  lines;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithm(Graph graph, List<Line> lines) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.lines = new ArrayList<Line>(graph.getLines());
    }

    public DijkstraAlgorithm(Graph graph) {
    }

    public DijkstraAlgorithm() {

    }

    public void callingDijkstraAlgorithm( Graph graph, Vertex source, Vertex destination ){
        lines = graph.getLines();
        nodes = graph.getVertexes();
        execute(source);
        LinkedList<Vertex> path = getPath(destination);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
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
                    + getDistance(node, target)) {
                distance.put(target, getLowestPrice(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
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
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
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

    private static Line getLowestDistanceNode(List<Line>  lines) {
        Line lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Line node: lines) {
            int nodeDistance = node.getPrice();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
