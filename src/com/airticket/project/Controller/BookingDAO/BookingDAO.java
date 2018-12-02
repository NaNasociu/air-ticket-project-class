package com.airticket.project.Controller.BookingDAO;

import com.airticket.project.Connector.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class BookingDAO {
    private ArrayList<Booking> airport = new ArrayList<Booking>();
    private ArrayList<FlightStepTwo> flightList = new ArrayList<FlightStepTwo>();

    public BookingDAO() throws SQLException {
        Connector db = new Connector();
        String stmt = "call sp_show_option_data()";
        ResultSet rs = db.ExecuteSQLStatementWithResult(stmt);
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

    public ArrayList<FlightStepTwo> getFlightList(String airportIn, String airportOut, Date timeFrom) throws SQLException {
        String temp = "";
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement statement = conn.prepareCall("{call sp_search_flight_when_sale(?, ?, ?)}");
        System.out.println(airportIn);
        System.out.println(airportOut);
        statement.setString(1, airportIn);
        statement.setString(2, airportOut);
        statement.setDate(3, java.sql.Date.valueOf("2018-1-1"));
//        statement.setDate(3, (java.sql.Date) timeFrom);
        boolean hadResults = statement.execute();

        while (hadResults) {
            ResultSet resultSet = statement.getResultSet();
            // process result set
            System.out.print("11");
            while (resultSet.next()) {
                // retrieve values of fields
                System.out.print("22");
                FlightStepTwo flightTemp = new FlightStepTwo();
                flightTemp.setFlightDate(resultSet.getDate("flightDate"));
                flightTemp.setArrives(resultSet.getString("Arrives"));
                flightTemp.setDeparts(resultSet.getString("Departs"));
                flightTemp.setBusiness(resultSet.getString("BUSINESS"));
                flightTemp.setEconomy(resultSet.getString("ECONOMY"));
                flightTemp.setPremium(resultSet.getString("PREMIUM"));
                flightTemp.printAll();
                flightList.add(flightTemp);

            }
            hadResults = statement.getMoreResults();
        }
        return flightList;
    }
}
