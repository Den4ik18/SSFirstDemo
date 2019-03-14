package com.dataBase.dao;

import com.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressDao implements Dao<Address> {
    private static final Connection connection = MySqlConnection.getInstance().getConnection();
    private static final String selectSql = "SELECT * FROM address";
    private static final String deleteSql = "DELETE FROM address WHERE address_id=?";
    private static final String insertSql = "INSERT INTO address(street, city, zip_code) VALUES (?,?,?)";
    private static final String selectWithCondition = "SELECT * FROM address WHERE address_id=?";
    private static final String updateSql = "UPDATE address SET street = ?,city = ?, zip_code = ? WHERE address_id = ?";


    @Override
    public List<Address> getAll() {
        List<Address> address = new ArrayList<>();
        try (Statement statement = Objects.requireNonNull(connection).createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("address_id");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                int zipCode = resultSet.getInt("zip_code");
                address.add(new Address(id, street, city, zipCode));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
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
    public Address getById(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(selectWithCondition);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                int zipCode = resultSet.getInt("zip_code");
                Address address = new Address();
                address.setStreet(street);
                address.setZipCode(zipCode);
                address.setCity(city);
                return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Address address) {
        if (address.getId() != null) {
            update(address);
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getPreparedStatement(address,insertSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Long update(Address address) {
        if(address.getId()==null){
            add(address);
            return 1L;
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(updateSql);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getZipCode());
            if (address.getId() != null) {
                preparedStatement.setLong(4, address.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address.getId();
    }
    private PreparedStatement getPreparedStatement(Address address,String line) throws SQLException {
        PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(line);
        preparedStatement.setString(1, address.getStreet());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setInt(3, address.getZipCode());
        return preparedStatement;
    }


}
