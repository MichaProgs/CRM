package de.michaprogs.crm.documents.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SearchInvoice {
	
	private ModelInvoice model;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchInvoice(ModelInvoice model){
		
		try{
			
			this.model = model;
			
			String stmt = "SELECT * FROM INVOICE WHERE   invoiceID LIKE ? AND "
															+ "invoiceDate LIKE ? AND "
															+ "customerID LIKE ? AND "
															+ "deliverybillID LIKE ? AND "
															+ "deliveryDate LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			if(model.getInvoiceID() == 0){
				ps.setString(i, "%");
			}else{
				ps.setString(i, model.getInvoiceID() + "%");
			}
			i++;
			if(model.getInvoiceDate().equals("null")){
				model.setInvoiceDate("");
			}
			ps.setString(i, model.getInvoiceDate() + "%");
			i++;
			if(model.getCustomerID() == 0){
				ps.setString(i, "%");
			}else{
				ps.setString(i, model.getCustomerID() + "%");
			}
			i++;
			if(model.getDeliverybillID() == 0){
				ps.setString(i, "%");
			}else{
				ps.setString(i, model.getDeliverybillID() + "%");
			}
			i++;
			if(model.getDeliveryDate().equals("null")){
				model.setDeliveryDate("");
			}
			ps.setString(i, model.getDeliveryDate() + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				model.getObsListInvoiceSearch().add(new ModelInvoice(
					rs.getInt("invoiceID"),
					rs.getString("invoiceDate"),
					rs.getInt("customerID"),
					rs.getInt("clerkID"),
					rs.getInt("deliverybillID"),
					rs.getString("deliveryDate")
				));
				
			}
			
			System.out.println("Alle Rechnungen mit Übereinstimmenden Suchkriterien wurden geladen!");
					
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
	
	public ModelInvoice getModelInvoice(){
		return model;
	}
	
}
