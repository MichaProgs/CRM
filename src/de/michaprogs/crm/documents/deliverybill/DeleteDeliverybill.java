package de.michaprogs.crm.documents.deliverybill;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteDeliverybill {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteDeliverybill(ModelDeliverybill md){
		
		try{
			
			String stmt = "DELETE FROM Deliverybill WHERE DeliverybillID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, md.getDeliverybillID());
			i++;
			
			ps.execute();
			
			System.out.println("Lieferschein " + md.getDeliverybillID() + " wurde aus Datenbank gelöscht!");
			
			/* ARTICLE */
			String stmtDeliverybillArticle = "DELETE FROM Deliverybillarticle WHERE DeliverybillID = ?";
				
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmtDeliverybillArticle);
			int count = 1;
			ps.setInt(count, md.getDeliverybillID());
			count++;
			
			ps.execute();
			
			System.out.println("Lieferschein-Artikel von Lieferschein " + md.getDeliverybillID() + " wurden aus Datenbank gelöscht!");
		
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
