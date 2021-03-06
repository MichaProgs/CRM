package de.michaprogs.crm.bolting;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class DeleteBolting {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteBolting(ModelBolting mb){
		
		try{
			
			String stmt = "DELETE FROM bolting WHERE boltingID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mb.getBoltingID());
			ps.execute();
			
			new Notification(	"Verschraubung wurde gel�scht!", 
								mb.getBoltingID() + " " + mb.getBolting(), 
								NotificationType.SUCCESS);
			
			System.out.println("Verschraubung " + mb.getBoltingID() + " " + mb.getBolting() + " wurde aus Datenbank gel�scht!");
			
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
