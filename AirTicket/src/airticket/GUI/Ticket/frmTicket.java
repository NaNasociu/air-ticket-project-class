/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Ticket;

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
public class frmTicket {
    
    public frmTicket(){
        try{
            Connector db = new Connector();  
            Connection conn = db.getConnection();            
            String link = "C:\\Users\\NhatTan\\Documents\\NetBeansProjects\\AirTicket\\src\\airticket\\GUI\\Ticket\\report1.jrxml";
//            String link = "D:\\NGU\\barcode.jrxml";
            JasperReport j_report = JasperCompileManager.compileReport(link);
            JasperPrint j_print = JasperFillManager.fillReport(j_report, null, conn);
            Map<String, Object> params = new HashMap<String, Object>(); 
           
            
            
            String temp = "ac";
            params.put("ticket_id", 2321312); 
            params.put("customer_name", temp);  
            params.put("flight_id", 1); 
            params.put("seat_id", 2); 
            params.put("departure", 2); 
            params.put("destination", 3); 
            params.put("date", 1); 
            params.put("boarding_time", 1); 
            
//            params.put("customer_name", 2321312);  
//            JasperPrint j_print = JasperFillManager.fillReport(j_report, params, new JREmptyDataSource());
            
//            JasperPrint j_print = JasperFillManager.fillReport(j_report, new HashMap(), new JREmptyDataSource()); 
            JasperViewer.viewReport(j_print);
        }catch(Exception e){
            System.out.println("Loi:" + e.toString());
        }    
    }
    
    public static void main(String[] args) {
        new frmTicket();
    }
}
