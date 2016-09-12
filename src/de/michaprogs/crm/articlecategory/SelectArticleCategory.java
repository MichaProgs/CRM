package de.michaprogs.crm.articlecategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectArticleCategory {

	private ModelArticleCategory mac;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectArticleCategory(ModelArticleCategory mac){
		
		try{
			
			this.mac = mac;
			
			String stmt = "SELECT * FROM ARTICLECATEGORY";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				/* TABLES */
				mac.getObsListArticleCategories().add(new ModelArticleCategory(
														rs.getInt("articlecategoryid"),
														rs.getString("articlecategory"))
														);
				
				/* COMBO BOXES */
				mac.getObsListArticleCategoriesComboBox().add(rs.getString("articlecategory"));
				
			}
			
			System.out.println("Alle Gebindegrößen aus Datenbank geladen!");
			
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
	
	public ModelArticleCategory getModelArticleCategory(){
		return mac;
	}
	
}
