package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTableInvoice {
	
	public CreateTableInvoice(Connection con){
		
		try{

			String stmt1 = "CREATE TABLE IF NOT EXISTS INVOICE("
									+ "INVOICEID INTEGER IDENTITY NOT NULL,"
									+ "INVOICEDATE VARCHAR_IGNORECASE,"
									+ "DELIVERYBILLID VARCHAR_IGNORECASE,"
									+ "DELIVERYDATE VARCHAR_IGNORECASE,"
									+ "NOTES VARCHAR_IGNORECASE,"
									+ "CUSTOMERID INTEGER NOT NULL,"
									+ "CLERKID INTEGER NOT NULL,"
									+ "TOTAL DECIMAL(10,2),"
									+ "AMOUNTOFPOSITIONS INTEGER"
									+ ")";
			
			String stmt2 = "CREATE TABLE IF NOT EXISTS INVOICEARTICLE("
									+ "INVOICEID INTEGER NOT NULL,"	
									+ "ARTICLEID INTEGER NOT NULL,"
									+ "AMOUNT DECIMAL(10,2),"
									+ "EK DECIMAL(10,2),"
									+ "VK DECIMAL(10,2),"
									+ "TOTAL DECIMAL(10,2)"
									+ ")";
			
			Statement statement = con.createStatement();
			statement.execute(stmt1);
			statement.execute(stmt2);
			
			System.out.println("Tabelle 'INVOICE' in Datenbank erstellt!");
			System.out.println("Tabelle 'INVOICEARTICLE' in Datenbank erstellt!");
			
			
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
