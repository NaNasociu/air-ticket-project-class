/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.Controller.Ticket;

import airticket.Connector.Connector;
import java.sql.*;

/**
 *
 * @author NhatTan
 */
public class TicketDAO {
    
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void getInvoice() throws SQLException{
        Connector db = new Connector();
        Connection conn = db.getConnection();
        CallableStatement stat = conn.prepareCall("");
        stat.setString(1, id);
        
        boolean result = stat.execute();
        while(result){
            ResultSet rs = stat.getResultSet();
            String status = rs.getString("id");
            System.out.println(status);
        
        }
        
    }
    
}
