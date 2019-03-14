package com.dataBase.dao;

import com.model.Job;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JobDao implements Dao<Job> {
    private static final Connection connection = MySqlConnection.getInstance().getConnection();
    private static final String selectSql = "SELECT * FROM job";
    private static final String deleteSql = "DELETE FROM job WHERE job_id=?";
    private static final String insertSql = "INSERT INTO job(company_name, start_date, end_date,position) VALUES (?,?,?,?)";
    private static final String selectWithCondition = "SELECT * FROM job WHERE job_id=?";
    private static final String updateSql = "UPDATE job SET company_name = ?,start_date = ?, end_date = ?,position = ? WHERE job_id = ?";

    @Override
    public List<Job> getAll() {
        List<Job> jobs = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("job_id");
                String companyName = resultSet.getString("company_name");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
                String position = resultSet.getString("position");
                jobs.add(new Job(id, companyName, startDate, endDate, position));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
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
    public Job getById(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(selectWithCondition);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String companyName = resultSet.getString("company_name");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                String position = resultSet.getString("position");
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
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
    public void add(Job job) {
        if (job.getId() == null) {
            update(job);
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(insertSql);
            preparedStatement.setString(1, job.getCompanyName());
            preparedStatement.setDate(3, Date.valueOf(job.getEndDate()));
            preparedStatement.setDate(2, Date.valueOf(job.getStartDate()));
            preparedStatement.setString(4, job.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long update(Job job) {
        if (job.getId() == null) {
            add(job);
            return 1L;
        }
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(updateSql);
            preparedStatement.setString(1, job.getCompanyName());
            preparedStatement.setDate(2, Date.valueOf(job.getStartDate()));
            preparedStatement.setDate(3, Date.valueOf(job.getEndDate()));
            preparedStatement.setString(4, job.getPosition());
            if (job.getId() != null) {
                preparedStatement.setLong(5, job.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job.getId();

    }


}

