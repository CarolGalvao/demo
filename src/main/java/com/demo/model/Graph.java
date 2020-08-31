package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Graph {

    private List<Vertex> vertexes;
    private List<Line> lines;

    public Graph() {

    }
}
