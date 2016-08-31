package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteSupplierContacts {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteSupplierContacts(	int _supplierID){
		
		try{
			
			String stmt = "DELETE FROM suppliercontacts WHERE supplierID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, _supplierID);
			i++;
			
			ps.execute();
			
			System.out.println("Alle Kontakte von Lieferant " + _supplierID + " wurden aus der Datenbank gelöscht!");
			
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
