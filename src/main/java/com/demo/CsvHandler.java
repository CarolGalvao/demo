package com.demo;

import com.demo.model.Graph;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Slf4j
public class CsvHandler {

    PopulatingTheModels populating = new PopulatingTheModels();

    public Graph loadObjectList(String fileName) {
        try {
            File pathName = new ClassPathResource(fileName).getFile();
            FileReader filereader = new FileReader(pathName);

            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            List<String[]> allData = csvReader.readAll();
            return consummingData(allData);
        }
        catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return new Graph();
        }
    }

    public Graph consummingData (List<String[]> allData){
        for (String[] row : allData) {
           populating.settingLine(row[0], row[1], row[2]);
        }
        return populating.graph;
    }
}
