package de.michaprogs.crm.article.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;

public class InsertArticleSupplier {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertArticleSupplier(int articleID, ObservableList<ModelArticleSupplier> obsListArticleSupplier){
		
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
			
			for(int index = 0; index < obsListArticleSupplier.size(); index++){			
				
				int i = 1;
				ps.setInt(i, articleID);
				i++;
				ps.setInt(i, obsListArticleSupplier.get(index).getSupplierID());
				i++;
				ps.setString(i, obsListArticleSupplier.get(index).getSupplierArticleID());
				i++;
				ps.setString(i, obsListArticleSupplier.get(index).getSupplierDescription1());
				i++;
				ps.setString(i, obsListArticleSupplier.get(index).getSupplierDescription2());
				i++;
				ps.setBigDecimal(i, obsListArticleSupplier.get(index).getSupplierEk());
				i++;
				ps.setInt(i, obsListArticleSupplier.get(index).getSupplierPriceUnit());
				i++;
				ps.setString(i, obsListArticleSupplier.get(index).getSupplierAmountUnit());
				i++;
				
				ps.execute();
				
				System.out.println("Hersteller " + obsListArticleSupplier.get(index).getSupplierID() + " zu Artikel " + obsListArticleSupplier.get(index).getSupplierArticleID() + " wurde der Datenbank hinzugefügt!");
				
			}
			
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
