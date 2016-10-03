package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class InsertArticleComparison {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertArticleComparison(int articleID, ObservableList<ModelArticle> obsListArticleComparison){
		
		try{
			
			String stmt = "INSERT INTO articlecomparison ( articleID, "
														+ "comparisonArticleID)"
														+ "VALUES(?,?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			for(int index = 0; index < obsListArticleComparison.size(); index++){
				int i = 1;
				ps.setInt(i, articleID);
				i++;
				ps.setInt(i, obsListArticleComparison.get(index).getArticleID());
				i++;
				ps.execute();
			}
			
//			for(int index = 0; index < obsListArticleComparison.size(); index++){
//				int i = 1;
//				ps.setInt(i, obsListArticleComparison.get(index).getArticleID());
//				i++;
//				ps.setInt(i, articleID);
//				i++;
//				ps.execute();
//			}
			
			System.out.println("Alle Alternativartikel hinzugefügt!");
			
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
