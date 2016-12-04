package de.michaprogs.crm.offer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectOffer {

	private ModelOffer mo;
	private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	private ObservableList<ModelOffer> obsListOffer = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * Constructor to select specific offer
	 * @param mo - ModelOffer
	 * @param Selection - the art of selection 
	 */
	public SelectOffer(ModelOffer mo, OfferSelection offerSelection){
		
		try{
			
			this.mo = mo;
			String stmt = "";
			
			con = new DBConnect().getConnection();
			
			if(offerSelection.equals(OfferSelection.SPECIFIC_OFFER)){
				stmt = "SELECT * FROM offer WHERE offerID = ? AND customerID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, mo.getOfferID());
				i++;
				ps.setInt(i, mo.getCustomerID());
				i++;
			}else if(offerSelection.equals(OfferSelection.ALL_OFFER_FROM_CUSTOMER)){
				stmt = "SELECT * FROM offer WHERE customerID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, mo.getCustomerID());
				i++;
			}
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				mo.setOfferID(rs.getInt("offerID"));
				mo.setOfferDate(rs.getString("offerDate"));
				mo.setRequest(rs.getString("request"));
				mo.setRequestDate(rs.getString("requestDate"));
				mo.setNotes(rs.getString("notes"));
				mo.setCustomerID(rs.getInt("customerID"));
				mo.setClerkID(rs.getInt("clerkID"));
				
				obsListOffer.add(new ModelOffer(
					rs.getInt("offerID"), 
					rs.getString("offerDate"), 
					rs.getInt("customerID"), 
					rs.getString("request"), 
					rs.getString("requestDate")
				));
			
				System.out.println("Angebot " + mo.getOfferID() + " wurde aus Datenbank geladen!");
				
			}
			
			/* OFFER ARTICLE */
			if(offerSelection.equals(OfferSelection.SPECIFIC_OFFER)){
				
				String stmtOfferArticle = "SELECT * FROM offerArticle WHERE offerID = ?";
				ps = con.prepareStatement(stmtOfferArticle);
				ps.setInt(1, mo.getOfferID());
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
					
					mo.setObsListArticle(obsListArticle);
							
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
	
	public enum OfferSelection{
		ALL_OFFER_FROM_CUSTOMER, SPECIFIC_OFFER
	}
	
		
	/*
	 * GETTER & SETTER
	 */
	public ObservableList<ModelArticle> getObsListArticle(){
		return obsListArticle;
	}
	
	public ObservableList<ModelOffer> getObsListOffer(){
		return obsListOffer;
	}
	
	public ModelOffer getModelOffer(){
		return mo;
	}
	
}
