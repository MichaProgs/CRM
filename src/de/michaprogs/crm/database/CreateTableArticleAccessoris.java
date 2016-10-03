package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTableArticleAccessoris {

	public CreateTableArticleAccessoris(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS ArticleAccessoris("
									+ "ARTICLEID INTEGER NOT NULL,"
									+ "ACCESSORISARTICLEID INTEGER NOT NULL)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'ARTICLE ACCESSORIS' in Datenbank erstellt!");
			
			
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
