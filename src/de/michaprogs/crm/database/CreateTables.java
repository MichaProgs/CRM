package de.michaprogs.crm.database;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {
	
	public CreateTables(){
		
//		dropTable(new DBConnect().getConnection());
		
		createTableCustomer(new DBConnect().getConnection());
		createTableCustomerContacts(new DBConnect().getConnection());
		new CreateTableCustomerCategory(new DBConnect().getConnection());
		
		createTableArticle(new DBConnect().getConnection());
		
		createTableBarrelsize(new DBConnect().getConnection());
		createTableBolting(new DBConnect().getConnection());
		createTableAmountUnit(new DBConnect().getConnection());
		createTableArticleCategory(new DBConnect().getConnection());
		createTableClerk(new DBConnect().getConnection());
		
		createTableSupplier(new DBConnect().getConnection());
		createTableSupplierContacts(new DBConnect().getConnection());
		
		createTableArticleSupplier(new DBConnect().getConnection());
		new CreateTableArticleComparison(new DBConnect().getConnection());
		new CreateTableArticleAccessoris(new DBConnect().getConnection());
		
		createTableWarehouse(new DBConnect().getConnection());
		createTableStock(new DBConnect().getConnection());
		
		createTableOffer(new DBConnect().getConnection());
		new CreateTableOrder(new DBConnect().getConnection());
		
	}
	
	private void createTableCustomer(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS CUSTOMER("
					+ "CUSTOMERID INTEGER IDENTITY,"
					+ "SALUTATION VARCHAR_IGNORECASE,"
					+ "NAME1 VARCHAR_IGNORECASE,"
					+ "NAME2 VARCHAR_IGNORECASE,"
					+ "STREET VARCHAR_IGNORECASE,"
					+ "LAND VARCHAR_IGNORECASE,"
					+ "ZIP INTEGER,"
					+ "LOCATION VARCHAR_IGNORECASE,"
					+ "PHONE VARCHAR_IGNORECASE,"
					+ "MOBILE VARCHAR_IGNORECASE,"
					+ "FAX VARCHAR_IGNORECASE,"
					+ "EMAIL VARCHAR_IGNORECASE,"
					+ "WEB VARCHAR_IGNORECASE,"
					+ "TAXID VARCHAR_IGNORECASE,"
					+ "USTID VARCHAR_IGNORECASE, "
					+ "PAYMENT VARCHAR_IGNORECASE, "
					+ "IBAN VARCHAR_IGNORECASE,"
					+ "BIC VARCHAR_IGNORECASE,"
					+ "BANK VARCHAR_IGNORECASE,"
					+ "PAYMENTSKONTO INTEGER,"
					+ "PAYMENTNETTO INTEGER,"
					+ "SKONTO VARCHAR_IGNORECASE,"
					+ "CATEGORY VARCHAR_IGNORECASE,"
					+ "LASTCHANGE DATE,"
					+ "NOTES VARCHAR_IGNORECASE,"
					+ "BILLINGID INTEGER"
					+ ")";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'CUSTOMER' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableCustomerContacts(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS CustomerContacts("	
									+ "CUSTOMERID INTEGER NOT NULL,"
									+ "SALUTATION VARCHAR_IGNORECASE,"
									+ "NAME VARCHAR_IGNORECASE,"
									+ "PHONE VARCHAR_IGNORECASE,"
									+ "FAX VARCHAR_IGNORECASE,"
									+ "EMAIL VARCHAR_IGNORECASE,"
									+ "DEPARTMENT VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'CUSTOMERCONTACTS' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableArticle(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS ARTICLE(   "
									+ "ARTICLEID INTEGER PRIMARY KEY,"
									+ "DESCRIPTION1 VARCHAR_IGNORECASE,"
									+ "DESCRIPTION2 VARCHAR_IGNORECASE,"
									+ "CATEGORY VARCHAR_IGNORECASE,"
									+ "EANID INTEGER,"
									+ "BARRELSIZE VARCHAR_IGNORECASE,"
									+ "BOLTING VARCHAR_IGNORECASE,"
									+ "LENGTH INTEGER,"
									+ "WIDTH INTEGER,"
									+ "HEIGHT INTEGER,"
									+ "WEIGHT DECIMAL(10,2),"
									+ "DESITY DECIMAL(10,4),"
									+ "EK DECIMAL(10,2),"
									+ "VK DECIMAL(10,2),"
									+ "PRICEUNIT INTEGER,"
									+ "AMOUNTUNIT VARCHAR_IGNORECASE,"
									+ "TAX INTEGER,"
									+ "LONGTEXT VARCHAR_IGNORECASE,"
									+ "NOTES VARCHAR_IGNORECASE,"
									+ "IMAGEFILEPATH VARCHAR_IGNORECASE,"
									+ "STOCKMINUNIT INTEGER,"
									+ "STOCKMAXUNIT INTEGER,"
									+ "STOCKALERTUNIT INTEGER,"
									+ "LASTCHANGE DATE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'ARTICLE' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableBarrelsize(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS BARRELSIZE("
									+ "BARRELSIZEID INTEGER IDENTITY,"
									+ "BARRELSIZE VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'BARRELSIZE' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableBolting(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS Bolting("
									+ "BOLTINGID INTEGER IDENTITY,"
									+ "BOLTING VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'BOLTING' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableAmountUnit(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS AmountUnit("
									+ "AMOUNTUNITID INTEGER IDENTITY,"
									+ "AMOUNTUNIT VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'AMOUNTUNIT' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableArticleCategory(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS ArticleCategory("
									+ "ARTICLECATEGORYID INTEGER IDENTITY,"
									+ "ARTICLECATEGORY VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'ARTICLECATEGORY' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableClerk(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS Clerk("
									+ "CLERKID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
									+ "SALUTATION VARCHAR_IGNORECASE,"
									+ "NAME VARCHAR_IGNORECASE,"
									+ "PHONE VARCHAR_IGNORECASE,"
									+ "FAX VARCHAR_IGNORECASE,"
									+ "EMAIL VARCHAR_IGNORECASE,"
									+ "DEPARTMENT VARCHAR_IGNORECASE"
									+ ")";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'CLERK' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableSupplier(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS SUPPLIER("
									+ "SUPPLIERID INTEGER IDENTITY,"
									+ "NAME1 VARCHAR_IGNORECASE,"
									+ "NAME2 VARCHAR_IGNORECASE,"
									+ "STREET VARCHAR_IGNORECASE,"
									+ "LAND VARCHAR_IGNORECASE,"
									+ "ZIP INTEGER,"
									+ "LOCATION VARCHAR_IGNORECASE,"
									+ "PHONE VARCHAR_IGNORECASE,"
									+ "FAX VARCHAR_IGNORECASE,"
									+ "EMAIL VARCHAR_IGNORECASE,"
									+ "WEB VARCHAR_IGNORECASE,"
									+ "CONTACTPERSON VARCHAR_IGNORECASE,"
									+ "USTID VARCHAR_IGNORECASE, "
									+ "PAYMENT VARCHAR_IGNORECASE, "
									+ "IBAN VARCHAR_IGNORECASE,"
									+ "BIC VARCHAR_IGNORECASE,"
									+ "BANK VARCHAR_IGNORECASE,"
									+ "PAYMENTSKONTO INTEGER,"
									+ "PAYMENTNETTO INTEGER,"
									+ "SKONTO VARCHAR_IGNORECASE,"
									+ "LASTCHANGE DATE,"
									+ "NOTES VARCHAR_IGNORECASE)"; //22
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'SUPPLIER' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableSupplierContacts(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS SupplierContacts("	
									+ "SUPPLIERID INTEGER NOT NULL,"
									+ "SALUTATION VARCHAR_IGNORECASE,"
									+ "NAME VARCHAR_IGNORECASE,"
									+ "PHONE VARCHAR_IGNORECASE,"
									+ "FAX VARCHAR_IGNORECASE,"
									+ "EMAIL VARCHAR_IGNORECASE,"
									+ "DEPARTMENT VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'SUPPLIERCONTACTS' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableArticleSupplier(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS ARTICLESUPPLIER("
									+ "SUPPLIERID INTEGER,"
									+ "ARTICLEID INTEGER,"
									+ "SUPPLIERARTICLEID VARCHAR_IGNORECASE," //Varchar because maybe supplier has articleID with chars
									+ "SUPPLIERDESCRIPTION1 VARCHAR_IGNORECASE,"
									+ "SUPPLIERDESCRIPTION2 VARCHAR_IGNORECASE,"
									+ "SUPPLIEREK DECIMAL(10,2),"
									+ "SUPPLIERPRICEUNIT INTEGER,"
									+ "SUPPLIERAMOUNTUNIT VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'ARTICLESUPPLIER' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableWarehouse(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS Warehouse("
									+ "WAREHOUSEID INTEGER IDENTITY NOT NULL,"
									+ "WAREHOUSE VARCHAR_IGNORECASE)";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'WAREHOUSE' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableStock(Connection con){
		
		try{

			String stmt = "CREATE TABLE IF NOT EXISTS Stock("
									+ "articleID INTEGER,"
									+ "supplierID INTEGER,"
									+ "warehouseID INTEGER,"
									+ "amount DECIMAL(10,2),"
									+ "ek DECIMAL(10,2),"
									+ "date VARCHAR_IGNORECASE"
									+ ")";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'STOCK' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createTableOffer(Connection con){
		
		try{

			String stmt1 = "CREATE TABLE IF NOT EXISTS Offer("
									+ "OFFERID INTEGER IDENTITY NOT NULL,"
									+ "OFFERDATE VARCHAR_IGNORECASE,"
									+ "REQUEST VARCHAR_IGNORECASE,"
									+ "REQUESTDATE VARCHAR_IGNORECASE,"
									+ "NOTES VARCHAR_IGNORECASE,"
									+ "CUSTOMERID INTEGER NOT NULL,"
									+ "CLERKID INTEGER NOT NULL"
									+ ")";
			
			String stmt2 = "CREATE TABLE IF NOT EXISTS OfferArticle("
									+ "OFFERID INTEGER NOT NULL,"	
									+ "ARTICLEID INTEGER NOT NULL,"
									+ "AMOUNT DECIMAL(10,2),"
									+ "EK DECIMAL(10,2),"
									+ "VK DECIMAL(10,2),"
									+ "TOTAL DECIMAL(10,2)"
									+ ")";
			
			Statement statement = con.createStatement();
			statement.execute(stmt1);
			statement.execute(stmt2);
			
			System.out.println("Tabelle 'OFFER' in Datenbank erstellt!");
			System.out.println("Tabelle 'OFFERARTICLE' in Datenbank erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void closeConnection(){
		
		try{
//			if(con != null)
//				con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//ONLY TO TEST
	private void dropTable(Connection con){
		
		try{
			
			String stmt = "DROP TABLE supplier";
			
			Statement statement = con.createStatement();
			statement.execute(stmt);
			
			System.out.println("Tabelle 'SUPPLIER' aus Datenbank gelöscht");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
//	CREATE MEMORY TABLE PUBLIC.STOCK(STOCKID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,STOCK VARCHAR(255) COLLATE SQL_TEXT_UCC)
//	ALTER TABLE PUBLIC.STOCK ALTER COLUMN STOCKID RESTART WITH 68
//	CREATE MEMORY TABLE PUBLIC.ARTICLE(ARTICLEID VARCHAR(32768) COLLATE SQL_TEXT_UCC PRIMARY KEY,DESCRIPTION1 VARCHAR(32768) COLLATE SQL_TEXT_UCC,DESCRIPTION2 VARCHAR(32768) COLLATE SQL_TEXT_UCC,CATEGORY VARCHAR(32768) COLLATE SQL_TEXT_UCC,EANID INTEGER,BARRELSIZE VARCHAR(32768) COLLATE SQL_TEXT_UCC,BOLTING VARCHAR(32768) COLLATE SQL_TEXT_UCC,LENGTH INTEGER,WIDTH INTEGER,HEIGHT INTEGER,WEIGHT DECIMAL(10,2),DESITY DECIMAL(10,2),EK DECIMAL(10,2),VK DECIMAL(10,2),PRICEUNIT INTEGER,AMOUNTUNIT VARCHAR(32768) COLLATE SQL_TEXT_UCC,TAX VARCHAR(32768) COLLATE SQL_TEXT_UCC,LONGTEXT VARCHAR(32768) COLLATE SQL_TEXT_UCC,IMAGEFILEPATH VARCHAR(255),STOCKMINUNIT INTEGER,STOCKMAXUNIT INTEGER,STOCKALERTUNIT INTEGER,LASTCHANGE DATE)
//	CREATE MEMORY TABLE PUBLIC.SUPPLIER(SUPPLIERID INTEGER PRIMARY KEY,NAME1 VARCHAR(32768) COLLATE SQL_TEXT_UCC,NAME2 VARCHAR(32768) COLLATE SQL_TEXT_UCC,STREET VARCHAR(32768) COLLATE SQL_TEXT_UCC,ZIP INTEGER,LOCATION VARCHAR(32768) COLLATE SQL_TEXT_UCC,PHONE VARCHAR(32768) COLLATE SQL_TEXT_UCC,FAX VARCHAR(32768) COLLATE SQL_TEXT_UCC,EMAIL VARCHAR(32768) COLLATE SQL_TEXT_UCC,WEB VARCHAR(32768) COLLATE SQL_TEXT_UCC,CONTACTPERSON VARCHAR(32768) COLLATE SQL_TEXT_UCC,IBAN VARCHAR(32768) COLLATE SQL_TEXT_UCC,BIC VARCHAR(32768) COLLATE SQL_TEXT_UCC,BANK VARCHAR(32768) COLLATE SQL_TEXT_UCC,PAYMENTSKONTO INTEGER,PAYMENTNETTO INTEGER,SKONTO VARCHAR(32768) COLLATE SQL_TEXT_UCC,USTID VARCHAR(32768) COLLATE SQL_TEXT_UCC)
//	CREATE MEMORY TABLE PUBLIC.BOLTING(BOLTINGID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,BOLTING VARCHAR(255) COLLATE SQL_TEXT_UCC)
//	ALTER TABLE PUBLIC.BOLTING ALTER COLUMN BOLTINGID RESTART WITH 3
//	CREATE MEMORY TABLE PUBLIC.BARRELSIZE(BARRELSIZEID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,BARRELSIZE VARCHAR(255) COLLATE SQL_TEXT_UCC)
//	ALTER TABLE PUBLIC.BARRELSIZE ALTER COLUMN BARRELSIZEID RESTART WITH 1
//	CREATE MEMORY TABLE PUBLIC.ARTICLESTOCK(ARTICLEID VARCHAR(32768) COLLATE SQL_TEXT_UCC,STOCKID INTEGER,SUPPLIERID VARCHAR(32768) COLLATE SQL_TEXT_UCC,AMOUNT INTEGER,AMOUNTUNIT VARCHAR(32768) COLLATE SQL_TEXT_UCC,EK DECIMAL(10,2),DATE DATE)
//	CREATE MEMORY TABLE PUBLIC.OFFER(OFFERID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000) NOT NULL PRIMARY KEY,DATE DATE,REQUEST VARCHAR(32768) COLLATE SQL_TEXT_UCC,REQUESTDATE DATE,CREATOR VARCHAR(32768) COLLATE SQL_TEXT_UCC,ENDING VARCHAR(32768) COLLATE SQL_TEXT_UCC,CUSTOMERIDBILLING VARCHAR(32768) COLLATE SQL_TEXT_UCC,SALUTATIONBILLING VARCHAR(32768) COLLATE SQL_TEXT_UCC,NAME1BILLING VARCHAR(32768) COLLATE SQL_TEXT_UCC,NAME2BILLING VARCHAR(32768) COLLATE SQL_TEXT_UCC,STREETBILLING VARCHAR(32768) COLLATE SQL_TEXT_UCC,ZIPBILLING INTEGER,LOCATIONBILLING VARCHAR(32768) COLLATE SQL_TEXT_UCC,CUSTOMERIDDELIVERY VARCHAR(32768) COLLATE SQL_TEXT_UCC,SALUTATIONDELIVERY VARCHAR(32768) COLLATE SQL_TEXT_UCC,NAME1DELIVERY VARCHAR(32768) COLLATE SQL_TEXT_UCC,NAME2DELIVERY VARCHAR(32768) COLLATE SQL_TEXT_UCC,STREETDELIVERY VARCHAR(32768) COLLATE SQL_TEXT_UCC,ZIPDELIVERY INTEGER,LOCATIONDELIVERY VARCHAR(32768) COLLATE SQL_TEXT_UCC)
//	ALTER TABLE PUBLIC.OFFER ALTER COLUMN OFFERID RESTART WITH 1000
//	CREATE MEMORY TABLE PUBLIC.OFFERPOSITIONS(OFFERID INTEGER,ARTICLEID VARCHAR(32768) COLLATE SQL_TEXT_UCC,AMOUNT INTEGER,VK DECIMAL(10,2),EK DECIMAL(10,2),GP DECIMAL(10,2))
//	CREATE MEMORY TABLE PUBLIC.SUPPLIERCONTACTS(SUPPLIERID INTEGER,NAME VARCHAR(32768) COLLATE SQL_TEXT_UCC,PHONE VARCHAR(32768) COLLATE SQL_TEXT_UCC,MOBILE VARCHAR(32768) COLLATE SQL_TEXT_UCC,FAX VARCHAR(32768) COLLATE SQL_TEXT_UCC,EMAIL VARCHAR(32768) COLLATE SQL_TEXT_UCC,DEPARTMENT VARCHAR(32768) COLLATE SQL_TEXT_UCC)
//	CREATE MEMORY TABLE PUBLIC.ARTICLESUPPLIER(ARTICLEID VARCHAR(32768) COLLATE SQL_TEXT_UCC,SUPPLIERID VARCHAR(32768) COLLATE SQL_TEXT_UCC,SUPPLIERARTICLEID VARCHAR(32768) COLLATE SQL_TEXT_UCC,SUPPLIERDESCRIPTION1 VARCHAR(32768) COLLATE SQL_TEXT_UCC,SUPPLIERDESCRIPTION2 VARCHAR(32768) COLLATE SQL_TEXT_UCC,SUPPLIEREK DECIMAL(10,2))
//
//	
}
