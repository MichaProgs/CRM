package de.michaprogs.crm.barrelsize;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateBarrelsize {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateBarrelsize(ModelBarrelsize mb){
	
		try{
			
			String stmt = "UPDATE barrelsize SET barrelsize = ? "
										+ "WHERE barrelsizeID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mb.getBarrelsize());
			i++;
			ps.setInt(i, mb.getBarrelsizeID()); //ALWAYS LAST
			i++;
			ps.execute();
			
			System.out.println("Änderungen an Gebindegröße " + mb.getBarrelsizeID() + " wurden in Datenbank gespeichert!");
			
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
