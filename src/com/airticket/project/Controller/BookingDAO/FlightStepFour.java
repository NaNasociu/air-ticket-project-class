package com.airticket.project.Controller.BookingDAO;

import com.airticket.project.Connector.Connector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightStepFour {
    private Customers customers;
    private String selectedFlight_Id;
    private FlightStepTwo flightStepTwo;
    private FlightStepTwo flightInfo;

    public FlightStepTwo getFligtInfo() throws SQLException {
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement statement = conn.prepareCall("{call sp_search_flight_with_flight_id(?)}");
        statement.setString(1, selectedFlight_Id);
        boolean hadResults = statement.execute();
        flightInfo = new FlightStepTwo();
        while (hadResults) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                flightInfo.setId(resultSet.getString("Flight ID"));
                flightInfo.setFlightDate(resultSet.getDate("Departure Time"));
                flightInfo.setDeparts(resultSet.getString("Departure Airport"));
                flightInfo.setArrives(resultSet.getString("Destination Airport"));
            }
            hadResults = statement.getMoreResults();
        }
        return flightInfo;
    }

    public FlightStepFour(Customers customers, String selectedFlight_Id) {
        this.customers = customers;
        this.selectedFlight_Id = selectedFlight_Id;
    }

    public void booking() throws SQLException {
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement statement = conn.prepareCall("{call sp_booking(?, ?, ?, ?, ?, ?)}");
        statement.setString(1, customers.getIdCard());
        statement.setString(2, selectedFlight_Id);
        statement.setString(3, "ECO");
        statement.setString(4, "CT1");
        statement.setString(5, "S01");
        statement.setString(6, "1");
        boolean hadResults = statement.execute();
        flightStepTwo = new FlightStepTwo();
        while (hadResults) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {

            }
            hadResults = statement.getMoreResults();
        }
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getSelectedFlight_Id() {
        return selectedFlight_Id;
    }

    public void setSelectedFlight_Id(String selectedFlight_Id) {
        this.selectedFlight_Id = selectedFlight_Id;
    }

    public FlightStepTwo getFlightStepTwo() {
        return flightStepTwo;
    }

    public void setFlightStepTwo(FlightStepTwo flightStepTwo) {
        this.flightStepTwo = flightStepTwo;
    }
}
