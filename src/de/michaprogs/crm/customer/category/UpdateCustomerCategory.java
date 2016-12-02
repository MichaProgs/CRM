package de.michaprogs.crm.customer.category;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class UpdateCustomerCategory {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateCustomerCategory(ModelCustomerCategory mcc){
	
		try{
			
			String stmt = "UPDATE CUSTOMERCATEGORY SET customerCategory = ? "
												+ "WHERE customerCategoryID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mcc.getCustomerCategory());
			i++;
			ps.setInt(i, mcc.getCustomerCategoryID()); //ALWAYS LAST
			i++;
			ps.execute();
			
			new Notification(	"Kunden-Kategorie wurde bearbeitet!", 
								mcc.getCustomerCategoryID() + " " + mcc.getCustomerCategory(), 
								NotificationType.SUCCESS);
			
			System.out.println("Änderungen an Kunden-Kategorie " + mcc.getCustomerCategoryID() + " wurden in Datenbank gespeichert!");
			
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
