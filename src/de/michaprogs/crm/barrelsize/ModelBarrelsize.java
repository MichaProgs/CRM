package de.michaprogs.crm.barrelsize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelBarrelsize {	
	
	private int barrelsizeID;
	private String barrelsize;
	
	private ObservableList<ModelBarrelsize> obsListBarrelsizes = FXCollections.observableArrayList();
	
	//Database
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public ModelBarrelsize(){}
	
	//Consturctor for ObservableList
	public ModelBarrelsize(int _barrelsizeID, String _barrelsize){
		this.barrelsizeID = _barrelsizeID;
		this.barrelsize = _barrelsize;
	}
	
	public void insertbarrelsize(String _barrelsize){
		
		try{
			
			String stmt = "INSERT INTO barrelsize (barrelsize) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, _barrelsize);
			ps.execute();
			
			System.out.println("Gebindegröße " + _barrelsize + " zur Datenbank hinzugefügt");
			
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
	
	public void selectBarrelsizes(){
		
		try{
			
			String stmt = "SELECT * FROM barrelsize";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				obsListBarrelsizes.add(new ModelBarrelsize(
					rs.getInt("barrelsizeID"),
					rs.getString("barrelsize")
				));
				
			}
			
			System.out.println("Alle Gebindegrößen aus Datenbank geladen");
			
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
	
	public void deleteBarrelsize(int _barrelsizeID, String _barrelsize){
	
		try{
			
			String stmt = "DELETE FROM barrelsize WHERE barrelsizeID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _barrelsizeID);
			ps.execute();
			
			System.out.println("Gebindegröße " + _barrelsizeID + " " + _barrelsize + " aus Datenbank gelöscht!");
			
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
	public int getBarrelsizeID() {
		return barrelsizeID;
	}

	public void setBarrelsizeID(int barrelsizeID) {
		this.barrelsizeID = barrelsizeID;
	}

	public String getBarrelsize() {
		return barrelsize;
	}

	public void setBarrelsize(String barrelsize) {
		this.barrelsize = barrelsize;
	}

	public ObservableList<ModelBarrelsize> getObsListBarrelsizes() {
		return obsListBarrelsizes;
	}

	public void setObsListBarrelsizes(ObservableList<ModelBarrelsize> obsListBarrelsizes) {
		this.obsListBarrelsizes = obsListBarrelsizes;
	}
	
}
