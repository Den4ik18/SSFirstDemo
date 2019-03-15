package com.dataBase;

import com.dataBase.dao.EmployeeDao;
import com.dataBase.dao.JobDao;
import com.model.Address;
import com.model.Employee;
import com.model.Job;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        System.out.println("====================================");

        Employee employee = new Employee();
        Job jobOnServe = new Job("SSS", LocalDate.parse("2010-06-04"),
                LocalDate.parse("2011-06-04"), "middle");
        List<Job> jobs = new ArrayList<>();
        jobs.add(jobOnServe);
        employee = new Employee("Grisha", "Pavlov", "+380983349065", new Address("Prospect ,55", "Chernivtsi", 58000),
                "male", "grisha33@gmail.com", jobs);
        employee.setDateOfBirth(LocalDate.parse("1999-06-04"));

        JobDao jobDao = new JobDao();
        List<Job> jobList = jobDao.getAll();
        for (Job job : jobList) {
            System.out.println(job);
        }
        System.out.println("====================================================");
        jobDao.add(jobOnServe);
        List<Job> jobList1 = jobDao.getAll();
        for (Job job : jobList1) {
            System.out.println(job);
        }
        System.out.println("===========================================");
        List<Employee> all = employeeDao.getAll();
        for (Employee em : all) {
            System.out.println(em);
        }

    }

}
