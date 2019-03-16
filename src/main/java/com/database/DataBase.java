package com.database;

import com.database.dao.EmployeeDao;
import com.model.Employee;

import java.util.List;

/**
 * This class is temporary.
 * He is for manual testing dao.
 */
public class DataBase {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        System.out.println("====================================");
        Employee employee = new Employee();
//        boolean remove = employeeDao.remove(10L);
//        System.out.println(remove);
        List<Employee> all = employeeDao.getAll();
        for (Employee em : all) {
            System.out.println(em);
        }

    }

}
