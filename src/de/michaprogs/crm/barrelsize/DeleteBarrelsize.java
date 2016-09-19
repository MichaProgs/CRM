package de.michaprogs.crm.barrelsize;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteBarrelsize {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteBarrelsize(int barrelsizeID){
		
		try{
			
			String stmt = "DELETE FROM barrelsize WHERE barrelsizeID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, barrelsizeID);
			ps.execute();
			
			System.out.println("Gebindegröße " + barrelsizeID + " wurde aus Datenbank gelöscht!");
			
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
