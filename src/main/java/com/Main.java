package com;

import com.service.EmployeeService;

import java.io.File;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        employeeService.searchEmployee(new File("src/main/resources/inputFile.txt"));

        employeeService.getEmployees().forEach(employee -> employee.getJobs().stream()
                .filter(job -> job.getEndDate() == null)
                .forEach(job -> job.setEndDate(LocalDate.now())));

        employeeService.exportYamlList("src/main/resources/main.yml", employeeService);
    }
}
