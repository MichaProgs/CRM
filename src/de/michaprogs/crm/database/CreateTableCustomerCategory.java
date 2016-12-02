package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTableCustomerCategory {

	public CreateTableCustomerCategory(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS CustomerCategory("
									+ "CUSTOMERCATEGORYID INTEGER IDENTITY,"
									+ "CUSTOMERCATEGORY VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'CUSTOMERCATEGORY' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	
}
