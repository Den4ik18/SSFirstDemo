package com.dataBase.dao;

import com.model.Address;
import com.model.Employee;
import com.model.Job;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDao implements Dao<Employee> {
    private static final Connection connection = MySqlConnection.getInstance().getConnection();
    private static final String selectSql = "SELECT * FROM employee";
    private static final String deleteSql = "DELETE FROM employee WHERE employee_id=?";
    private static final String insertSql = "INSERT INTO employee(name, last_name, phone_number,sex,email,date_of_birth) VALUES (?,?,?,?,?,?)";
    private static final String updateSql = "UPDATE employee SET name = ?,last_name = ?, phone_number = ?, sex = ?, email = ?, date_of_birth= ? WHERE employee_id = ?";
    private static final String selectWithCondition = "SELECT * FROM employee WHERE employee_id=?";
    private static final String selectAddress = "SELECT street,city,zip_code FROM address WHERE employee_id = ?";
    private static final String selectJob = "SELECT company_name,start_date,end_date,position FROM job WHERE employee_id = ?";

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                long id = resultSet.getLong("employee_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                String sex = resultSet.getString("sex");
                String email = resultSet.getString("email");
                LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                Address address = getAddress(id);
                List<Job> jobs = getJobs(id);

                employees.add(new Employee(id, name, lastName, phoneNumber, address, sex, email, dateOfBirth, jobs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


    @Override
    public void remove(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(deleteSql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getById(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(selectWithCondition);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long emloyeeId = resultSet.getLong("employee_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String sex = resultSet.getString("sex");
                String phoneNumber = resultSet.getString("phone_number");
                LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                String email = resultSet.getString("email");

                Address address = getAddress(emloyeeId);
                List<Job> jobs = getJobs(emloyeeId);

                Employee employee = new Employee();
                employee.setId(id);
                employee.setName(name);
                employee.setEmail(email);
                employee.setDateOfBirth(dateOfBirth);
                employee.setSex(sex);
                employee.setLastName(lastName);
                employee.setPhoneNumber(phoneNumber);
                employee.setAddress(address);
                employee.setJobs(jobs);
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void add(Employee employee) {
        if(employee.getId()!=null){
            update(employee);
           return;
        }
        try {
            PreparedStatement preparedStatement = getPreparedStatement(employee,insertSql);
            if (employee.getId() != null) {
                preparedStatement.setLong(4, employee.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private PreparedStatement getPreparedStatement(Employee employee,String line) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(line);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getPhoneNumber());
        preparedStatement.setString(4, employee.getSex());
        preparedStatement.setString(5, employee.getEmail());
        preparedStatement.setDate(6, Date.valueOf(employee.getDateOfBirth()));
        return preparedStatement;
    }

    @Override
    public Long update(Employee employee) {
        if(employee.getId()==null){
            add(employee);
            return 1L;
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getPreparedStatement(employee,updateSql);
            if (employee.getId() != null) {
                preparedStatement.setLong(4, employee.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return employee.getId();
    }

    private Address getAddress(long id) throws SQLException {
        PreparedStatement addressPreparedStatement = Objects.requireNonNull(connection).prepareStatement(selectAddress);
        addressPreparedStatement.setLong(1, id);
        ResultSet resultSetForAddress = addressPreparedStatement.executeQuery();
        Address address = new Address();
        while (resultSetForAddress.next()) {
            address.setStreet(resultSetForAddress.getString("street"));
            address.setCity(resultSetForAddress.getString("city"));
            address.setZipCode(resultSetForAddress.getInt("zip_code"));
        }
        return address;
    }

    private List<Job> getJobs(long id) throws SQLException {
        PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(selectJob);
        preparedStatement.setLong(1, id);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        List<Job> jobs = new ArrayList<>();
        while (resultSet1.next()) {
            jobs.add(new Job(resultSet1.getString("company_name"), resultSet1.getDate("start_date").toLocalDate(),
                    resultSet1.getDate("end_date").toLocalDate(), resultSet1.getString("position")));
        }
        return jobs;
    }

}
