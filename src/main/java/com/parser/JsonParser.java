package com.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Employee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JsonParser {

    public static Employee getEmployeeWithJsonFile(String line) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(line, Employee.class);
    }

    public static void getJsonFromEmployee(Employee employee){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(employee);
        try {
            Files.write(Paths.get("src/main/resources/jsonFormat.json"),
                    jsonString.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Employee getEmployeeFromJsonFile(File file){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Employee importJsonFromSingleLine(String line) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(line, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void exportJson(String fileName, Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName);
        try {
            mapper.writeValue(file, employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
