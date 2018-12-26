/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.Controller.Booking;

import airticket.Connector.Connector;
import java.sql.*;

/**
 *
 * @author NhatTan
 */
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
    
}
