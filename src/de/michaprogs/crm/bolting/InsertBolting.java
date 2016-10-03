package de.michaprogs.crm.bolting;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class InsertBolting {
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertBolting(ModelBolting mb){
		
		try{
			
			String stmt = "INSERT INTO bolting (bolting) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, mb.getBolting());
			ps.execute();
			
			new Notification(	"Verschraubung wurde gespeichert!", 
								mb.getBolting(), 
								NotificationType.SUCCESS);
			
			System.out.println("Verschraubung " + mb.getBolting() + " zur Datenbank hinzugefügt");
			
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
