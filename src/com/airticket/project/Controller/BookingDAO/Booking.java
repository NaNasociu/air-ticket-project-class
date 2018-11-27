package com.airticket.project.Controller.BookingDAO;

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
