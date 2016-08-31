package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class InsertCustomer {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertCustomer(ModelCustomer mc, ObservableList<ModelContact> obsListContacts){
		
		try{
			
			String stmt = "INSERT INTO customer ("
				+ "customerID,"
				+ "salutation,"
				+ "name1,"
				+ "name2,"
				+ "street,"
				+ "land,"
				+ "zip,"
				+ "location,"
				+ "phone,"
				+ "mobile,"
				+ "fax,"
				+ "email,"
				+ "web,"
				+ "contactperson,"
				+ "ustID,"
				+ "payment,"
				+ "iban,"
				+ "bic,"
				+ "bank,"
				+ "paymentskonto,"
				+ "paymentnetto,"
				+ "skonto,"
				+ "lastChange,"
				+ "notes,"
				+ "billingID)"
				+ "VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, mc.getCustomerID());
			i++;
			ps.setString(i, mc.getSalutation());
			i++;
			ps.setString(i, mc.getName1());
			i++;
			ps.setString(i, mc.getName2());
			i++;
			ps.setString(i, mc.getStreet());
			i++;
			ps.setString(i, mc.getLand());
			i++;
			ps.setInt(i, mc.getZip());
			i++;
			ps.setString(i, mc.getLocation());
			i++;
			ps.setString(i, mc.getPhone());
			i++;
			ps.setString(i, mc.getMobile());
			i++;
			ps.setString(i, mc.getFax());
			i++;
			ps.setString(i, mc.getEmail());
			i++;
			ps.setString(i, mc.getWeb());
			i++;
			ps.setString(i, mc.getContact());
			i++;
			ps.setString(i, mc.getUstID());
			i++;
			ps.setString(i, mc.getPayment());
			i++;
			ps.setString(i, mc.getIBAN());
			i++;
			ps.setString(i, mc.getBIC());
			i++;
			ps.setString(i, mc.getBank());
			i++;
			ps.setInt(i, mc.getPaymentSkonto());
			i++;
			ps.setInt(i, mc.getPaymentNetto());
			i++;
			ps.setInt(i, mc.getSkonto());
			i++;
			ps.setString(i, mc.getLastChange());
			i++;
			ps.setString(i, mc.getNotes());
			i++;
			ps.setInt(i, mc.getBillingID());
			i++;
			
			ps.execute();
			
			System.out.println("Kunde " + mc.getCustomerID() + " " + mc.getName1() + " wurde zur Datenbank hinzugefügt");
			
			/* CONTACTS */
			new InsertCustomerContacts(mc.getCustomerID(), obsListContacts);
			
			
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
