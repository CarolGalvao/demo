package com.demo.business;

import com.demo.model.Graph;
import com.demo.model.PostBody;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Slf4j
@Component
public class CsvHandler {

    PopulatingTheModels populating = new PopulatingTheModels();

    public Graph buildGraph(String fileName) {
        try {
            return populateGraph(readFile(fileName));
        }
        catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return new Graph();
        }
    }

    public Graph populateGraph(List<String[]> allData){
        for (String[] row : allData) {
           populating.addGraphEntry(row[0], row[1], row[2]);
        }
        return populating.graph;
    }

    public List<String[]> readFile(String fileName) throws IOException, CsvException {
        File file = new ClassPathResource(fileName).getFile();

        FileReader fileReader = new FileReader(file);
        CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
        return csvReader.readAll();
    }

    public void writeLine(String pathName, PostBody postBody) throws IOException, CsvException {
        List<String[]> alldata = readFile(pathName);
        File pathNames = new ClassPathResource(pathName).getFile();
        FileWriter outputfile = new FileWriter(pathNames);
        CSVWriter csvWriter = new CSVWriter(outputfile);
        for (String[] row : alldata) {csvWriter.writeNext(row);}
        csvWriter.writeNext(postBody.toString().split(","));
        csvWriter.close();

    }

    public Graph readingCsvFile(String fileName){
        Graph graph = buildGraph(fileName);
        System.out.println(graph);
        if(graph == null){
            log.info("Erro ao ler o arquivo csv");
            return new Graph();
        }
        return graph;
    }

}
