package com.database.dao;

import com.model.Job;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JobDao implements Dao<Job> {
    private static final Connection connection = MySqlConnection.getInstance().getConnection();
    private static final String SELECT_FROM_JOB = "SELECT * FROM job";
    private static final String DELETE_FROM_JOB = "DELETE FROM job WHERE job_id=?";
    private static final String INSERT_INTO_JOB = "INSERT INTO job(company_name, start_date, end_date,position) VALUES (?,?,?,?)";
    private static final String INSERT_INTO_JOB_WITH_EMPLOYEE_ID = "INSERT INTO job(company_name, start_date, end_date,position,employee_id) VALUES (?,?,?,?,?)";
    private static final String SELECT_WITH_CONDITION = "SELECT * FROM job WHERE job_id=?";
    private static final String UPDATE_FROM_JOB = "UPDATE job SET company_name = ?,start_date = ?, end_date = ?,position = ? WHERE job_id = ?";
    private static final String DELETE_FROM_JOB_BY_COMPANY_NAME = "DELETE FROM job WHERE company_name=?";
    private static final String JOB_ID = "job_id";
    private static final String COMPANY_NAME = "company_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String POSITION = "position";


    @Override
    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_JOB);
            while (resultSet.next()) {
                Long id = resultSet.getLong(JOB_ID);
                String companyName = resultSet.getString(COMPANY_NAME);
                LocalDate startDate = resultSet.getDate(START_DATE).toLocalDate();
                LocalDate endDate = resultSet.getDate(END_DATE).toLocalDate();
                String position = resultSet.getString(POSITION);
                jobs.add(new Job(id, companyName, startDate, endDate, position));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public boolean remove(Long id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(DELETE_FROM_JOB);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeByCompanyName(String companyName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_JOB_BY_COMPANY_NAME);
            preparedStatement.setString(1, companyName);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Job getById(Long id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_WITH_CONDITION);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String companyName = resultSet.getString(COMPANY_NAME);
                LocalDate startDate = resultSet.getDate(START_DATE).toLocalDate();
                String position = resultSet.getString(POSITION);
                LocalDate endDate = resultSet.getDate(END_DATE).toLocalDate();
                Job job = new Job();
                job.setCompanyName(companyName);
                job.setStartDate(startDate);
                job.setEndDate(endDate);
                job.setPosition(position);
                return job;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Job add(Job job) {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(INSERT_INTO_JOB);
            preparedStatement.setString(1, job.getCompanyName());
            preparedStatement.setDate(3, Date.valueOf(job.getEndDate()));
            preparedStatement.setDate(2, Date.valueOf(job.getStartDate()));
            preparedStatement.setString(4, job.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public void addJobForEmployee(Job job, Long employeeId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_JOB_WITH_EMPLOYEE_ID);
            preparedStatement.setString(1, job.getCompanyName());
            preparedStatement.setString(4, job.getPosition());
            preparedStatement.setDate(2, Date.valueOf(job.getStartDate()));
            preparedStatement.setDate(3, Date.valueOf(job.getEndDate()));
            preparedStatement.setLong(5, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long update(Job job, Long id) {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(UPDATE_FROM_JOB);
            preparedStatement.setString(1, job.getCompanyName());
            preparedStatement.setDate(2, Date.valueOf(job.getStartDate()));
            preparedStatement.setDate(3, Date.valueOf(job.getEndDate()));
            preparedStatement.setString(4, job.getPosition());
            if (job.getId() != null) {
                preparedStatement.setLong(5, id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job.getId();

    }


}

