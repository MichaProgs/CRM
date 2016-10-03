package de.michaprogs.crm.bolting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectBolting {

	private ModelBolting mb;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectBolting(ModelBolting mb){
		
		try{
			
			this.mb = mb;
			
			String stmt = "SELECT * FROM bolting";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				mb.getObsListBoltings().add(new ModelBolting(
					rs.getInt("boltingID"),
					rs.getString("bolting")
				));
				
			}
			
			System.out.println("Alle Verschraubungen aus Datenbank geladen");
			
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
	
	public ModelBolting getModelBolting(){
		return mb;
	}
	
}
