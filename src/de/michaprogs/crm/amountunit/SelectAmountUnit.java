package de.michaprogs.crm.amountunit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectAmountUnit {

	private ModelAmountUnit mau;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectAmountUnit(ModelAmountUnit mau){
		
		try{
			
			this.mau = mau;
			
			String stmt = "SELECT * FROM amountUnit";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				mau.getObsListAmountUnits().add(new ModelAmountUnit(
					rs.getInt("amountUnitID"),
					rs.getString("amountUnit")
				));
				
				mau.getObsListAmountUnitsComboBox().add(
					rs.getString("amountUnit")
				);
				
			}
			
			System.out.println("Alle Mengeneinheiten aus Datenbank geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	public ModelAmountUnit getModelAmountUnit(){
		return mau;
	}
	
}
