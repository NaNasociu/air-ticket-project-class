package com.airticket.project.Controller.LoginDAO;

import com.airticket.project.Connector.Connector;

import java.sql.*;

public class LoginDAO {
    private String username;
    private String password;
    private boolean isLogin;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean loginSession() throws SQLException {

        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement statement = conn.prepareCall("{call sp_check_login(?, ?)}");
        statement.setString(1, username);
        statement.setString(2, password);

        boolean hadResults = statement.execute();
        while (hadResults) {
            ResultSet resultSet = statement.getResultSet();
            // process result set
            while (resultSet.next()) {
                // retrieve values of fields
                Integer status = resultSet.getInt("status_");
                System.out.println(status);
                if (status == 1) {
                    return true;
                }
            }
            hadResults = statement.getMoreResults();
        }
        return false;
    };

    public boolean logoutSession() throws SQLException {
        try {
            if (isLogin) {
                String sql_stmt = "";
                Connector db = new Connector();
                db.ExecuteSQLStatement(sql_stmt);
                isLogin = false;
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("The following code has occured: " + ex.getMessage());
        }
        return false;
    }
}
