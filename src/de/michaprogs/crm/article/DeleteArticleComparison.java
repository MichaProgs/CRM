package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class DeleteArticleComparison {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteArticleComparison(int articleID, ObservableList<ModelArticle> obsListArticleComparison){
		
		try{
			
			String stmt = "DELETE FROM articlecomparison WHERE articleID = ?";
			String stmt2 = "DELETE FROM articlecomparison WHERE comparisonarticleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
//			for(int i = 0; i < obsListArticleComparison.size(); i++){
//				ps.setInt(1, obsListArticleComparison.get(i).getArticleID());
//				ps.execute();
//			}
			
			ps.setInt(1, articleID);
			ps.execute();
			
//			ps = con.prepareStatement(stmt2);
//			for(int i = 0; i < obsListArticleComparison.size(); i++){
//				ps.setInt(1, obsListArticleComparison.get(i).getArticleID());
//				ps.execute();
//			}
//			
//			ps.setInt(1, articleID);
//			ps.execute();
			
			ps = con.prepareStatement(stmt2);
			ps.setInt(1, articleID);
			ps.execute();
			
			System.out.println("Alle Alternativartikel gelöscht!");
			
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
