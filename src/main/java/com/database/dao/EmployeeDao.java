package com.database.dao;

import com.model.Address;
import com.model.Employee;
import com.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDao implements Dao<Employee> {
    private static final Logger logger = LogManager.getLogger(EmployeeDao.class);
    private static final Connection connection = MySqlConnection.getInstance().getConnection();
    private static final String SELECT_FROM_EMPLOYEE = "SELECT * FROM employee";
    private static final String DELETE_FROM_EMPLOYEE = "DELETE FROM employee WHERE employee_id=?";
    private static final String INSERT_TO_EMPLOYEE = "INSERT INTO employee(name, last_name, phone_number,sex,email,date_of_birth) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET name = ?,last_name = ?, phone_number = ?, sex = ?, email = ?, date_of_birth= ? WHERE employee_id = ?";
    private static final String SELECT_WITH_CONDITION = "SELECT * FROM employee WHERE employee_id=?";
    private static final String SELECT_ADDRESS = "SELECT street,city,zip_code FROM address WHERE employee_id = ?";
    private static final String SELECT_JOB = "SELECT company_name,start_date,end_date,position FROM job WHERE employee_id = ?";
    private static final String DELETE_BY_NAME = "DELETE FROM employee WHERE name=?";
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String NAME = "name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String SEX = "sex";
    private static final String EMAIL = "email";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String COMPANY_NAME = "company_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String POSITION = "position";
    private static final String STREET = "street";
    private static final String CITY = "city";
    private static final String ZIP_CODE = "zip_code";

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_EMPLOYEE);
            while (resultSet.next()) {
                long id = resultSet.getLong(EMPLOYEE_ID);
                String name = resultSet.getString(NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String sex = resultSet.getString(SEX);
                String email = resultSet.getString(EMAIL);
                LocalDate dateOfBirth = resultSet.getDate(DATE_OF_BIRTH).toLocalDate();
                Address address = getAddress(id);
                List<Job> jobs = getJobs(id);
                employees.add(new Employee(id, name, lastName, phoneNumber, address, sex, email, dateOfBirth, jobs));
                logger.info("Employee was received");
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Return all employees");
        return employees;
    }


    @Override
    public boolean remove(Long id) {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(DELETE_FROM_EMPLOYEE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Employee was deleted by id: " + id);
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Employee wasn't deleted: " + id);
        return false;
    }

    public boolean removeByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            logger.info("Employee was deleted by name: " + name);
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Employee wasn't deleted: " + name);
        return false;
    }

    @Override
    public Employee getById(Long id) {
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_WITH_CONDITION);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long employeeId = resultSet.getLong(EMPLOYEE_ID);
                String name = resultSet.getString(NAME);
                String lastName = resultSet.getString(LAST_NAME);
                String sex = resultSet.getString(SEX);
                LocalDate dateOfBirth = resultSet.getDate(DATE_OF_BIRTH).toLocalDate();
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String email = resultSet.getString(EMAIL);

                Address address = getAddress(employeeId);
                List<Job> jobs = getJobs(employeeId);

                employee.setId(id);
                employee.setName(name);
                employee.setEmail(email);
                employee.setDateOfBirth(dateOfBirth);
                employee.setSex(sex);
                employee.setLastName(lastName);
                employee.setPhoneNumber(phoneNumber);
                employee.setAddress(address);
                employee.setJobs(jobs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Employee was received by id: " + id);
        return employee;
    }

    @Override
    public Employee add(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TO_EMPLOYEE);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(3, employee.getPhoneNumber());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(4, employee.getSex());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setDate(6, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Employee was added to database");
        return employee;
    }

    private PreparedStatement getPreparedStatement(Employee employee, String line) throws SQLException {
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
    public Long update(Employee employee, Long id) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(employee, UPDATE_EMPLOYEE);
            if (employee.getId() != null) {
                preparedStatement.setLong(7, id);
            }
            preparedStatement.executeUpdate();
            logger.info("Employee was updated by id: " + id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Return id employee what was updated by id:" + id);
        return employee.getId();
    }

    private Address getAddress(long id) throws SQLException {
        PreparedStatement addressPreparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_ADDRESS);
        addressPreparedStatement.setLong(1, id);
        ResultSet resultSetForAddress = addressPreparedStatement.executeQuery();
        Address address = new Address();
        while (resultSetForAddress.next()) {
            address.setStreet(resultSetForAddress.getString(STREET));
            address.setCity(resultSetForAddress.getString(CITY));
            address.setZipCode(resultSetForAddress.getInt(ZIP_CODE));
        }
        logger.info("Return address certain employee by id: " + id);
        return address;
    }

    private List<Job> getJobs(long id) throws SQLException {
        PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_JOB);
        preparedStatement.setLong(1, id);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        List<Job> jobs = new ArrayList<>();
        while (resultSet1.next()) {
            jobs.add(new Job(resultSet1.getString(COMPANY_NAME), resultSet1.getDate(START_DATE).toLocalDate(),
                    resultSet1.getDate(END_DATE).toLocalDate(), resultSet1.getString(POSITION)));
        }
        logger.info("Return jobs certain employee by id: " + id);
        return jobs;
    }

}
