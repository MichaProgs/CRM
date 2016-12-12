package de.michaprogs.crm.documents.deliverybill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectDeliverybill {

	private ModelDeliverybill md;
	private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	private ObservableList<ModelDeliverybill> obsListDeliverybill = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * Constructor to select specific Deliverybill
	 * @param mo - ModelInvoice
	 * @param Selection - the art of selection 
	 */
	public SelectDeliverybill(ModelDeliverybill md, DeliverybillSelection deliverybillSelection){
		
		try{
			
			this.md = md;
			String stmt = "";
			
			con = new DBConnect().getConnection();
			
			if(deliverybillSelection.equals(DeliverybillSelection.SPECIFIC_DELIVERYBILL)){
				stmt = "SELECT * FROM Deliverybill WHERE DeliverybillID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, md.getDeliverybillID());
				i++;
			}else if(deliverybillSelection.equals(DeliverybillSelection.ALL_DELIVERYBILL_FROM_CUSTOMER)){
				stmt = "SELECT * FROM Deliverybill WHERE customerID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, md.getCustomerID());
				i++;
			}
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				md.setDeliverybillID(rs.getInt("DeliverybillID"));
				md.setDeliverybillDate(rs.getString("DeliverybillDate"));
				md.setRequest(rs.getString("request"));
				md.setRequestDate(rs.getString("requestDate"));
				md.setNotes(rs.getString("notes"));
				md.setCustomerID(rs.getInt("customerID"));
				md.setClerkID(rs.getInt("clerkID"));
				
				ModelClerk clerk = new SelectClerk(new ModelClerk(rs.getInt("clerkID")), Selection.SELECT_SPECIFIC).getModelClerk();
				
				obsListDeliverybill.add(new ModelDeliverybill(
					rs.getInt("deliverybillID"), 
					rs.getString("deliverybillDate"),
					rs.getString("request"), 
					rs.getString("requestDate"),
					clerk.getName(),
					rs.getInt("amountOfPositions"),
					rs.getBigDecimal("total"),
					rs.getBoolean("deliverystate")
				));
			
				System.out.println("Angebot " + md.getDeliverybillID() + " wurde aus Datenbank geladen!");
				
			}
			
			/* ARTICLE */
			if(deliverybillSelection.equals(DeliverybillSelection.SPECIFIC_DELIVERYBILL)){
				
				String stmtDeliverybillArticle = "SELECT * FROM DeliverybillArticle WHERE DeliverybillID = ?";
				ps = con.prepareStatement(stmtDeliverybillArticle);
				ps.setInt(1, md.getDeliverybillID());
				rs = ps.executeQuery();
				while(rs.next()){
					
					ModelArticle article = new SelectArticle(new ModelArticle(rs.getInt("articleID"))).getModelArticle();
					
					obsListArticle.add(new ModelArticle(
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
					
					md.setObsListArticle(obsListArticle);
							
					System.out.println("Angebots-Artikel " + rs.getInt("articleID") + " wurde aus Datenbank geladen!");
					
				}
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public enum DeliverybillSelection{
		ALL_DELIVERYBILL_FROM_CUSTOMER, SPECIFIC_DELIVERYBILL
	}
	
		
	/*
	 * GETTER & SETTER
	 */
	public ObservableList<ModelArticle> getObsListArticle(){
		return obsListArticle;
	}
	
	public ObservableList<ModelDeliverybill> getObsListDeliverybill(){
		return obsListDeliverybill;
	}
	
	public ModelDeliverybill getModelDeliverybill(){
		return md;
	}
	
}
