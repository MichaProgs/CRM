package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class InsertArticleAccessoris {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertArticleAccessoris(int articleID, ObservableList<ModelArticle> obsListArticleAccessoris){
		
		try{
			
			String stmt = "INSERT INTO articleAccessoris ( articleID, "
														+ "AccessorisArticleID)"
														+ "VALUES(?,?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			for(int index = 0; index < obsListArticleAccessoris.size(); index++){
				int i = 1;
				ps.setInt(i, articleID);
				i++;
				ps.setInt(i, obsListArticleAccessoris.get(index).getArticleID());
				i++;
				ps.execute();
			}
			
			System.out.println("Alle Zubehörartikel hinzugefügt!");
			
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
