package de.michaprogs.crm.bolting;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteBolting {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteBolting(int boltingID){
		
		try{
			
			String stmt = "DELETE FROM bolting WHERE boltingID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, boltingID);
			ps.execute();
			
			System.out.println("Verschraubung " + boltingID + " wurde aus Datenbank gelöscht!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
}
