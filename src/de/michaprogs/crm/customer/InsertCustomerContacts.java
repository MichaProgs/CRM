package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class InsertCustomerContacts {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertCustomerContacts(int customerID, ObservableList<ModelContact> obsListContacts){
	
		try{
						
			String stmt = "INSERT INTO customercontacts ("
				+ "customerID,"
				+ "salutation,"
				+ "name,"
				+ "phone,"
				+ "mobile,"
				+ "fax,"
				+ "email,"
				+ "department)"
				+ "VALUES "
				+ "(?,?,?,?,?,?,?,?)"; //8
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			for(int index = 0; index < obsListContacts.size(); index++){
				
				int i = 1;
				ps.setInt(i, customerID);
				i++;
				ps.setString(i, obsListContacts.get(index).getSalutation());
				i++;
				ps.setString(i, obsListContacts.get(index).getName());
				i++;
				ps.setString(i, obsListContacts.get(index).getPhone());
				i++;
				ps.setString(i, obsListContacts.get(index).getMobile());
				i++;
				ps.setString(i, obsListContacts.get(index).getFax());
				i++;
				ps.setString(i, obsListContacts.get(index).getEmail());
				i++;
				ps.setString(i, obsListContacts.get(index).getDepartment());
				i++;
				
				ps.execute();
				
				System.out.println("Kontakt " + obsListContacts.get(index).getName() + " wurde zu Kunde " + customerID + " hinzugefügt!");
				
			}
			
			
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
