package de.michaprogs.crm.clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateClerk {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateClerk(ModelClerk mc){
		
		try{
			
			String stmt = "UPDATE clerk SET salutation = ?, "
										+ "name = ?,"
										+ "phone = ?,"
										+ "fax = ?,"
										+ "email = ?,"
										+ "department = ? "
										+ "WHERE clerkID = ?"; //ALWAYS LAST
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mc.getSalutation());
			i++;
			ps.setString(i, mc.getName());
			i++;
			ps.setString(i, mc.getPhone());
			i++;
			ps.setString(i, mc.getFax());
			i++;
			ps.setString(i, mc.getEmail());
			i++;
			ps.setString(i, mc.getDepartment());
			i++;
			ps.setInt(i, mc.getClerkID()); //ALWAYS LAST
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Sachbearbeiter " + mc.getClerkID() + " wurden in Datenbank gespeichert!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
