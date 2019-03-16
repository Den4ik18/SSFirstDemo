package com.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlParser {
    private static final Logger logger = LogManager.getLogger(YamlParser.class);
    public static void writeAllObjectToYamlFile(List<Employee> employeeList) {
        ObjectMapper mapper = new ObjectMapper(new YAMLMapper().getFactory());
        try {
            mapper.writeValue(new File("src/main/resources/main.yml"), employeeList);
            logger.info("All object was written to yaml file");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    public static String exportYamlList(String fileName, List<Employee> employees) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File file = new File(fileName);
        try {
            mapper.writeValue(file, employees);
            logger.info("Employees was written to file");
        } catch (IOException e) {
            logger.error(e.fillInStackTrace().getStackTrace());
        }
        logger.info("Employee wasn't written to file");
        return null;
    }


}
