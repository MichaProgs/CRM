package de.michaprogs.crm.bolting;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class UpdateBolting {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateBolting(ModelBolting mb){
		
		try{
			
			String stmt = "UPDATE bolting SET bolting = ? "
										+ "WHERE boltingID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mb.getBolting());
			i++;
			ps.setInt(i, mb.getBoltingID()); //ALWAYS LAST
			i++;
			ps.execute();
			
			new Notification(	"Verschraubung wurde bearbeitet!", 
								mb.getBoltingID() + " " + mb.getBolting(), 
								NotificationType.SUCCESS);
			
			System.out.println("Änderungen an Verschraubung " + mb.getBoltingID() + " wurden in Datenbank gespeichert!");
			
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
