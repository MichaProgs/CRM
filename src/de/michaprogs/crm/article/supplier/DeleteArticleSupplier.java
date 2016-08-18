package de.michaprogs.crm.article.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteArticleSupplier {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteArticleSupplier(	int _articleID){
		
		try{
			
			String stmt = "DELETE FROM articlesupplier WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			
			ps.execute();
			
			System.out.println("Alle Artikelhersteller zu Artikel " + _articleID + " gelöscht");
			
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
