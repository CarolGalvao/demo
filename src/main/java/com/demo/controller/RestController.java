package com.demo.controller;

import com.demo.model.Graph;
import com.demo.model.PostBody;
import com.demo.model.Vertex;
import com.demo.utils.CsvHandler;
import com.demo.utils.PopulatingTheModels;
import com.demo.utils.RoutesService;
import com.sun.istack.internal.NotNull;
import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedList;

@Controller
@RequestMapping(value = "/")
public class RestController {

    @PostMapping(value = "/")
    public ResponseEntity<?> addRoute(
            @RequestBody @NotNull PostBody body) throws IOException {

        System.out.println(body.getSource() + body.getDestination() + body.getPrice());
        ConsoleController definingTravel = new ConsoleController();
        String [] newLine = {body.getSource(), body.getDestination(), body.getPrice()};
        CsvHandler csvHandler = new CsvHandler();
        csvHandler.writeLine("input-file.txt", newLine);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> bestRoute(
            @RequestParam @NotNull String source,
            @RequestParam @NotNull String destination){
        RoutesService routesService = new RoutesService();
        Graph graph = routesService.graph("input-file.txt");

        PopulatingTheModels populating = new PopulatingTheModels();
        Vertex sourceVertex = populating.findCity(graph, source);
        Vertex destinationVertex = populating.findCity(graph, destination);

        Pair<LinkedList<Vertex>, Integer> path = routesService.path(graph,sourceVertex,destinationVertex);

        return ResponseEntity.ok(path);
    }

}
