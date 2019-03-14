package com.dataBase.dao;

import com.mysql.cj.MysqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static MySqlConnection instance;
    private Connection connection;


    private MySqlConnection()  {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MySqlConnection getInstance()  {
        if (instance == null) {
            instance = new MySqlConnection();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new MySqlConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return instance;
    }


    void closeConnectionAndStatement(Connection connection, PreparedStatement preparedStatement) {
        if (connection != null && preparedStatement != null) {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
