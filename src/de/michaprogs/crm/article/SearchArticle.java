package de.michaprogs.crm.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchArticle {

	private ObservableList<ModelArticle> obsListSearch = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchArticle(	String _articleID,
							String _description1,
							String _description2,
							String _barrelsize,
							String _bolting){
		
		try{
			
			String stmt = "SELECT * FROM article WHERE 	articleID LIKE ? AND"
													+ "	description1 LIKE ? AND"
													+ " description2 LIKE ? AND"
													+ " barrelsize LIKE ? AND"
													+ " bolting LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _articleID + "%");
			i++;
			ps.setString(i, _description1 + "%");
			i++;
			ps.setString(i, _description2 + "%");
			i++;
			ps.setString(i, _barrelsize + "%");
			i++;
			ps.setString(i, _bolting + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListSearch.add(new ModelArticle(
					rs.getInt("articleID"), 
					rs.getString("description1"), 
					rs.getString("description2"), 
					rs.getString("barrelsize"), 
					rs.getString("bolting")
				));
				
			}
			
			System.out.println("Alle Artikel mit übereinstimmenden Suchfaktoren aus Datenbank geladen");
			
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
	
	public ObservableList<ModelArticle> getObsListSearch(){
		return obsListSearch;
	}
	
}
