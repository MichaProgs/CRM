package de.michaprogs.crm.barrelsize;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class DeleteBarrelsize {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteBarrelsize(ModelBarrelsize mb){
		
		try{
			
			String stmt = "DELETE FROM barrelsize WHERE barrelsizeID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mb.getBarrelsizeID());
			ps.execute();
			
			new Notification(	"Gebindegröße wurde gelöscht!", 
								mb.getBarrelsizeID() + " " + mb.getBarrelsize(), 
								NotificationType.SUCCESS);
			
			System.out.println("Gebindegröße " + mb.getBarrelsizeID() + " " + mb.getBarrelsize() + " wurde aus Datenbank gelöscht!");
			
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
