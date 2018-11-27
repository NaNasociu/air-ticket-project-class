package com.airticket.project.Controller.BookingDAO;

import com.airticket.project.Connector.Connector;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAO {
    private ArrayList<Booking> airport = new ArrayList<Booking>();

    public BookingDAO() throws SQLException {
        Connector db = new Connector();
        String stmt = "call sp_show_option_data()";
        ResultSet rs = db.ExecuteSQLStatementWithResult(stmt);
        Integer i = 0;
        rs.beforeFirst();
        rs.last();
        String[] list = new String[rs.getRow()];
        rs.beforeFirst();
        while (rs.next()) {
            String name = rs.getString("airport_name");
            String id = rs.getString("airport_id");
            Booking temp = new Booking(id, name);
            airport.add(temp);
        }
    }

    public String[] getAirportName() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        for (Booking temp : airport) {
            list.add(temp.getAirport_name());
        }
        String[] airportList = new String[list.size()];
        airportList = list.toArray(airportList);
        return  airportList;
    }

    public String convertNameToId(String name) {
        String idTemp = "";
        for (Booking temp : airport) {
            if (temp.getAirport_name() == name) {
                idTemp = temp.getAirport_id();
            }
        }
        return idTemp;
    }

    public String getFlightList(String airportIn, String airportOut) {
        String temp = "";
        return temp;
    }
}
