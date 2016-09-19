package de.michaprogs.crm.amountunit;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertAmountUnit {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertAmountUnit(ModelAmountUnit mau){
		
		try{
			
			String stmt = "INSERT INTO amountUnit (amountUnit) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, mau.getAmountUnit());
			ps.execute();
			
			System.out.println("Gebindegröße " + mau.getAmountUnit() + " zur Datenbank hinzugefügt");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
