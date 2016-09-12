package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.article.supplier.DeleteArticleSupplier;
import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

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
			
			/* ARTICLE SUPPLIER */
			new DeleteArticleSupplier(_articleID);
			
			new Notification(	"Gelöscht!", 
								"Artikel " + _articleID + " wurde erfolgreich gelöscht!", 
								NotificationType.SUCCESS);
			
			System.out.println("Artikel " + _articleID + " wurde aus der Datenbank gelöscht");
			
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
