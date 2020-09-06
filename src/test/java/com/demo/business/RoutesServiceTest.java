//package com.demo.business;
//
//import com.demo.model.Graph;
//import com.demo.model.Vertex;
//import javafx.util.Pair;
//import junitparams.JUnitParamsRunner;
//import org.hamcrest.MatcherAssert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.LinkedList;
//
//@RunWith(JUnitParamsRunner.class)
//public class RoutesServiceTest {
//
//    @Test
//    public void graphOk(){
//        RoutesService routesService = new RoutesService();
//        Graph graph = routesService.graph("input-file.txt");
//        MatcherAssert.assertThat("Must have a populated graph",
//                (graph != null && graph.getLines() != null && graph.getVertexes() != null));
//    }
//
//    @Test
//    public void graphWithoutAValidFile(){
//        RoutesService routesService = new RoutesService();
//        Graph graph = routesService.graph("");
//        MatcherAssert.assertThat("Must not have a populated graph",
//                (graph != null && graph.getLines() == null && graph.getVertexes() == null));
//        //TO DO - FileNotFoundException
//    }
//
//    @Test
//    public void graphEmptyFile(){
//        RoutesService routesService = new RoutesService();
//        Graph graph = routesService.graph("empty-file.txt");
//        MatcherAssert.assertThat("Must not have a populated graph",
//                (graph != null && graph.getLines() == null && graph.getVertexes() == null));
//    }
//
//    @Test
//    public void pathOk(){
//        RoutesService routesService = new RoutesService();
//        PopulatingTheModels populating = new PopulatingTheModels();
//        Graph graph = routesService.graph("input-file.txt");
//        Vertex source = populating.findCity(graph, "GRU");
//        Vertex destination = populating.findCity(graph, "ORL");
//        Pair<LinkedList<Vertex>, Integer> pairPath = routesService.path(graph, source, destination);
//        MatcherAssert.assertThat("Must be complited",
//                (pairPath.getKey() != null && pairPath.getKey().size() >= 2 && pairPath.getValue() != null));
//    }
//
////    @Test
////    public void pathNotExist(){
////        //Look for this case em dijksta
////        RoutesService routesService = new RoutesService();
////        PopulatingTheModels populating = new PopulatingTheModels();
////        Graph graph = routesService.graph("input-file.txt");
////        Vertex source = populating.findCity(graph, "EEE");
////        Vertex destination = populating.findCity(graph, "FLW");
////        Pair<LinkedList<Vertex>, Integer> pairPath = routesService.path(graph, source, destination);
////        MatcherAssert.assertThat("Must be complited",
////                (pairPath.getKey() != null && pairPath.getKey().size() == 0 && pairPath.getValue() != null));
////    }
//
//}
