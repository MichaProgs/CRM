package de.michaprogs.crm.documents.order;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateOrder {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateOrder(ModelOrder mo){
		
		try{
			
			String stmt = "UPDATE SUPPLIERORDER SET orderDate = ?,"
										+ "request = ?,"
										+ "requestDate = ?,"
										+ "notes = ?,"
										+ "supplierID = ?,"
										+ "clerkID = ?,"
										+ "total = ?,"
										+ "amountOfPositions = ? "
										
										+ "WHERE orderID = ?"; //ALWAYS LAST!
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mo.getOrderDate());
			i++;
			ps.setString(i, mo.getRequest());
			i++;
			ps.setString(i, mo.getRequestDate());
			i++;
			ps.setString(i, mo.getNotes());
			i++;
			ps.setInt(i, mo.getSupplierID());
			i++;
			ps.setInt(i, mo.getClerkID());
			i++;
			ps.setBigDecimal(i, mo.getTotal());
			i++;
			ps.setInt(i, mo.getAmountOfPositions());
			i++;
			
			//ALWAYS LAST!
			ps.setInt(i, mo.getOrderID());
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Bestellung " + mo.getOrderID() + " wurden in Datenbank gespeichert!");
			
			/* OFFER ARTICLE */
			String stmtDeleteOrderArticle = "DELETE FROM ORDERARTICLE WHERE orderID = ?";
			ps = con.prepareStatement(stmtDeleteOrderArticle);
			ps.setInt(1, mo.getOrderID());
			ps.execute();
			
			System.out.println("Bestell-Artikel zu Bestellung " + mo.getOrderID() + " wurden aus Datenbank gelöscht!");
			
			String stmtOrderArticle = "INSERT INTO ORDERARTICLE ("
													+ "orderID,"
													+ "articleID,"
													+ "amount,"
													+ "ek,"
													+ "vk,"
													+ "total"
													+ ")"
													+ "VALUES(?,?,?,?,?,?)"; //6
			
			for(int index = 0; index < mo.getObsListArticle().size(); index++){
				
				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtOrderArticle);
				int count = 1;
				ps.setInt(count, mo.getOrderID());
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
				
				System.out.println("Bestell-Artikel " + mo.getObsListArticle().get(index).getArticleID() + " " + mo.getObsListArticle().get(index).getDescription1() + " wurde der Datenbank hinzugefügt!");
				
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
