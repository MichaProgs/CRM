package de.michaprogs.crm.documents.offer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateOffer {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateOffer(ModelOffer mo){
		
		try{
			
			String stmt = "UPDATE offer SET offerDate = ?,"
										+ "request = ?,"
										+ "requestDate = ?,"
										+ "notes = ?,"
										+ "customerID = ?,"
										+ "clerkID = ?,"
										+ "amountOfPositions = ?,"
										+ "total = ? "
										
										+ "WHERE offerID = ?"; //ALWAYS LAST!
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mo.getOfferDate());
			i++;
			ps.setString(i, mo.getRequest());
			i++;
			ps.setString(i, mo.getRequestDate());
			i++;
			ps.setString(i, mo.getNotes());
			i++;
			ps.setInt(i, mo.getCustomerID());
			i++;
			ps.setInt(i, mo.getClerkID());
			i++;
			ps.setInt(i, mo.getAmountOfPositions());
			i++;
			ps.setBigDecimal(i, mo.getTotal());
			i++;
			
			//ALWAYS LAST!
			ps.setInt(i, mo.getOfferID());
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Angebot " + mo.getOfferID() + " wurden in Datenbank gespeichert!");
			
			/* OFFER ARTICLE */
			String stmtDeleteOfferArticle = "DELETE FROM offerarticle WHERE offerID = ?";
			ps = con.prepareStatement(stmtDeleteOfferArticle);
			ps.setInt(1, mo.getOfferID());
			ps.execute();
			
			System.out.println("Angebots-Artikel zu Angebot " + mo.getOfferID() + " wurden aus Datenbank gelöscht!");
			
			String stmtOfferArticle = "INSERT INTO offerarticle ("
													+ "offerID,"
													+ "articleID,"
													+ "amount,"
													+ "ek,"
													+ "vk,"
													+ "total"
													+ ")"
													+ "VALUES(?,?,?,?,?,?)"; //6
			
			for(int index = 0; index < mo.getObsListArticle().size(); index++){
				
				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtOfferArticle);
				int count = 1;
				ps.setInt(count, mo.getOfferID());
				count++;
				ps.setInt(count, mo.getObsListArticle().get(index).getArticleID());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getAmount());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getEk());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getVk());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getTotal());
				count++;
				
				ps.execute();
				
				System.out.println("Angebots-Artikel " + mo.getObsListArticle().get(index).getArticleID() + " " + mo.getObsListArticle().get(index).getDescription1() + " wurde der Datenbank hinzugefügt!");
				
			}
			
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
