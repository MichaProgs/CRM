package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.database.DBConnect;

public class SelectCustomerContacts {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectCustomerContacts(ModelCustomer mc){
		
		try{
			
			String stmt = "SELECT * FROM customercontacts WHERE customerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mc.getCustomerID());
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				mc.getObsListContacts().add(new ModelContact(
					rs.getInt("customerID"), 
					rs.getString("salutation"), 
					rs.getString("name"), 
					rs.getString("phone"), 
					rs.getString("mobile"),
					rs.getString("fax"), 
					rs.getString("email"), 
					rs.getString("department")
				));
				
			}
			
			System.out.println("Alle Kontakte zu Kunde " + mc.getCustomerID() + " " + mc.getName1() + " aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
