package de.michaprogs.crm.customer.category;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class InsertCustomerCategory {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertCustomerCategory(ModelCustomerCategory mcc){
		
		try{
			
			String stmt = "INSERT INTO CUSTOMERCATEGORY (customercategory) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, mcc.getCustomerCategory());
			ps.execute();
			
			new Notification(	"Kunden-Kategorie wurde Gespeichert!", 
								mcc.getCustomerCategoryID() + " " + mcc.getCustomerCategory(), 
								NotificationType.SUCCESS);
			
			System.out.println("Kunden-Kategorie " + mcc.getCustomerCategoryID() + " " + mcc.getCustomerCategory() + " wurde zur Datenbank hinzugefügt");
			
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
