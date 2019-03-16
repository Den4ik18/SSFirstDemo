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


public class ParserService {
    private static List<Employee> employeesFromFile = new ArrayList<>();
    private static Employee employeeTxt = new Employee();

    public List<Employee> getEmployees() {
        return employeesFromFile;
    }

    public void setEmployees(List<Employee> employees) {
        ParserService.employeesFromFile = employees;
    }

    public List<Employee> searchEmployeeInSingleFile(File fileName) {
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
                } else if (line.startsWith("{")) {
                    Employee jsonEmployee = new Employee();
                    jsonEmployee = importJsonFromSingleLine(line);
                    employeesFromFile.add(jsonEmployee);
                } else {
                    txt.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            employeeTxt = serealizationTxt(txt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employeesFromFile.add(employeeTxt);
        return employeesFromFile;
    }
}



