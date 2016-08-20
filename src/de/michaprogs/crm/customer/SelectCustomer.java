package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectCustomer {

	private ModelCustomer mc;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectCustomer(ModelCustomer mc){
		
		try{
			
			this.mc = mc;
			
			String stmt = "SELECT * FROM customer WHERE customerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, mc.getCustomerID());
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				mc.setCustomerID(rs.getInt("customerID"));
				mc.setSalutation(rs.getString("salutation"));
				mc.setName1(rs.getString("name1"));
				mc.setName2(rs.getString("name2"));
				mc.setStreet(rs.getString("street"));
				mc.setLand(rs.getString("land"));
				mc.setZip(rs.getInt("zip"));
				mc.setLocation(rs.getString("location"));
				
				mc.setPhone(rs.getString("phone"));
				mc.setMobile(rs.getString("mobile"));
				mc.setFax(rs.getString("fax"));
				mc.setEmail(rs.getString("email"));
				mc.setWeb(rs.getString("web"));
				mc.setContact(rs.getString("contactperson"));
				mc.setUstID(rs.getString("ustID"));
				
				mc.setPayment(rs.getString("payment"));
				mc.setIBAN(rs.getString("IBAN"));
				mc.setBIC(rs.getString("BIC"));
				mc.setBank(rs.getString("bank"));
				mc.setPaymentSkonto(rs.getInt("paymentSkonto"));
				mc.setPaymentNetto(rs.getInt("paymentNetto"));
				mc.setSkonto(rs.getInt("skonto"));
				
				mc.setNotes(rs.getString("notes"));
				mc.setLastChange(rs.getString("lastChange"));
				
				mc.setBillingID(rs.getInt("billingID"));
				
			}
			
			System.out.println("Kunde " + mc.getCustomerID() + " " + mc.getName1() + " aus Datenbank geladen!");
			
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
	
	public ModelCustomer getModelCustomer(){
		return mc;
	}
	
}
