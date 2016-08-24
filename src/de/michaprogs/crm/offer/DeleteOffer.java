package de.michaprogs.crm.offer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteOffer {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteOffer(ModelOffer mo){
		
		try{
			
			String stmt = "DELETE FROM offer WHERE offerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, mo.getOfferID());
			i++;
			
			ps.execute();
			
			System.out.println("Angebot " + mo.getOfferID() + " wurde aus Datenbank gelöscht!");
			
			/* OFFER ARTICLE */
			String stmtOfferArticle = "DELETE FROM offerarticle WHERE offerID = ?";
				
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmtOfferArticle);
			int count = 1;
			ps.setInt(count, mo.getOfferID());
			count++;
			
			ps.execute();
			
			System.out.println("Angebots-Artikel von Angebot " + mo.getOfferID() + " wurden aus Datenbank gelöscht!");
		
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
