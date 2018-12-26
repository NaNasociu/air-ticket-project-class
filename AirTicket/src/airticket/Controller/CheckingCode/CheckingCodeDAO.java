/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.Controller.CheckingCode;

import airticket.Connector.Connector;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author NhatTan
 */
public class CheckingCodeDAO {
    
    private String codeId, customer_name, flight_id, seat_id, departure, destination, date_fly, boarding_time;

    public CheckingCodeDAO() {

    }

    public CheckingCodeDAO(String code) {
        codeId = code;
    }

    public void searchInfo() throws SQLException {
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement st = conn.prepareCall("{call sp_checking_code_and_export_ticket(?)}");
        st.setString(1, codeId);
        boolean hadResults = st.execute();
        while (hadResults) {
            ResultSet resultSet = st.getResultSet();
            while (resultSet.next()) {
                customer_name = resultSet.getString("Customer Name");
                departure = resultSet.getString("Departure Airport");
                destination = resultSet.getString("Destination Airport");
                flight_id = resultSet.getString("Flight");
                date_fly = resultSet.getString("Date");
                boarding_time = resultSet.getString("Boarding Time");
                seat_id = resultSet.getString("Seat ID");
            }
            hadResults = st.getMoreResults();
        }
        try{        
            String link = "C:\\Users\\NhatTan\\Documents\\NetBeansProjects\\AirTicket\\src\\airticket\\GUI\\Ticket\\invoice.jrxml";
            JasperReport j_report = JasperCompileManager.compileReport(link);
            Map<String, Object> params = new HashMap<String, Object>(); 
           
            params.put("ticket_id", codeId); 
            params.put("customer_name", customer_name);  
            params.put("flight_id", flight_id); 
            params.put("seat_id", seat_id); 
            params.put("departure", departure); 
            params.put("destination", destination); 
            params.put("date", date_fly); 
            params.put("boarding_time", boarding_time); 

            JasperPrint j_print = JasperFillManager.fillReport(j_report, params, new JREmptyDataSource()); 
            JasperViewer.viewReport(j_print);
        }catch(Exception e){
            System.out.println("Loi:" + e.toString());
        }           
        
    }    
    
    
}
