package com.airticket.project.Controller.BookingDAO;

import com.airticket.project.Connector.Connector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
    private String idCard;
    private Boolean sex;


    public void createCustomer() throws SQLException {
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement statement = conn.prepareCall("{call sp_add_new_customer(?,?,?,?,?,?,?,?,?,?,?,?,?}");
        statement.setString(1,  this.idCard);
        statement.setString(2, this.passportNumber);
        System.out.print(this.passportExpiry);
        statement.setDate(3, (java.sql.Date) this.passportExpiry);
        statement.setString(4, this.familyName);
        statement.setString(5, this.middleAndGivenName);
        statement.setDate(6, (java.sql.Date) this.birthdate);
        statement.setString(7, String.valueOf(this.sex));
        statement.setString(8, this.mobileNumber);
        statement.setString(9, this.email);
        statement.setString(10, this.address);
        statement.setString(11, this.state);
        statement.setString(12, this.nationality);
        statement.setString(13, "VIP");
        boolean hadResults = statement.execute();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

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
