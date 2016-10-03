package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import de.michaprogs.crm.article.supplier.InsertArticleSupplier;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.ObservableList;
import tray.notification.NotificationType;

public class InsertArticle {

	private boolean wasSuccessful = false;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertArticle(ModelArticle ma, ObservableList<ModelArticleSupplier> obsListArticleSupplier){
		
		try{
			
			String stmt = "INSERT INTO article ("
				+ "articleID,"
				+ "description1,"
				+ "description2,"
				+ "category,"
				+ "eanID,"
				+ "barrelsize,"
				+ "bolting,"
				+ "length,"
				+ "width,"
				+ "height,"
				+ "weight,"
				+ "desity,"
				+ "ek,"
				+ "vk,"
				+ "priceunit,"
				+ "amountunit,"
				+ "tax,"
				+ "longtext,"
				+ "imagefilepath,"
				+ "stockminunit,"
				+ "stockmaxunit,"
				+ "stockalertunit,"
				+ "lastchange)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
				+ ")"; //23
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, ma.getArticleID());
			i++;
			ps.setString(i, ma.getDescription1());
			i++;
			ps.setString(i, ma.getDescription2());
			i++;
			ps.setString(i, ma.getCategory());
			i++;
			ps.setInt(i, ma.getEanID());
			i++;
			ps.setString(i, ma.getBarrelsize());
			i++;
			ps.setString(i, ma.getBolting());
			i++;
			ps.setInt(i, ma.getLength());
			i++;
			ps.setInt(i, ma.getWidth());
			i++;
			ps.setInt(i, ma.getHeight());
			i++;
			ps.setDouble(i, ma.getWeight());
			i++;
			ps.setDouble(i, ma.getDesity());
			i++;
			ps.setBigDecimal(i, ma.getEk());
			i++;
			ps.setBigDecimal(i, ma.getVk());
			i++;
			ps.setInt(i, ma.getPriceUnit());
			i++;
			ps.setString(i, ma.getAmountUnit());
			i++;
			ps.setInt(i, ma.getTax());
			i++;
			ps.setString(i, ma.getLongtext());
			i++;
			ps.setString(i, ma.getImageFilepath());
			i++;
			ps.setInt(i, ma.getStockMinUnit());
			i++;
			ps.setInt(i, ma.getStockMaxUnit());
			i++;
			ps.setInt(i, ma.getStockAlertUnit());
			i++;
			ps.setString(i, ma.getLastChange());
			i++;
			
			ps.execute();
			
			/* ARTICLE SUPPLIER */				
			new InsertArticleSupplier(ma.getArticleID(), obsListArticleSupplier);
			
			new Notification(	"Artikel wurde gespeichert!", 
								ma.getArticleID() + " " + ma.getDescription1(), 
								NotificationType.SUCCESS);
			
			System.out.println("Artikel " + ma.getArticleID() + " " + ma.getDescription1() + " wurde erfolgreich gespeichert!");
			
			wasSuccessful = true;
			
		}catch(SQLIntegrityConstraintViolationException e){
			
			new Notification(	"Speichern nicht möglich!", 
								"Artikelnummer bereits vergeben!", 
								NotificationType.ERROR);
			
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
	
	public boolean wasSuccessful(){
		return wasSuccessful;
	}
	
}
