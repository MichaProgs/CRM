package de.michaprogs.crm.barrelsize;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class InsertBarrelsize {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertBarrelsize(ModelBarrelsize mb){
		
		try{
			
			String stmt = "INSERT INTO barrelsize (barrelsize) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, mb.getBarrelsize());
			ps.execute();
			
			new Notification(	"Gebindegröße wurde gespeichert!", 
								mb.getBarrelsize(), 
								NotificationType.SUCCESS);
			
			System.out.println("Gebindegröße " + mb.getBarrelsize() + " zur Datenbank hinzugefügt");
			
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
