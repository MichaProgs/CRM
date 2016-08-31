package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class UpdateSupplier {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateSupplier(ModelSupplier ms, ObservableList<ModelContact> obsListContacts){
		
		try{
			
			String stmt = "UPDATE supplier SET "
				+ "name1 = ?,"
				+ "name2 = ?,"
				+ "street = ?,"
				+ "land = ?,"
				+ "zip = ?,"
				+ "location = ?,"
				+ "phone = ?,"
				+ "fax = ?,"
				+ "email = ?,"
				+ "web = ?,"
				+ "contactperson = ?,"
				+ "ustID = ?,"
				+ "payment = ?,"
				+ "iban = ?,"
				+ "bic = ?,"
				+ "bank = ?,"
				+ "paymentskonto = ?,"
				+ "paymentnetto = ?,"
				+ "skonto = ?,"
				+ "lastChange = ?,"
				+ "notes = ? "
				
				+ "WHERE supplierID = ?"; //ALWAYS LAST
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setString(i, ms.getName1());
			i++;
			ps.setString(i, ms.getName2());
			i++;
			ps.setString(i, ms.getStreet());
			i++;
			ps.setString(i, ms.getLand());
			i++;
			ps.setInt(i, ms.getZip());
			i++;
			ps.setString(i, ms.getLocation());
			i++;
			ps.setString(i, ms.getPhone());
			i++;
			ps.setString(i, ms.getFax());
			i++;
			ps.setString(i, ms.getEmail());
			i++;
			ps.setString(i, ms.getWeb());
			i++;
			ps.setString(i, ms.getContact());
			i++;
			ps.setString(i, ms.getUstID());
			i++;
			ps.setString(i, ms.getPayment());
			i++;
			ps.setString(i, ms.getIBAN());
			i++;
			ps.setString(i, ms.getBIC());
			i++;
			ps.setString(i, ms.getBank());
			i++;
			ps.setInt(i, ms.getPaymentSkonto());
			i++;
			ps.setInt(i, ms.getPaymentNetto());
			i++;
			ps.setInt(i, ms.getSkonto());
			i++;
			ps.setString(i, ms.getLastChange());
			i++;
			ps.setString(i, ms.getNotes());
			i++;
			ps.setInt(i, ms.getSupplierID()); //ALWAYS LAST!
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Lieferant " + ms.getSupplierID() + " " + ms.getName1() + " wurde in Datenbank gespeichert!");
			
			/* CONTACTS */
			new UpdateSupplierContacts(ms.getSupplierID(), obsListContacts);
			
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
