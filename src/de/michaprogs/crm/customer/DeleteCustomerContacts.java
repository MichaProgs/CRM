package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteCustomerContacts {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteCustomerContacts(int _customerID){
		
		try{
			
			String stmt = "DELETE FROM customercontacts WHERE customerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _customerID);
			ps.execute();
			
			System.out.println("Alle Kontakte von " + _customerID + " wurde aus Datenbank gelöscht!" );
			
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
