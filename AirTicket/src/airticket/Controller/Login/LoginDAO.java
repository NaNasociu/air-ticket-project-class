/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.Controller.Login;

import airticket.Connector.Connector;
import java.sql.CallableStatement;
import java.sql.*;

/**
 *
 * @author NhatTan
 */
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
        CallableStatement statement = conn.prepareCall("call sp_check_login(?, ?)");
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
   
}
