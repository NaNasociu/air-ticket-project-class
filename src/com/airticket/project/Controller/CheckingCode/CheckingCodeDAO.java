package com.airticket.project.Controller.CheckingCode;

import com.airticket.project.Connector.Connector;

import java.sql.*;

public class CheckingCodeDAO {
    private String codeId;
    private String ticketId;
    private String idCard;
    private Date bookingDate;
    private String f_id;
    private String seatId;
    private String ticketFare;
    private String promodId;
    private String employId;
    private String ticketStatus;

    public CheckingCodeDAO() {

    }

    public CheckingCodeDAO(String code) {
        codeId = code;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getIdCard() {
        return idCard;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getF_id() {
        return f_id;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getTicketFare() {
        return ticketFare;
    }

    public String getPromodId() {
        return promodId;
    }

    public String getEmployId() {
        return employId;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public void setTicketFare(String ticketFare) {
        this.ticketFare = ticketFare;
    }

    public void setPromodId(String promodId) {
        this.promodId = promodId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void searchInfo() throws SQLException {
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement st = conn.prepareCall("{call sp_search_ticket(?)}");
        st.setString(1, codeId);
        boolean hadResults = st.execute();
        while (hadResults) {
            ResultSet resultSet = st.getResultSet();
            while (resultSet.next()) {
                ticketId = resultSet.getString("ticket_id");
                idCard = resultSet.getString("id_card_number");
                bookingDate = resultSet.getDate("booking_date");
                f_id = resultSet.getString("f_id");
                seatId = resultSet.getString("seat_id");
                ticketFare = resultSet.getString("ticket_fare");
                promodId = resultSet.getString("promo_id");
                employId = resultSet.getString("empl_id");
                ticketStatus = resultSet.getString("ticket_status");
            }
            hadResults = st.getMoreResults();
        }
    }

}
