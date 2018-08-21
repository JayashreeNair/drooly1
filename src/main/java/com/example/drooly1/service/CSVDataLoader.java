package com.example.drooly1.service;

import com.example.drooly1.model.Category;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collections;
import java.util.List;

@Service
public class CSVDataLoader {

    public CSVDataLoader(){

    }

    @PostConstruct
    public void loadDataToKieSession(){
//        try {
//            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
//            CsvMapper mapper = new CsvMapper();
//            File file = new ClassPathResource("data.csv").getFile();
//            MappingIterator readValues =
//                    mapper.readerFor(Category.class)
//                            .with(bootstrapSchema).readValues(file);
//            List<Category> categories = readValues.readAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
