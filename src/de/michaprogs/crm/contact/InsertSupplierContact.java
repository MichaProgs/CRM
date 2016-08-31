package de.michaprogs.crm.contact;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertSupplierContact {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertSupplierContact(ModelContact mc){
		
		try{
			
			String stmt = "INSERT INTO suppliercontacts ("
													+ "supplierID,"
													+ "salutation, "
													+ "name,"
													+ "phone,"
													+ "fax,"
													+ "email,"
													+ "department) "
													+ "VALUES(?,?,?,?,?,?,?)"; //7
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, mc.getID());
			i++;
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
			
			ps.execute();
			
			System.out.println("Lieferantenkontakt " + mc.getName() + " wurde zu Lieferant " + mc.getID() + " hinzugefügt!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
