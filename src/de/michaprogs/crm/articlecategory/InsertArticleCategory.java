package de.michaprogs.crm.articlecategory;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class InsertArticleCategory {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertArticleCategory(ModelArticleCategory mac){
		
		try{
			
			String stmt = "INSERT INTO ARTICLECATEGORY (articlecategory) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, mac.getArticleCategory());
			ps.execute();
			
			new Notification(	"Gespeichert!", 
								"Die Kategorie " + mac.getArticleCategory() + " wurde erfolgreich gespeichert!", 
								NotificationType.SUCCESS);
			
			System.out.println("Gebindegröße " + mac.getArticleCategory() + " zur Datenbank hinzugefügt");
			
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
