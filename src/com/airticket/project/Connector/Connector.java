package com.airticket.project.Connector;

import com.airticket.project.config;

import java.sql.*;

public class Connector {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public Connector() throws SQLException {
        try {
            connection = DriverManager.getConnection(config.DB_URL, config.USER, config.PASS);

        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void ExecuteSQLStatement(String sql_stmt) {
        try {
            statement = connection.createStatement();

            statement.executeUpdate(sql_stmt);
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
    }

    public ResultSet ExecuteSQLStatementWithResult(String sql_stmt) {
        ResultSet rset = null;
        try {
            statement = connection.createStatement();
            rset = statement.executeQuery(sql_stmt);
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
        return rset;
    }
}