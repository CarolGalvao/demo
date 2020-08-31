package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Route {
    public String departureLocation;
    public String arrivalLocation;
    public String price;

    public Route() {

    }
}
