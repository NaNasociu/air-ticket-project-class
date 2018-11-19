package com.airticket.project.Controller.Login;

import com.airticket.project.Connector.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;

public class Login {
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
        String sql_stmt = "";

        Connector db = new Connector();

        ResultSet rset = db.ExecuteSQLStatementWithResult(sql_stmt);
        while (rset.next()) {
            isLogin = true;

            return true;
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
