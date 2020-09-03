package com.demo.utils;

import com.demo.model.Graph;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;

@Slf4j
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

    public void writeLine(String pathName, String[] addLine) {
        try{
            if(isValidAddLine(addLine)){
                List<String[]> alldata = readFile(pathName);
                File pathNames = new ClassPathResource(pathName).getFile();
                FileWriter outputfile = new FileWriter(pathNames);
                CSVWriter csvWriter = new CSVWriter(outputfile);
                for (String[] row : alldata) {csvWriter.writeNext(row);}
                csvWriter.writeNext(addLine);
                csvWriter.close();
            }else{
                log.info("Writing is not in the expected format");
            }
        }catch (Exception e) {
            log.error("An error occurred while writing to the file" + pathName, e);
        }
    }

    public boolean isValidAddLine(String[] addLine){
        return addLine != null && addLine.length == 3;
    }

    public Graph readingCsvFile(String fileName){
        CsvHandler csvHandler = new CsvHandler();
        Graph graph = csvHandler.buildGraph(fileName);
        System.out.println(graph);
        if(graph == null){
            log.info("Erro ao ler o arquivo csv");
            return new Graph();
        }
        return graph;
    }

}
