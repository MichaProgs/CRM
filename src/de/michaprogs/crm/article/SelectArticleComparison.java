package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectArticleComparison{
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSet rs2;
	
	public SelectArticleComparison(ModelArticle ma){
		
		try{
			
			String stmt = "SELECT * FROM articlecomparison WHERE articleID = ?";
			String stmt2 = "SELECT * FROM article WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, ma.getArticleID());
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ps = con.prepareStatement(stmt2);
				i = 1;
				ps.setInt(i, rs.getInt("comparisonarticleID"));
				i++;
				rs2 = ps.executeQuery();
				while(rs2.next()){
					ma.getObsListArticleComparison().add(new ModelArticle(
						rs2.getInt("articleID"),
						rs2.getString("description1"),
						rs2.getString("description2"),
						rs2.getString("barrelsize"),
						rs2.getString("bolting"),
						rs2.getBigDecimal("ek")
					));
				}
				
			}
			
			String stmt3 = "SELECT * FROM articlecomparison WHERE comparisonArticleID = ?";
			ps = con.prepareStatement(stmt3);
			i = 1;
			ps.setInt(i, ma.getArticleID());
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ps = con.prepareStatement(stmt2);
				i = 1;
				ps.setInt(i, rs.getInt("articleID"));
				i++;
				rs2 = ps.executeQuery();
				while(rs2.next()){
					ma.getObsListArticleComparison().add(new ModelArticle(
						rs2.getInt("articleID"),
						rs2.getString("description1"),
						rs2.getString("description2"),
						rs2.getString("barrelsize"),
						rs2.getString("bolting"),
						rs2.getBigDecimal("ek")
					));
				}
				
			}
			
			System.out.println("Alle Alternativprodukte zu Artikel " + ma.getArticleID() + " " + ma.getDescription1() + " aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					ps.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
