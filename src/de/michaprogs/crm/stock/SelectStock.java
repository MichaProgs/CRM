package de.michaprogs.crm.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.database.DBConnect;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectStock {

	private ObservableList<ModelStock> obsListStock = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectStock(	int _articleID,
						int _warehouseID){
		
		try{
			
			String stmt = "SELECT * FROM stock WHERE articleID = ? AND warehouseID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			ps.setInt(i, _warehouseID);
			i++;
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ModelSupplier supplier = new SelectSupplier(new ModelSupplier(rs.getInt("articleID"))).getModelSupplier();
				ModelArticle article = new SelectArticle(new ModelArticle(rs.getInt("articleID"))).getModelArticle();
								
				obsListStock.add(new ModelStock(
					rs.getInt("supplierID"), 
					supplier.getName1(),
					rs.getDouble("amount"), 
					article.getAmountUnit(), 
					rs.getBigDecimal("ek"), 
					String.valueOf(article.getPriceUnit()), 
					rs.getString("date")
				));
				
			}
			
			System.out.println("Bestand zu Artikel " + _articleID + " in Lager " + _warehouseID + " aus Datenbank geladen!");
			
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
	
	public ObservableList<ModelStock> getObsListStock(){
		return obsListStock;
	}
	
}
