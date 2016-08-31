package de.michaprogs.crm.contact;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteSupplierContact {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteSupplierContact(int supplierID){
		
		try{
			
			String stmt = "DELETE FROM suppliercontacts WHERE supplierID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, supplierID);
			i++;
			
			ps.execute();
			
			System.out.println("Alle Lieferantekontakte von Lieferant " + supplierID + " wurden aus Datenbank gelöscht!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
