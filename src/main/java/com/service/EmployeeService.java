package com.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.xml.XmlMapper;
import com.model.Employee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.parser.JsonParser.importJsonFromSingleLine;
import static com.parser.TxtParser.serealizationTxt;
import static com.parser.XmlParser.getEmployeeFromXmlInputStream;


public class EmployeeService {
    private static List<Employee> employees = new ArrayList<>();
    private static Employee employeeTxt = new Employee();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        EmployeeService.employees = employees;
    }

    public void searchEmployee(File fileName) {
        boolean textFlag = false;
        List<String> txt = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<")) {
                    Employee employeeXml = new Employee();
                    InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
                    employeeXml = getEmployeeFromXmlInputStream(stream);
                    employees.add(employeeXml);
                } else if (line.startsWith("{")) {
                    Employee jsonEmployee = new Employee();
                    jsonEmployee = importJsonFromSingleLine(line);
                    employees.add(jsonEmployee);
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
        employees.add(employeeTxt);
    }



    public String exportYamlList(String fileName, EmployeeService employeeService) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File file = new File(fileName);
        try {
            mapper.writeValue(file, employeeService);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}



