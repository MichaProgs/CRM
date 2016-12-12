package de.michaprogs.crm.documents.offer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchOffer {

	private ObservableList<ModelOffer> obsListSearch = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchOffer(	String _offerID, //Search as String
						String _offerDate,
						String _customerID, //Search as String
						String _request,
						String _requestDate){
		
		try{
			
			String stmt = "SELECT * FROM offer WHERE   offerID LIKE ? AND "
													+ "offerDate LIKE ? AND "
													+ "customerID LIKE ? AND "
													+ "request LIKE ? AND "
													+ "requestDate LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _offerID + "%");
			i++;
			if(_offerDate.equals("null")){
				_offerDate = "";
			}
			ps.setString(i, _offerDate + "%");
			i++;
			ps.setString(i, _customerID + "%");
			i++;
			ps.setString(i, _request + "%");
			i++;
			if(_requestDate.equals("null")){
				_requestDate = "";
			}
			ps.setString(i, _requestDate + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListSearch.add(new ModelOffer(
					rs.getInt("offerID"),
					rs.getString("offerDate"),
					rs.getInt("customerID"),
					rs.getString("request"),
					rs.getString("requestDate")
				));
				
			}
			
			System.out.println("Alle Angebote mit Übereinstimmenden Suchkriterien wurden geladen!");
					
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
	
	public ObservableList<ModelOffer> getObsListSearch(){
		return obsListSearch;
	}
	
}
