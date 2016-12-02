package de.michaprogs.crm.customer.category;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class DeleteCustomerCategory {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteCustomerCategory(ModelCustomerCategory mcc){
		
		try{
			
			String stmt = "DELETE FROM CUSTOMERCATEGORY WHERE CUSTOMERCATEGORYID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mcc.getCustomerCategoryID());
			ps.execute();
			
			new Notification(	"Kunden-Kategorie wurde gelöscht!", 
								mcc.getCustomerCategoryID() + " " + mcc.getCustomerCategory(), 
								NotificationType.SUCCESS);
			
			System.out.println("Kategorie " + mcc.getCustomerCategoryID() + " " + mcc.getCustomerCategory() + " wurde erfolgreich gelöscht!");
			
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
