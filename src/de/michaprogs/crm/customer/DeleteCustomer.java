package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class DeleteCustomer {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteCustomer(int _customerID){
		
		try{
			
			String stmt = "DELETE FROM customer WHERE customerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _customerID);
			ps.execute();
			
			/* CONTACTS */
			new DeleteCustomerContacts(_customerID);
			
			new Notification("Gelöscht!", "Der Kunde " + _customerID + " wurde erfolgreich gelöscht!" , NotificationType.SUCCESS);
			System.out.println("Kunde " + _customerID + " wurde erfolgreich gelöscht!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
