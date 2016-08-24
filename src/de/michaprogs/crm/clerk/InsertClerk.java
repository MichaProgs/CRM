package de.michaprogs.crm.clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertClerk {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertClerk(ModelClerk mc){
		
		try{
			
			String stmt = "INSERT INTO clerk ("
											+ "salutation,"
											+ "name,"
											+ "phone,"
											+ "fax,"
											+ "email,"
											+ "department) "
											+ "VALUES"
											+ "(?,?,?,?,?,?)";
			
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

			ps.execute();
			
			System.out.println("Sachbearbeiter " + mc.getName() + " wurde der Datenbank hinzugefügt!");
			
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
