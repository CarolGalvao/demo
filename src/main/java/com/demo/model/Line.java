package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Line {

    private Vertex source;
    private Vertex destination;
    private int price;

    public Line() {

    }
}
