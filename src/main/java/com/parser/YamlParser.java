package com.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.model.Employee;
import com.service.ParserService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlParser {

    public static void writeAllObjectToYamlFile(List<Employee> employeeList) {
        ObjectMapper mapper = new ObjectMapper(new YAMLMapper().getFactory());
        try {
            mapper.writeValue(new File("src/main/resources/main.yml"), employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String exportYamlList(String fileName, List<Employee> employees) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File file = new File(fileName);
        try {
            mapper.writeValue(file, employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
