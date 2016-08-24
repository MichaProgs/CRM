package de.michaprogs.crm.offer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertOffer {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertOffer(ModelOffer mo){
		
		try{
			
			String stmt = "INSERT INTO offer ("
					+ "offerID,"
					+ "offerDate,"
					+ "request,"
					+ "requestDate,"
					+ "notes,"
					+ "customerID,"
					+ "clerkID"
					+ ")"
					+ "VALUES(?,?,?,?,?,?,?)"; //7
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, mo.getOfferID());
			i++;
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
			
			ps.execute();
			
			System.out.println("Angebot " + mo.getOfferID() + " wurde der Datenbank hinzugefügt!");
			
			/* OFFER ARTICLE */
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
				ps.setDouble(count, mo.getObsListArticle().get(index).getAmount());
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
