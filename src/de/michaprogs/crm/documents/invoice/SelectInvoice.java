package de.michaprogs.crm.documents.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.database.DBConnect;

public class SelectInvoice {

	private ModelInvoice model;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * Constructor to select specific Invoice
	 * @param model
	 * @param Selection - the art of selection 
	 */
	public SelectInvoice(ModelInvoice model, InvoiceSelection invoiceSelection){
		
		System.out.println(model.getCustomerID());
		
		try{
			
			this.model = model;
			String stmt = "";
			
			con = new DBConnect().getConnection();
			
			if(invoiceSelection.equals(InvoiceSelection.SPECIFIC_INVOICE)){
				stmt = "SELECT * FROM INVOICE WHERE invoiceID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, model.getInvoiceID());
				i++;
			}else if(invoiceSelection.equals(InvoiceSelection.ALL_INVOICE_FROM_CUSTOMER)){
				stmt = "SELECT * FROM INVOICE WHERE customerID = ?";
				ps = con.prepareStatement(stmt);
				int i = 1;
				ps.setInt(i, model.getCustomerID());
				i++;
			}
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				model.setInvoiceID(rs.getInt("invoiceID"));
				model.setInvoiceDate(rs.getString("invoiceDate"));
				model.setDeliverybillID(rs.getInt("deliverybillID"));
				model.setDeliveryDate(rs.getString("deliveryDate"));
				model.setNotes(rs.getString("notes"));
				model.setCustomerID(rs.getInt("customerID"));
				model.setClerkID(rs.getInt("clerkID"));
				
				ModelClerk clerk = new SelectClerk(new ModelClerk(rs.getInt("clerkID")), Selection.SELECT_SPECIFIC).getModelClerk();
				
				model.getObsListCustomerInvoice().add(new ModelInvoice(
					rs.getInt("invoiceID"), 
					rs.getString("invoiceDate"),
					rs.getInt("deliverybillID"), 
					rs.getString("deliveryDate"),
					clerk.getName(),
					rs.getInt("amountOfPositions"),
					rs.getBigDecimal("total")
				));
			
				System.out.println("Rechnung " + model.getInvoiceID() + " wurde aus Datenbank geladen!");
				
			}
			
			/* ARTICLE */
			if(invoiceSelection.equals(InvoiceSelection.SPECIFIC_INVOICE)){
				
				String stmtPositions = "SELECT * FROM INVOICEARTICLE WHERE invoiceID = ?";
				ps = con.prepareStatement(stmtPositions);
				ps.setInt(1, model.getInvoiceID());
				rs = ps.executeQuery();
				while(rs.next()){
					
					ModelArticle article = new SelectArticle(new ModelArticle(rs.getInt("articleID"))).getModelArticle();
					
					model.getObsListPositions().add(new ModelArticle(
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
							
					System.out.println("Rechnungs-Artikel " + rs.getInt("articleID") + " wurde aus Datenbank geladen!");
					
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
	
	public enum InvoiceSelection{
		ALL_INVOICE_FROM_CUSTOMER, SPECIFIC_INVOICE
	}
	
		
	/*
	 * GETTER & SETTER
	 */
	public ModelInvoice getModelInvoice(){
		return model;
	}
	
}
