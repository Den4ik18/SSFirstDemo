package com.service;

import com.model.Employee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.parser.JsonParser.importJsonFromSingleLine;
import static com.parser.TxtParser.serealizationTxt;
import static com.parser.XmlParser.getEmployeeFromXmlInputStream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserService {
    private final Logger logger = LogManager.getLogger(ParserService.class);
    private static List<Employee> employeesFromFile = new ArrayList<>();
    private static Employee employeeTxt = new Employee();

    public List<Employee> getEmployees() {
        return employeesFromFile;
    }

    public void setEmployees(List<Employee> employees) {
        ParserService.employeesFromFile = employees;
    }

    public List<Employee> searchEmployeeInSingleFile(File fileName) {
        logger.info("try to search employee in file");
        boolean textFlag = false;
        List<String> txt = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<")) {
                    Employee employeeXml = new Employee();
                    InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
                    employeeXml = getEmployeeFromXmlInputStream(stream);
                    employeesFromFile.add(employeeXml);
                    logger.info("Added employee from xml format");
                } else if (line.startsWith("{")) {
                    Employee jsonEmployee = new Employee();
                    jsonEmployee = importJsonFromSingleLine(line);
                    employeesFromFile.add(jsonEmployee);
                    logger.info("Added employee from json format");
                } else {
                    txt.add(line);
                }
            }
        } catch (IOException e) {
            logger.error("Something wrong with file: " + e.getMessage());
        }
        try {
            employeeTxt = serealizationTxt(txt);
        } catch (ParseException e) {
            logger.error("Txt file was'n parsed: ",e.getMessage());

        }
        employeesFromFile.add(employeeTxt);
        logger.info("List object was returned from file");
        return employeesFromFile;
    }
}



