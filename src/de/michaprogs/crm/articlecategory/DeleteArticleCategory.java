package de.michaprogs.crm.articlecategory;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class DeleteArticleCategory {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteArticleCategory(ModelArticleCategory mac){
		
		try{
			
			String stmt = "DELETE FROM ARTICLECATEGORY WHERE ARTICLECATEGORYID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mac.getArticleCategoryID());
			ps.execute();
			
			new Notification(	"Artikel-Kategorie wurde gelöscht!", 
								mac.getArticleCategoryID() + " " + mac.getArticleCategory(), 
								NotificationType.SUCCESS);
			
			System.out.println("Kategorie " + mac.getArticleCategoryID() + " " + mac.getArticleCategory() + " wurde erfolgreich gelöscht!");
			
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
