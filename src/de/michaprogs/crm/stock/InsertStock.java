package de.michaprogs.crm.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertStock {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertStock(ModelStock ms){
		
		try{
			
			String stmt = "INSERT INTO stock( "
				+ "articleID, "
				+ "supplierID,"
				+ "warehouseID,"
				+ "amount,"
				+ "ek,"
				+ "date"
				+ ") "
				+ "VALUES(?,?,?,?,?,?)"; //6
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, ms.getArticleID());
			i++;
			ps.setInt(i, ms.getSupplierID());
			i++;
			ps.setInt(i, ms.getWarehouseID());
			i++;
			ps.setDouble(i, ms.getAmount());
			i++;
			ps.setBigDecimal(i, ms.getEk());
			i++;
			ps.setString(i, ms.getDate());
			i++;
			
			ps.execute();
			
			System.out.println("Wareneingang zu Aritkel " + ms.getArticleID() + " zu Lager " + ms.getWarehouseID() + " hinzugefügt!");
			
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
