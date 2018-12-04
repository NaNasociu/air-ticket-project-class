package com.airticket.project.Controller.BookingDAO;

import java.util.Date;

public class Customers {
    private String familyName;
    private String middleAndGivenName;
    private String address;
    private String city;
    private String state;
    private String email;
    private Date birthdate;
    private String passportNumber;
    private String mobileNumber;
    private Date passportExpiry;
    private String nationality;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleAndGivenName() {
        return middleAndGivenName;
    }

    public void setMiddleAndGivenName(String middleAndGivenName) {
        this.middleAndGivenName = middleAndGivenName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(Date passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
