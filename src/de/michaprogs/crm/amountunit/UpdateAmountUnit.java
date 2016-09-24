package de.michaprogs.crm.amountunit;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateAmountUnit {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public UpdateAmountUnit(ModelAmountUnit mau){
	
		try{
			
			String stmt = "UPDATE AmountUnit SET AmountUnit = ? "
												+ "WHERE AmountUnitID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, mau.getAmountUnit());
			i++;
			ps.setInt(i, mau.getAmountUnitID()); //ALWAYS LAST
			i++;
			ps.execute();
			
			System.out.println("Änderungen an Mengeneinheit " + mau.getAmountUnitID() + " wurden in Datenbank gespeichert!");
			
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
