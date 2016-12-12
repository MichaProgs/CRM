package de.michaprogs.crm.documents.order;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteOrder {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteOrder(ModelOrder mo){
		
		try{
			
			String stmt = "DELETE FROM SUPPLIERORDER WHERE orderID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, mo.getOrderID());
			i++;
			
			ps.execute();
			
			System.out.println("Bestellung " + mo.getOrderID() + " wurde aus Datenbank gelöscht!");
			
			/* OFFER ARTICLE */
			String stmtOfferArticle = "DELETE FROM ORDERARTICLE WHERE orderID = ?";
				
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmtOfferArticle);
			int count = 1;
			ps.setInt(count, mo.getOrderID());
			count++;
			
			ps.execute();
			
			System.out.println("Bestell-Artikel von Angebot " + mo.getOrderID() + " wurden aus Datenbank gelöscht!");
		
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
