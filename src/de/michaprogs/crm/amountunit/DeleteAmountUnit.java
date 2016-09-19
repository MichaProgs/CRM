package de.michaprogs.crm.amountunit;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteAmountUnit {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteAmountUnit(int amountUnitID){
		
		try{
			
			String stmt = "DELETE FROM amountUnit WHERE amountUnitID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, amountUnitID);
			ps.execute();
			
			System.out.println("Mengeneinheit " + amountUnitID + " aus Datenbank gelöscht!");
			
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
