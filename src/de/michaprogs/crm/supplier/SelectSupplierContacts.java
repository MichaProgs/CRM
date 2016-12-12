package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.database.DBConnect;

public class SelectSupplierContacts {
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectSupplierContacts(ModelSupplier ms){
		
		try{
			
			String stmt = "SELECT * FROM suppliercontacts WHERE supplierID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, ms.getSupplierID());
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ms.getObsListContacts().add(new ModelContact(
					rs.getInt("supplierID"), 
					rs.getString("salutation"), 
					rs.getString("name"), 
					rs.getString("phone"),
					rs.getString("mobile"),
					rs.getString("fax"), 
					rs.getString("email"), 
					rs.getString("department")
				));
				
			}
			
			System.out.println("Alle Kontakte zu Lieferant " + ms.getSupplierID() + " aus Datenbank geladen!");
			
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
