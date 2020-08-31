package com.demo;

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

    public void settingLine(String source, String destination, String price){
        if(source != null && destination != null && price != null) {
            Line line = new Line();
            settingLineSource(line, source);
            settingLineDestination(line, destination);
            line.setPrice(Integer.parseInt(price));
            lines.add(line);
            graph.setLines(lines);
        }else{
            log.info("Some line in the csv file has invalid value");
        }
    }

    public void settingLineSource(Line line, String source){
        Vertex addVertex = findCity(source);
        if(addVertex != null){
            line.setSource(addVertex);
        }else{
            line.setSource(settingCity(source));
        }
    }
    public void settingLineDestination(Line line, String destination){
        Vertex addVertex = findCity(destination);
        if(findCity(destination) != null){
            line.setDestination(addVertex);
        }else{
            line.setDestination(settingCity(destination));
        }
    }

    public Vertex findCity(String row){
        return vertexes.stream().filter(vertex -> row.equals(vertex.getCity())).findAny().orElse(null);
    }

    public Vertex settingCity(String row){
        Vertex vertex = new Vertex();
        vertex.setCity(row);
        vertexes.add(vertex);
        graph.setVertexes(null);
        graph.setVertexes(vertexes);
        return vertex;
    }
}
