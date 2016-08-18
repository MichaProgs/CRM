package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteArticle {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteArticle(int _articleID){
		
		try{
			
			String stmt = "DELETE FROM article WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _articleID);
			ps.execute();
			
			System.out.println("Artikel " + _articleID + " wurde aus der Datenbank gel�scht");
			
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