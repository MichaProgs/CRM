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
	
	public DeleteArticle(ModelArticle ma){
		
		try{
			
			String stmt = "DELETE FROM article WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, ma.getArticleID());
			ps.execute();
			
			/* ARTICLE SUPPLIER */
			new DeleteArticleSupplier(ma.getArticleID());
			
			new Notification(	"Artikel wurde gelöscht!", 
								ma.getArticleID() + " " + ma.getDescription1(), 
								NotificationType.SUCCESS);
			
			System.out.println("Artikel " + ma.getArticleID() + " " + ma.getDescription1() + " wurde aus der Datenbank gelöscht");
			
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
