package com.database.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlConnection {
    private static final Logger logger = LogManager.getLogger(MySqlConnection.class);
    private static final String URL = "jdbc:postgresql://localhost:5432/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static MySqlConnection instance;
    private Connection connection;


    private MySqlConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error("Database connection creation failed : ", ex.getMessage());
        }
    }

    Connection getConnection() {
        return connection;
    }

    static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new MySqlConnection();
                    logger.info("Open connection");
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        logger.info("Return instance");
        return instance;
    }


}
