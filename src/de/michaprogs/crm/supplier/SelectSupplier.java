package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectSupplier {

	private ModelSupplier ms;
	private ObservableList<ModelContact> obsListContact = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectSupplier(ModelSupplier ms){
		
		try{
			
			this.ms = ms;
			
			String stmt = "SELECT * FROM supplier WHERE supplierID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, ms.getSupplierID());
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ms.setSupplierID(rs.getInt("supplierID"));
				ms.setName1(rs.getString("name1"));
				ms.setName2(rs.getString("name2"));
				ms.setStreet(rs.getString("street"));
				ms.setLand(rs.getString("land"));
				ms.setZip(rs.getInt("zip"));
				ms.setLocation(rs.getString("location"));
				
				ms.setPhone(rs.getString("phone"));
				ms.setFax(rs.getString("fax"));
				ms.setEmail(rs.getString("email"));
				ms.setWeb(rs.getString("web"));
				ms.setContact(rs.getString("contactperson"));				
				ms.setUstID(rs.getString("ustID"));
				
				ms.setPayment(rs.getString("payment"));
				ms.setIBAN(rs.getString("iban"));
				ms.setBIC(rs.getString("bic"));
				ms.setBank(rs.getString("bank"));
				ms.setPaymentSkonto(rs.getInt("paymentSkonto"));
				ms.setPaymentNetto(rs.getInt("paymentNetto"));
				ms.setSkonto(rs.getInt("skonto"));
				
				ms.setLastChange(rs.getString("lastChange"));
				ms.setNotes(rs.getString("notes"));
				
			}
			
			System.out.println("Lieferant " + ms.getSupplierID() + " " + ms.getName1() + " wurde aus Datenbank geladen!");
			
			/* CONTACTS */
			
			stmt = "SELECT * FROM suppliercontacts WHERE supplierID = ?";
			
			ps = con.prepareStatement(stmt);
			
			i = 1;
			ps.setInt(i, ms.getSupplierID());
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListContact.add(new ModelContact(
					ms.getSupplierID(),
					rs.getString("salutation"),
					rs.getString("name"),
					rs.getString("phone"),
					rs.getString("fax"),
					rs.getString("email"),
					rs.getString("department")
				));
				
			}
			
			ms.setObsListContact(obsListContact);
			
			System.out.println("Alle Kontakte zu Lieferant " + ms.getSupplierID() + " " + ms.getName1() + " wurden aus Datenbank geladen!");
			
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
	
	public ModelSupplier getModelSupplier(){
		return ms;
	}
	
}
