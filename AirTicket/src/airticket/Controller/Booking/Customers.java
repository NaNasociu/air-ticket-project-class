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
public class Customers {
    private String familyName;
    private String middleAndGivenName;
    private String address;
    private String city;
    private String state;
    private String email;
    private String birthdate;
    private String passportNumber;
    private String mobileNumber;
    private String passportExpiry;
    private String nationality;
    private String idCard;
    private int sex;


    public void createCustomer() throws SQLException {
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement statement = conn.prepareCall("{call sp_add_new_customer(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        statement.setString(1,  this.idCard);
        statement.setString(2, this.passportNumber);
        statement.setString(3, this.passportExpiry);
        statement.setString(4, this.familyName);
        statement.setString(5, this.middleAndGivenName);
        statement.setString(6, this.birthdate);
        statement.setInt(7, 1);
        statement.setString(8, this.mobileNumber);
        statement.setString(9, this.email);
        statement.setString(10, this.address);
        statement.setString(11, this.city);
        statement.setString(12, this.nationality);
        statement.setString(13, "CT1");

        boolean hadResults = statement.execute();
        System.out.println(hadResults);
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
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

    public String getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(String passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
}
