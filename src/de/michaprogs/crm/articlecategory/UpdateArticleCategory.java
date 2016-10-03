package de.michaprogs.crm.articlecategory;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class UpdateArticleCategory {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateArticleCategory(ModelArticleCategory mac){
	
		try{
			
			String stmt = "UPDATE articleCategory SET articleCategory = ? "
												+ "WHERE articleCategoryID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mac.getArticleCategory());
			i++;
			ps.setInt(i, mac.getArticleCategoryID()); //ALWAYS LAST
			i++;
			ps.execute();
			
			new Notification(	"Artikel-Kategorie wurde bearbeitet!", 
								mac.getArticleCategoryID() + " " + mac.getArticleCategory(), 
								NotificationType.SUCCESS);
			
			System.out.println("Änderungen an Artikel-Kategorie " + mac.getArticleCategoryID() + " wurden in Datenbank gespeichert!");
			
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
