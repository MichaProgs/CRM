package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	static final String DB_URL = "database\\article";
	static final String USER = "admin";
	static final String PW = "";
	static final String driver = "org.hsqldb.jdbcDriver";
	
	private static Connection con;
	
	public DBConnect(){
		
		try{
			Class.forName(driver);			
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + DB_URL, USER, PW);	
			
//			System.out.println("Connected to Database");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection(){
		return con;
	}
	
}
