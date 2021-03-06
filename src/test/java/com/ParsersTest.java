package com;

import com.model.Address;
import com.model.Employee;
import com.model.Job;
import com.service.ParserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.parser.JsonParser.*;
import static com.parser.XmlParser.*;

public class ParsersTest {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
    private ParserService parserService;
    private Employee employeeFromTxt;
    private Employee employeeFromJson;
    private Employee employeeFromXml;


    @DataProvider
    public Object[] getXmlEmployee() {
        LocalDate dateOfBirth = LocalDate.parse("1990-10-05");
        Job jobOnServe = new Job("SoftServe", LocalDate.parse("2014-10-13"),
                LocalDate.parse("2015-10-13"), "junior");
        Job jobOnInventor = new Job("InventorSoft", LocalDate.parse("2017-10-13"),
                LocalDate.parse("2018-02-13"), "junior");
        List<Job> jobs = new ArrayList<>();
        jobs.add(jobOnServe);
        jobs.add(jobOnInventor);
        return new Object[]{
                employeeFromXml = new Employee("Petro", "Ivanov", "+38098443423", new Address("Holovna ,110", "Chernivtsi", 58000),
                        "male", "ivanov.pet@gmail.com", dateOfBirth, jobs)
        };
    }

    @DataProvider
    public Object[] getJsonEmployee() {
        Job jobOnServe = new Job("SoftServe", LocalDate.parse("2010-06-04"),
                LocalDate.parse("2011-06-04"), "junior");
        List<Job> jobs = new ArrayList<>();
        jobs.add(jobOnServe);
        employeeFromJson = new Employee("Denys", "Ohorodnik", "+380973999060", new Address("Nebesnoi Sotni ,4B", "Chernivtsi", 58000),
                "male", "den.ohorodnik@gmail.com", jobs);
        employeeFromJson.setDateOfBirth(LocalDate.parse("1999-06-04"));
        return new Object[]{
                employeeFromJson
        };
    }
    @DataProvider
    public Object[] getTxtEmployee() {
        Job jobOnServe = new Job("InventorSort", LocalDate.parse("2016-04-10"),
                LocalDate.parse("2017-09-13"), "junior");
        List<Job> jobs = new ArrayList<>();
        jobs.add(jobOnServe);
        employeeFromJson = new Employee("Petro", "Ivanov", "+38098443423", new Address(" Holovna 220", "Chernivtsi", 58000),
                "male", "petro.ivanov@ukr.net", jobs);
        employeeFromJson.setDateOfBirth(LocalDate.parse("1990-10-05"));
        return new Object[]{
                employeeFromJson
        };
    }


    @Test(dataProvider = "getXmlEmployee")
    public void pasreXmlFileAndReturnTrueWhenObjectsAreEquals(Employee expectedEmployee) {
        Employee employee = getEmployeeWithXmlFile(new File("src/main/resources/xmlFile.xml"));
        Assert.assertEquals(employee, expectedEmployee);

    }
    @Test(dataProvider = "getXmlEmployee")
    public void exportEmployeeToXmlFileAndReturnTrueWhenObjectAreEquals(Employee expectedEmployee) throws IOException {
        exportXML("src/main/resources/xmlFileForTest.xml",expectedEmployee);
        Employee employee = getEmployeeWithXmlFile(new File("src/main/resources/xmlFile.xml"));
        Assert.assertEquals(employee,expectedEmployee);
    }

    @Test(dataProvider = "getJsonEmployee")
    public void parseJsonFileAndReturnTrueWhenObjectsAreEquals(Employee expectedEmployee) {
        Employee employee = getEmployeeFromJsonFile(new File("src/main/resources/jsonFile.json"));
        Assert.assertEquals(employee, expectedEmployee);
    }
    @Test(dataProvider = "getJsonEmployee")
    public void exportEmployeeToJsonFileThenReadHimAndReturnTrueWhenObjectsAreEquals(Employee expectedEmployee){
        exportJson("src/main/resources/jsonFileForTest.json",expectedEmployee);
        Employee employee = getEmployeeFromJsonFile(new File("src/main/resources/jsonFileForTest.json"));
        Assert.assertEquals(employee,expectedEmployee);
    }

    @Test(dataProvider = "getXmlEmployee")
    public void serializationEmployeeFromXmlFileAndReturnTrueWhenObjectsAreEquals(Employee expectedEmployee) {
        writeToXmlFileFromEmployee(expectedEmployee);
        Employee employee = getEmployeeWithXmlFile(new File("src/main/resources/xmlFormat.xml"));
        Assert.assertEquals(employee, expectedEmployee);
    }

    @Test(dataProvider = "getJsonEmployee")
    public void serializationEmployeeFromJsonFileAndReturnTrueWhenObjectsAreEquals(Employee expectedEmployee) {
        writeJsonFromEmployee(expectedEmployee);
        Employee employee = getEmployeeFromJsonFile(new File("src/main/resources/jsonFile.json"));
        Assert.assertEquals(employee, expectedEmployee);
    }

    @Test(dataProvider = "getJsonEmployee")
    public void serializationEmployeeAnotherFileAndReturnTrueWhenObjectsAreEquals(Employee expectedEmployee) {
        writeJsonFromEmployee(expectedEmployee);
        Employee employee = getEmployeeFromJsonFile(new File("src/main/resources/jsonFile.json"));
        Assert.assertEquals(expectedEmployee, employee);
    }


}
