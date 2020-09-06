package com.demo.business;

import com.demo.model.Graph;
import com.demo.model.Line;
import com.demo.model.Vertex;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PopulatingTheModels {

    List<Vertex> vertexes = new ArrayList<>();
    List<Line> lines = new ArrayList<>();
    Graph graph = new Graph();

    public void addGraphEntry(String source, String destination, String price){
        if(isValidEntry(source, destination, price)) {
            try{
                Line line = new Line();
                addSourceLine(line, source);
                addDestinationLine(line, destination);
                line.setPrice(Integer.parseInt(price));
                lines.add(line);
                graph.setLines(lines);
            }catch (Exception e){
                log.error("Invalid line");
            }
        }else{
            log.info("Some line in the csv file has invalid value");
        }
    }

    public void addSourceLine(Line line, String source){
        Vertex addVertex = findCity(source);
        if(addVertex != null){
            line.setSource(addVertex);
        }else{
            line.setSource(addVertex(source));
        }
    }
    public void addDestinationLine(Line line, String destination){
        Vertex addVertex = findCity(destination);
        if(findCity(destination) != null){
            line.setDestination(addVertex);
        }else{
            line.setDestination(addVertex(destination));
        }
    }

    public Vertex findCity(String row){
        return vertexes.stream().filter(vertex -> row.equals(vertex.getCity())).findAny().orElse(null);
    }

    public Vertex findCity(Graph graph, String city){
        if(graph.getVertexes() != null) {
            return graph.getVertexes().stream().filter(vertex -> city.equals(vertex.getCity())).findAny().orElse(null);
        }
        return null;
    }

    public Vertex addVertex(String row){
        Vertex vertex = new Vertex();
        vertex.setCity(row);
        vertexes.add(vertex);
        graph.setVertexes(vertexes);
        return vertex;
    }

    public boolean isValidEntry(String source, String destination, String price){
        return isNotEmpty(source) && isNotEmpty(destination) && isNotEmpty(price);
    }

    public boolean isNotEmpty(String entry){
        return entry != null && !entry.isEmpty();
    }
}
