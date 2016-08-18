package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectArticle {

	private ModelArticle ma;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectArticle(ModelArticle ma){
		
		try{
			
			this.ma = ma;
			
			String stmt = "SELECT * FROM article WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, ma.getArticleID());
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ma.setArticleID(rs.getInt("articleID"));
				ma.setDescription1(rs.getString("description1"));
				ma.setDescription2(rs.getString("description2"));
				ma.setCategory(rs.getString("category"));
				ma.setEanID(rs.getInt("eanID"));
				ma.setBarrelsize(rs.getString("barrelsize"));
				ma.setBolting(rs.getString("bolting"));
				ma.setLength(rs.getInt("length"));
				ma.setWidth(rs.getInt("width"));
				ma.setHeight(rs.getInt("height"));
				ma.setWeight(rs.getDouble("weight"));
				ma.setDesity(rs.getDouble("desity"));
				ma.setEk(rs.getBigDecimal("ek"));
				ma.setVk(rs.getBigDecimal("vk"));
				ma.setPriceUnit(rs.getInt("priceunit"));
				ma.setAmountUnit(rs.getString("amountunit"));
				ma.setTax(rs.getInt("tax"));
				ma.setLongtext(rs.getString("longtext"));
				ma.setImageFilepath(rs.getString("imageFilePath"));
				ma.setStockMinUnit(rs.getInt("stockMinUnit"));
				ma.setStockMaxUnit(rs.getInt("stockMaxUnit"));
				ma.setStockAlertUnit(rs.getInt("stockAlertUnit"));
				ma.setLastChange(rs.getString("lastChange"));
				
			}
			
			System.out.println("Artikel " + ma.getArticleID() + " " + ma.getDescription1() + " aus Datenbank geladen!");
			
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
	
	public ModelArticle getModelArticle(){
		return ma;
	}
	
}
