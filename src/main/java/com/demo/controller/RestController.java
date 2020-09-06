package com.demo.controller;

import com.demo.model.Graph;
import com.demo.model.PostBody;
import com.demo.model.Vertex;
import com.demo.business.CsvHandler;
import com.demo.business.PopulatingTheModels;
import com.demo.business.RoutesService;
import com.demo.response.Route;
import com.demo.response.WrongEntryException;
import com.sun.istack.internal.NotNull;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@Controller
@RequestMapping(value = "/")
public class RestController {

    @Value("${fileName}")
    String nameFile;

    @PostMapping(value = "/")
    @ExceptionHandler(WrongEntryException.class)
    public ResponseEntity<?> addRoute(
            @RequestBody @NotNull PostBody body) {

        CsvHandler csvHandler = new CsvHandler();
        try {
            csvHandler.writeLine(nameFile, body);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/")
    public ResponseEntity<?> bestRoute(
            @RequestParam @NotNull String source,
            @RequestParam @NotNull String destination){

        RoutesService routesService = new RoutesService();
        Graph graph = routesService.graph(nameFile);

        PopulatingTheModels populating = new PopulatingTheModels();
        Vertex sourceVertex = populating.findCity(graph, source);
        Vertex destinationVertex = populating.findCity(graph, destination);

        Pair<LinkedList<Vertex>, Integer> path = routesService.path(graph,sourceVertex,destinationVertex);

        Route route = routesService.routeBuilder(path,destination);
        return ResponseEntity.ok(route);
    }

}
