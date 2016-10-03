package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class DeleteArticleAccessoris {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteArticleAccessoris(int articleID, ObservableList<ModelArticle> obsListArticleAccessoris){
		
		try{
			
			String stmt = "DELETE FROM articleAccessoris WHERE articleID = ?";
			String stmt2 = "DELETE FROM articleAccessoris WHERE AccessorisarticleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			ps.setInt(1, articleID);
			ps.execute();
			
			ps = con.prepareStatement(stmt2);
			ps.setInt(1, articleID);
			ps.execute();
			
			System.out.println("Alle Zubehörartikel gelöscht!");
			
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
