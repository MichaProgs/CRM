package de.michaprogs.crm.clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SelectClerk {

	private ModelClerk mc;
	private ObservableList<ModelClerk> obsListClerk = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectClerk(ModelClerk mc, Selection selection){
		
		try{
		
			this.mc = mc;
			String stmt = "";
			
			con = new DBConnect().getConnection();
			
			if(selection.equals(Selection.SELECT_ALL)){
				stmt = "SELECT * FROM clerk";
				ps = con.prepareStatement(stmt);
			}else if(selection.equals(Selection.SELECT_SPECIFIC)){
				stmt = "SELECT * FROM clerk WHERE clerkID = ?";
				ps = con.prepareStatement(stmt);
				ps.setInt(1, mc.getClerkID());
			}
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				mc.setClerkID(rs.getInt("clerkID"));
				mc.setSalutation(rs.getString("salutation"));
				mc.setName(rs.getString("name"));
				mc.setPhone(rs.getString("phone"));
				mc.setFax(rs.getString("fax"));
				mc.setEmail(rs.getString("email"));
				mc.setDepartment(rs.getString("department"));
				
				obsListClerk.add(new ModelClerk(
					rs.getInt("clerkID"),
					rs.getString("salutation"),
					rs.getString("name"),
					rs.getString("phone"),
					rs.getString("fax"),
					rs.getString("email"),
					rs.getString("department")
				));
				
			}
			
			System.out.println("Alle Sachbearbeiter aus Datenbank geladen!");
			
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
	
	public enum Selection{
		SELECT_ALL, SELECT_SPECIFIC
	}
	
	public ObservableList<ModelClerk> getObsListClerk(){
		return obsListClerk;
	}
	
	public ModelClerk getModelClerk(){
		return mc;
	}
	
}

