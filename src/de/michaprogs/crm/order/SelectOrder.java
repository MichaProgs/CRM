package de.michaprogs.crm.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.database.DBConnect;

public class SelectOrder {

	private ModelOrder mo;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectOrder(ModelOrder mo){
		
		try{
		
			this.mo = mo;
			
			String stmt = "SELECT * FROM supplierorder WHERE orderID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mo.getOrderID());
			rs = ps.executeQuery();
			while(rs.next()){
				
				mo.setOrderID(rs.getInt("orderID"));
				mo.setOrderDate(rs.getString("orderDate"));
				mo.setRequest(rs.getString("request"));
				mo.setRequestDate(rs.getString("requestID"));
				mo.setNotes(rs.getString("notes"));
				mo.setClerkID(rs.getInt("clerkID"));
				mo.setSupplierID(rs.getInt("supplierID"));
				
			}
			
			stmt = "SELECT * FROM orderarticle WHERE orderID = ?";
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mo.getOrderID());
			rs = ps.executeQuery();
			while(rs.next()){
				
				ModelArticle article = new SelectArticle(new ModelArticle(rs.getInt("articleID"))).getModelArticle();
				
				mo.getObsListArticle().add(
						new ModelArticle(
							rs.getInt("articleID"), 
							article.getDescription1(), 
							article.getDescription2(), 
							article.getBarrelsize(), 
							article.getBolting(), 
							rs.getBigDecimal("amount"), 
							article.getAmountUnit(), 
							rs.getBigDecimal("vk"), 
							rs.getBigDecimal("ek"), 
							article.getPriceUnit(), 
							rs.getBigDecimal("total"), 
							article.getTax()));
				
			}
			
			System.out.println("Die Bestellung " + mo.getOrderID() + " wurde aus Datenbank geladen!");			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}	
	
	public ModelOrder getModelOrder(){
		return mo;
	}
	
}
