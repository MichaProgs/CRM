package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchCustomer {

	private ObservableList<ModelCustomer> obsListSearch = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchCustomer(	String _customerID, 
							String _name1,
							String _name2,
							String _street,
							String _land,
							String _zip,
							String _location,
							
							String _phone,
							String _mobile,
							String _fax,
							String _email){
		
		try{
			
			String stmt = "SELECT * FROM customer WHERE customerID LIKE ? AND "
													+ "name1 LIKE ? AND "
													+ "name2 LIKE ? AND "
													+ "street LIKE ? AND "
													+ "land LIKE ? AND "
													+ "zip LIKE ? AND "
													+ "location LIKE ? AND "
													+ "phone LIKE ? AND "
													+ "mobile LIKE ? AND "
													+ "fax LIKE ? AND "
													+ "email LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _customerID + "%");
			i++;
			ps.setString(i, _name1 + "%");
			i++;
			ps.setString(i, _name2 + "%");
			i++;
			ps.setString(i, _street + "%");
			i++;
			//Empty ComboBox = null
			if(_land == null){
				_land = "";
			}
			ps.setString(i, _land + "%");
			i++;
			ps.setString(i, _zip + "%");
			i++;
			ps.setString(i, _location + "%");
			i++;			
			ps.setString(i, _phone + "%");
			i++;
			ps.setString(i, _mobile + "%");
			i++;
			ps.setString(i, _fax + "%");
			i++;
			ps.setString(i, _email + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
								
				this.obsListSearch.add(new ModelCustomer(
					rs.getInt("customerID"), 
					rs.getString("name1"), 
					rs.getString("name2"), 
					rs.getString("street"), 
					rs.getString("land"), 
					rs.getInt("zip"), 
					rs.getString("location"), 
					rs.getString("phone")
				));
				
			}
			
			System.out.println("Alle Kunden mit übereinstimmenden Suchfaktoren aus Datenbank geladen!");
			
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
	
	public ObservableList<ModelCustomer> getObsListSearch(){
		return obsListSearch;
	}
	
}
