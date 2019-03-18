package com.database.service;

import com.database.dao.EmployeeDao;
import com.model.Employee;

import java.util.List;

public class EmployeeService implements Service<Employee> {
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public boolean remove(Long id) {
        return employeeDao.remove(id);
    }

    @Override
    public Employee getById(Long id) {
        return employeeDao.getById(id);
    }

    @Override
    public Employee add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public Long update(Employee employee, Long id) {
        return employeeDao.update(employee, id);
    }

    @Override
    public boolean removeByParameter(String name) {
        return employeeDao.removeByName(name);
    }
}
