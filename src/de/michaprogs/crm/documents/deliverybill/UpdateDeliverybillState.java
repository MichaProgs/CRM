package de.michaprogs.crm.documents.deliverybill;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateDeliverybillState {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateDeliverybillState(ModelDeliverybill md){
		
		try{
			
			String stmt = "UPDATE Deliverybill SET deliverystate = ? "
											+ "WHERE DeliverybillID = ?"; //ALWAYS LAST!
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setBoolean(i, md.getDeliverystate());
			i++;
			
			//ALWAYS LAST!
			ps.setInt(i, md.getDeliverybillID());
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Lieferstatus von Lieferschein " + md.getDeliverybillID() + " wurden in Datenbank gespeichert!");
			
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
