package de.michaprogs.crm.article.bolting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelBolting {	
	
	private int boltingID;
	private String bolting;
	
	private ObservableList<ModelBolting> obsListBoltings = FXCollections.observableArrayList();
	
	//Database
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public ModelBolting(){}
	
	//Consturctor for ObservableList
	public ModelBolting(int boltingID, String bolting){
		this.boltingID = boltingID;
		this.bolting = bolting;
	}
	
	public void insertBolting(String bolting){
		
		try{
			
			String stmt = "INSERT INTO bolting (bolting) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, bolting);
			ps.execute();
			
			System.out.println("Verschraubung " + bolting + " zur Datenbank hinzugefügt");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

	public void selectBoltings(){
		
		try{
			
			String stmt = "SELECT * FROM bolting";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				obsListBoltings.add(new ModelBolting(
					rs.getInt("boltingID"),
					rs.getString("bolting")
				));
				
			}
			
			System.out.println("Alle Verschraubungen aus Datenbank geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	private void closeConnection(){
		
		try{
			
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public int getBoltingID() {
		return boltingID;
	}

	public void setBoltingID(int boltingID) {
		this.boltingID = boltingID;
	}

	public String getBolting() {
		return bolting;
	}

	public void setBolting(String bolting) {
		this.bolting = bolting;
	}

	public ObservableList<ModelBolting> getObsListBoltings() {
		return obsListBoltings;
	}

	public void setObsListBoltings(ObservableList<ModelBolting> obsListBoltings) {
		this.obsListBoltings = obsListBoltings;
	}
	
}
