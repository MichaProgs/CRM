package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTableOrder {
	
	public CreateTableOrder(Connection con){
		
		try{

			String stmt1 = "CREATE TABLE IF NOT EXISTS SupplierOrder("
									+ "ORDERID INTEGER IDENTITY NOT NULL,"
									+ "ORDERDATE VARCHAR_IGNORECASE,"
									+ "REQUEST VARCHAR_IGNORECASE,"
									+ "REQUESTDATE VARCHAR_IGNORECASE,"
									+ "NOTES VARCHAR_IGNORECASE,"
									+ "SUPPLIERID INTEGER NOT NULL,"
									+ "CLERKID INTEGER NOT NULL"
									+ ")";
			
			String stmt2 = "CREATE TABLE IF NOT EXISTS OrderArticle("
									+ "ORDERID INTEGER NOT NULL,"	
									+ "ARTICLEID INTEGER NOT NULL,"
									+ "AMOUNT DECIMAL(10,2),"
									+ "EK DECIMAL(10,2),"
									+ "VK DECIMAL(10,2),"
									+ "TOTAL DECIMAL(10,2)"
									+ ")";
			
			Statement statement = con.createStatement();
			statement.execute(stmt1);
			statement.execute(stmt2);
			
			System.out.println("Tabelle 'SUPPLIERORDER' in Datenbank erstellt!");
			System.out.println("Tabelle 'ORDERARTICLE' in Datenbank erstellt!");
			
			
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
