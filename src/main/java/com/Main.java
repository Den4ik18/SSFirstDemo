package com;

import com.parser.YamlParser;
import com.service.ParserService;

import java.io.File;
import java.time.LocalDate;

/**
 * This class for parsing files.
 * This class is temporary.
 * */
public class Main {
    public static void main(String[] args) {

        ParserService parserService = new ParserService();
        parserService.setEmployees(parserService.searchEmployeeInSingleFile(new File("src/main/resources/inputFile.txt")));

        parserService.getEmployees().forEach(employee -> employee.getJobs().stream()
                .filter(job -> job.getEndDate() == null)
                .forEach(job -> job.setEndDate(LocalDate.now())));

        YamlParser.exportYamlList("src/main/resources/main.yml", parserService.getEmployees());
    }
}
