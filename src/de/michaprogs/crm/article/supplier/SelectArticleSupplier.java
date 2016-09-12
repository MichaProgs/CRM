package de.michaprogs.crm.article.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.database.DBConnect;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;

public class SelectArticleSupplier {
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectArticleSupplier(ModelArticle ma){
			
		try{
			
			String stmt = "SELECT * FROM articlesupplier WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, ma.getArticleID());
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ModelSupplier supplier = new SelectSupplier(new ModelSupplier(rs.getInt("supplierID"))).getModelSupplier();
				
				ma.getObsListArticleSupplier().add(new ModelArticleSupplier(
					rs.getInt("supplierID"),
					supplier.getName1(),
					rs.getString("supplierArticleID"),
					rs.getString("supplierDescription1"),
					rs.getString("supplierDescription2"),
					rs.getBigDecimal("supplierEk"),
					rs.getInt("supplierPriceUnit"),
					rs.getString("supplierAmountUnit")
				));
				
			}
			
			System.out.println("Alle Artikelhersteller zu Artikel " + ma.getArticleID() + " geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
