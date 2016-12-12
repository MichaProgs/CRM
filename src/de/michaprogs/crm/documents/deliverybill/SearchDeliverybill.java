package de.michaprogs.crm.documents.deliverybill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchDeliverybill {

	private ObservableList<ModelDeliverybill> obsListSearch = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchDeliverybill(	String _deliverybillID, //Search as String
						String _deliverybillDate,
						String _customerID, //Search as String
						String _request,
						String _requestDate){
		
		try{
			
			String stmt = "SELECT * FROM DELIVERYBILL WHERE   deliverybillID LIKE ? AND "
													+ "deliverybillDate LIKE ? AND "
													+ "customerID LIKE ? AND "
													+ "request LIKE ? AND "
													+ "requestDate LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _deliverybillID + "%");
			i++;
			if(_deliverybillDate.equals("null")){
				_deliverybillDate = "";
			}
			ps.setString(i, _deliverybillDate + "%");
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
				
				obsListSearch.add(new ModelDeliverybill(
					rs.getInt("deliverybillID"),
					rs.getString("deliverybillDate"),
					rs.getInt("customerID"),
					rs.getString("request"),
					rs.getString("requestDate")
				));
				
			}
			
			System.out.println("Alle Lieferscheine mit Übereinstimmenden Suchkriterien wurden geladen!");
					
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
	
	public ObservableList<ModelDeliverybill> getObsListSearch(){
		return obsListSearch;
	}
	
}
