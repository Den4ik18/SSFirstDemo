package com.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JsonParser {
    private static final Logger logger = LogManager.getLogger(JsonParser.class);


    public static void writeJsonFromEmployee(Employee employee) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(employee);
        try {
            Files.write(Paths.get("src/main/resources/jsonFormat.json"),
                    jsonString.getBytes(), StandardOpenOption.CREATE);
            logger.info("In json file was written employee");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static Employee getEmployeeFromJsonFile(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, Employee.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Employee importJsonFromSingleLine(String line) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(line, Employee.class);
        } catch (IOException e) {
            logger.error(e.fillInStackTrace().getStackTrace());
        }
        logger.info("File was not read successful");
        return null;
    }

    public static void exportJson(String fileName, Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName);
        try {
            mapper.writeValue(file, employee);
            logger.info("Employee was written to file");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
