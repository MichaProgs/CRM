package de.michaprogs.crm.clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteClerk {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteClerk(int clerkID){
		
		try{
			
			String stmt = "DELETE FROM clerk WHERE clerkID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, clerkID);
			i++;

			ps.execute();
			
			System.out.println("Sachbearbeiter " + clerkID + " wurde aus Datenbank gelöscht!");
			
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
