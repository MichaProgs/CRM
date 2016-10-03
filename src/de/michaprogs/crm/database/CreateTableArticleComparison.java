package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTableArticleComparison {

	public CreateTableArticleComparison(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS ArticleComparison("
									+ "ARTICLEID INTEGER NOT NULL,"
									+ "COMPARISONARTICLEID INTEGER NOT NULL)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'ARTICLE COMPARISON' in Datenbank erstellt!");
			
			
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
