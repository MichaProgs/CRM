package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SearchCustomer {
	
	private ModelCustomer mc;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SearchCustomer(ModelCustomer mc){
		
		try{
			
			this.mc = mc;
			
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
													+ "email LIKE ? AND "
													+ "category LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			if(mc.getCustomerID() == 0){
				ps.setString(i, "" + "%");
			}else{
				ps.setString(i, mc.getCustomerID() + "%");
			}
			i++;
			ps.setString(i, mc.getName1() + "%");
			i++;
			ps.setString(i, mc.getName2() + "%");
			i++;
			ps.setString(i, mc.getStreet() + "%");
			i++;
			ps.setString(i, mc.getLand() + "%");
			i++;
			if(mc.getZip() == 0){
				ps.setString(i, "" + "%");
			}else{
				ps.setString(i, mc.getZip() + "%");
			}
			i++;
			ps.setString(i, mc.getLocation() + "%");
			i++;			
			ps.setString(i, mc.getPhone() + "%");
			i++;
			ps.setString(i, mc.getMobile() + "%");
			i++;
			ps.setString(i, mc.getFax() + "%");
			i++;
			ps.setString(i, mc.getEmail() + "%");
			i++;
			ps.setString(i, mc.getCategory() + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
								
				mc.getObsListSearch().add(new ModelCustomer(
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
	
	public ModelCustomer getModelCustomer(){
		return mc;
	}
	
}
