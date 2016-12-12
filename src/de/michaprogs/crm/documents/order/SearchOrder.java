package de.michaprogs.crm.documents.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.database.DBConnect;

public class SearchOrder {
	
	private ModelOrder mo;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchOrder(ModelOrder mo){
		
		try{
			
			this.mo = mo;
			
			String stmt = "SELECT * FROM supplierorder WHERE   orderID LIKE ? AND "
															+ "orderDate LIKE ? AND "
															+ "supplierID LIKE ? AND "
															+ "request LIKE ? AND "
															+ "requestDate LIKE ? AND "
															+ "clerkID LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i =  1;
			if(mo.getOrderID() == 0){
				ps.setString(i, "%");
			}else{
				ps.setString(i, mo.getOrderID() + "%");
			}
			i++;
			if(mo.getOrderDate().equals("null")){
				mo.setOrderDate("");
			}
			ps.setString(i, mo.getOrderDate() + "%");
			i++;
			if(mo.getSupplierID() == 0){
				ps.setString(i, "%");
			}else{
				ps.setString(i, mo.getSupplierID() + "%");
			}
			i++;
			ps.setString(i, mo.getRequest() + "%");
			i++;
			if(mo.getRequestDate().equals("null")){
				mo.setRequestDate("");
			}
			ps.setString(i, mo.getRequestDate() + "%");
			i++;
			if(mo.getClerkID() == 0){
				ps.setString(i, "%");
			}else{
				ps.setString(i, mo.getClerkID() + "%");
			}
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				mo.getObsListOrderSearch().add(new ModelOrder(
					rs.getInt("orderID"),
					rs.getString("orderDate"),
					rs.getString("request"),
					rs.getString("requestDate"),
					rs.getInt("supplierID"),
					rs.getInt("clerkID")
				));
				
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
							article.getTax(),
							article.getLongtext()
						));
				
			}
			
			System.out.println("Alle Bestellungen mit übereinstimmenden Suchkriterien wurden geladen!");			
			
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
