package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteSupplier {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteSupplier(	int _supplierID){
		
		try{
			
			String stmt = "DELETE FROM supplier WHERE supplierID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, _supplierID);
			i++;
			
			ps.execute();
			
			System.out.println("Lieferant " + _supplierID + " wurde aus der Datenbank gelöscht");
			
			/* CONTACTS */
			new DeleteSupplierContacts(_supplierID);
			
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
