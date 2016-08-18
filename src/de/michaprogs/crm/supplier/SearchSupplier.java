package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchSupplier {

	private ObservableList<ModelSupplier> obsListSearch = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchSupplier(	String _supplierID,
							String _name1,
							String _name2,
							String _street,
							String _land,
							String _zip,
							String _location,
							String _phone){
		
		try{
			
			String stmt = "SELECT * FROM supplier WHERE    supplierID LIKE ? AND "
														+ "name1 LIKE ? AND "
														+ "name2 LIKE ? AND "
														+ "street LIKE ? AND "
														+ "land LIKE ? AND "
														+ "zip LIKE ? AND "
														+ "location LIKE ? AND "
														+ "phone LIKE ? ";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setString(i, _supplierID + "%");
			i++;
			ps.setString(i, _name1 + "%");
			i++;
			ps.setString(i, _name2 + "%");
			i++;
			ps.setString(i, _street + "%");
			i++;
			ps.setString(i, _land + "%");
			i++;
			ps.setString(i, _zip + "%");
			i++;
			ps.setString(i, _location + "%");
			i++;
			ps.setString(i, _phone + "%"); 
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListSearch.add(new ModelSupplier(
					rs.getInt("supplierID"), 
					rs.getString("name1"), 
					rs.getString("name2"),
					rs.getString("street"),
					rs.getString("land"), 
					rs.getInt("zip"), 
					rs.getString("location"),
					rs.getString("phone")
				));
				
			}
			
			System.out.println("Alle Lieferanten mit übereinstimmenden Suchfaktoren aus Datenbank geladen!");
			
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
	
	public ObservableList<ModelSupplier> getObsListSearch(){
		return obsListSearch;
	}
	
}
