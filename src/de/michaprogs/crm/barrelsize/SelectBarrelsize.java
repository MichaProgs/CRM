package de.michaprogs.crm.barrelsize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectBarrelsize {

	private ModelBarrelsize mb;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectBarrelsize(ModelBarrelsize mb){
		
		try{
			
			this.mb = mb;
			
			String stmt = "SELECT * FROM barrelsize";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				mb.getObsListBarrelsizes().add(new ModelBarrelsize(
					rs.getInt("barrelsizeID"),
					rs.getString("barrelsize")
				));
				
			}
			
			System.out.println("Alle Gebindegrößen aus Datenbank geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	public ModelBarrelsize getModelBarrelsize(){
		return mb;
	}
	
}
