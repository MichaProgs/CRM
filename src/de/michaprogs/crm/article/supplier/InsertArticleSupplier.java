package de.michaprogs.crm.article.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertArticleSupplier {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertArticleSupplier(ModelArticleSupplier mas){
		
		try{
			
			String stmt = "INSERT INTO articlesupplier ("
				+ "articleID,"
				+ "supplierID,"
				+ "supplierArticleID,"
				+ "supplierDescription1,"
				+ "supplierDescription2,"
				+ "supplierEk,"
				+ "supplierPriceUnit,"
				+ "supplierAmountUnit"
				+ ")"
				+ "VALUES (?,?,?,?,?,?,?,?)"; //8
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, mas.getArticleID());
			i++;
			ps.setInt(i, mas.getSupplierID());
			i++;
			ps.setString(i, mas.getSupplierArticleID());
			i++;
			ps.setString(i, mas.getSupplierDescription1());
			i++;
			ps.setString(i, mas.getSupplierDescription2());
			i++;
			ps.setBigDecimal(i, mas.getSupplierEk());
			i++;
			ps.setInt(i, mas.getSupplierPriceUnit());
			i++;
			ps.setString(i, mas.getSupplierAmountUnit());
			i++;
			
			ps.execute();
			
			System.out.println("Hersteller " + mas.getSupplierID() + " zu Artikel " + mas.getArticleID() + " wurde der Datenbank hinzugefügt!");
			
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
