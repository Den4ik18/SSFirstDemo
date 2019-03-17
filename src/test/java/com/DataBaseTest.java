package com;

import com.database.dao.AddressDao;
import com.database.dao.EmployeeDao;
import com.database.dao.JobDao;
import com.model.Address;
import com.model.Employee;
import com.model.Job;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class DataBaseTest {
    private Job testJob;
    private Address testAddress;

    private Employee initializeEmployee() {
        Employee employee = new Employee();
        employee.setName("Denys");
        employee.setLastName("Ohohodnik");
        employee.setSex("male");
        employee.setPhoneNumber("+380973999060");
        employee.setDateOfBirth(LocalDate.now().withYear(1999).withMonth(6).withDayOfMonth(4));
        employee.setEmail("den.ohorodnik@gmail.com");

        return employee;
    }
    @DataProvider
    public Object[] getJob() {
        testJob = new Job("SoftServe", LocalDate.parse("2018-10-02"),
                LocalDate.parse("2019-03-12"), "middle");
        return new Object[]{
                testJob
        };
    }
    @DataProvider
    public Object[] getAddress(){
        Address address = new Address("Holovna, 110","Chenivtsi",58000);
        return new Object[]{
                testAddress = address
        };
    }

    private Job initializeJob() {
        Job job = new Job();
        job.setPosition("junior");
        job.setCompanyName("SoftServe");
        job.setStartDate(LocalDate.parse("2010-10-03"));
        job.setEndDate(LocalDate.parse("2015-05-03"));
        return job;
    }

    @Test(dataProvider = "getJob")
    public void getJobByIdAndReturnTrueWhenObjectsAreEquals(Job expectedJob){
        Job job = new JobDao().getById(1L);
        Assert.assertEquals(job,expectedJob);
    }

    @Test(dataProvider = "getAddress")
    public void getAddressByIdAndReturnTrueWhenObjectsAreEquals(Address expectedAddress){
        Address address = new AddressDao().getById(2L);
        Assert.assertEquals(address,expectedAddress);
    }

    @Test
    public void insertEmployeeAndSelectAllEmployeeThenReturnEmployeeById() {
        EmployeeDao employeeDao = new EmployeeDao();
        Employee expectedEmployee = initializeEmployee();

        employeeDao.add(expectedEmployee);
        List<Employee> employees = employeeDao.getAll();
        employees.sort(Comparator.comparing(Employee::getId).reversed());
        Employee actual = employees.get(0);
        expectedEmployee.setId(employees.get(0).getId());
        expectedEmployee.setAddress(new Address());

        Assert.assertEquals(actual, expectedEmployee);
        employeeDao.remove(expectedEmployee.getId());
    }

    @Test
    public void insertJobAndSelectAllJobsThenReturnJobById() {
        JobDao jobDao = new JobDao();
        Job expectedJob = initializeJob();
        jobDao.add(expectedJob);

        List<Job> jobs = jobDao.getAll();
        jobs.sort(Comparator.comparing(Job::getId).reversed());
        Job actual = jobs.get(0);
        expectedJob.setId(jobs.get(0).getId());

        Assert.assertEquals(actual, expectedJob);
        jobDao.remove(expectedJob.getId());
    }
    @Test
    public void updateEmployeeAndReturnTrueThenObjectAreEquals() {
        EmployeeDao employeeDao = new EmployeeDao();
        Employee expected = initializeEmployee();
        employeeDao.add(expected);
        expected.setAddress(new Address());
        employeeDao.update(expected,1L);
        List<Employee> employees = employeeDao.getAll();
        employees.sort(Comparator.comparing(Employee::getId).reversed());
        expected.setId(employees.get(0).getId());
        Employee actual = employees.get(0);

        Assert.assertEquals(actual, expected);
        employeeDao.remove(expected.getId());
    }
}
