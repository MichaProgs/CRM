package de.michaprogs.crm.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.database.DBConnect;

public class SelectOrder {

	private ModelOrder mo;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectOrder(ModelOrder mo, OrderSelection orderSelection){
		
		try{
			
			this.mo = mo;			
			String stmt = "";
			
			con = new DBConnect().getConnection();
			
			if(orderSelection.equals(OrderSelection.SPECIFIC_ORDER)){
				stmt = "SELECT * FROM SUPPLIERORDER WHERE orderID = ? AND supplierID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, mo.getOrderID());
				i++;
				ps.setInt(i, mo.getSupplierID());
				i++;
			}else if(orderSelection.equals(OrderSelection.ALL_ORDER_TO_SUPPLIER)){
				stmt = "SELECT * FROM SUPPLIERORDER WHERE supplierID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, mo.getSupplierID());
				i++;
			}
				
			rs = ps.executeQuery();
			while(rs.next()){
				
				mo.setOrderID(rs.getInt("orderID"));
				mo.setOrderDate(rs.getString("orderDate"));
				mo.setRequest(rs.getString("request"));
				mo.setRequestDate(rs.getString("requestDate"));
				mo.setNotes(rs.getString("notes"));
				mo.setClerkID(rs.getInt("clerkID"));
				mo.setSupplierID(rs.getInt("supplierID"));
				
				ModelClerk clerk = new SelectClerk(new ModelClerk(rs.getInt("clerkID")), Selection.SELECT_SPECIFIC).getModelClerk();
				
				mo.getObsListSupplierOrder().add(new ModelOrder(
					rs.getInt("orderID"),
					rs.getString("orderDate"), 
					rs.getString("request"),
					rs.getString("requestDate"),
					clerk.getName(),
					rs.getBigDecimal("total"),
					rs.getInt("amountOfPositions")
					)
				);
				
			}
			
			/* ORDER POSITIONS */
			if(orderSelection.equals(OrderSelection.SPECIFIC_ORDER)){
				
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
				
				System.out.println("Die Bestellung " + mo.getOrderID() + " wurde aus Datenbank geladen!");			
			
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}	
	
	public enum OrderSelection{
		ALL_ORDER_TO_SUPPLIER, SPECIFIC_ORDER
	}
	
	public ModelOrder getModelOrder(){
		return mo;
	}
	
}
