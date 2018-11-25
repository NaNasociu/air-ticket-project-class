package com.airticket.project.Controller.BookingDAO;

import com.airticket.project.Connector.Connector;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAO {
    public String[] getAirport_in() throws SQLException {
        Connector db = new Connector();
        String stmt = "call sp_show_option_data()";
        ResultSet rs = db.ExecuteSQLStatementWithResult(stmt);
        Integer i = 0;
        rs.beforeFirst();
        rs.last();
        String[] list = new String[rs.getRow()];
        rs.beforeFirst();

        while (rs.next()) {
            list[i] = rs.getString("airport_in");
            i++;
        }
        return  list;
    }
    public String[] getAirport_out() throws SQLException {
        Connector db = new Connector();
        String stmt = "call sp_show_option_data()";
        ResultSet rs = db.ExecuteSQLStatementWithResult(stmt);
        Integer i = 0;
        rs.beforeFirst();
        rs.last();
        String[] list = new String[rs.getRow()];
        rs.beforeFirst();
        while (rs.next()) {
            list[i] = rs.getString("airport_out");
            i++;
        }
        return  list;
    }
}
