package com.airticket.project.Controller.BookingDAO;

import java.util.Date;

public class FlightStepTwo {
    private Date flightDate;
    private String departs;
    private String arrives;
    private String economy;
    private String premium;
    private String business;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getDeparts() {
        return departs;
    }

    public void setDeparts(String departs) {
        this.departs = departs;
    }

    public String getArrives() {
        return arrives;
    }

    public void setArrives(String arrives) {
        this.arrives = arrives;
    }

    public String getEconomy() {
        return economy;
    }

    public void setEconomy(String economy) {
        this.economy = economy;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public void printAll() {
        System.out.println(flightDate);
        System.out.print(departs);
        System.out.print(arrives);
        System.out.print(economy);
        System.out.print(premium);
        System.out.print(business);

    }
}
