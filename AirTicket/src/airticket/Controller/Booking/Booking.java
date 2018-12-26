/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.Controller.Booking;

/**
 *
 * @author NhatTan
 */
public class Booking {
    private String airport_name;
    private String airport_id;

    public Booking() {
        airport_name = "";
        airport_id = "";
    }

    public Booking(String id, String name) {
        airport_id = id;
        airport_name = name;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public String getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(String airport_id) {
        this.airport_id = airport_id;
    } 

}
