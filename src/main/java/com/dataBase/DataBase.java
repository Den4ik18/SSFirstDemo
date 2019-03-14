package com.dataBase;

import com.dataBase.dao.EmployeeDao;
import com.model.Employee;

import java.util.List;

public class DataBase {
    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.getAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        /*Employee byId = employeeDao.getById(4L);
        System.out.println(byId);*/


    }

}
