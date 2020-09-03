package com.demo.utils;

import com.demo.model.Graph;
import com.opencsv.exceptions.CsvException;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class CsvHandlerTest {

    CsvHandler csvHandler = new CsvHandler();

    @Test
    public void buildGraphOk (){
        Graph graph = csvHandler.buildGraph("input-file.txt");
        Assert.assertTrue((graph!= null && graph.getVertexes() != null && graph.getLines() != null));
    }

    @Test
    public void buildGraphFileEmpty (){
        Graph graph = csvHandler.buildGraph("empty-file.txt");
        Assert.assertTrue((graph!= null && graph.getVertexes() == null && graph.getLines() == null));
    }

    @Test
    public void buildGraphNullFile (){
        Graph graph = csvHandler.buildGraph(null);
        Assert.assertTrue((graph!= null && graph.getVertexes() == null && graph.getLines() == null));
        //to do - catch java.lang.IllegalArgumentException
    }

    @Test
    public void readFileOk() throws IOException, CsvException {
        List<String[]> readFile = csvHandler.readFile("test-file.txt");
        Assert.assertArrayEquals(readFile.get(0), new String[]{"test"});

    }

    @Test
    public void readFileEmpty() throws IOException, CsvException {
        List<String[]> readFile = csvHandler.readFile("empty-file.txt");
        Assert.assertTrue(readFile.isEmpty());

    }
}
