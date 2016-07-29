package de.michaprogs.crm.warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelWarehouse {

	private int warehouseID;
	private String warehouse;
	
	private ObservableList<ModelWarehouse> obsListWarehouses = FXCollections.observableArrayList();
	private ObservableList<String> obsListWarehousesCombo = FXCollections.observableArrayList();
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ModelWarehouse(){}
	
	/**
	 * Constructor for ObservableList (Warehouse Add)
	 * @param _warehouseID
	 * @param _warehouse
	 */
	public ModelWarehouse(	int _warehouseID,
							String _warehouse){
		
		this.warehouseID = _warehouseID;
		this.warehouse = _warehouse;
		
	}
	
	/**
	 * Constructor for ObservableList (Warehouse Combo)
	 * @param _warehouse
	 */
//	public ModelWarehouse(String _warehouse){
//		this.warehouse = _warehouse;
//	}
	
	public void insertWarehouse(String _warehouse){
		
		try{
			
			String stmt = "INSERT INTO warehouse (warehouse) VALUES (?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setString(1, _warehouse);
			
			ps.execute();
			
			System.out.println("Lager " + _warehouse + " zur Datenbank hinzugefügt!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void selectWarehouses(){
		
		try{
			
			String stmt = "SELECT * FROM warehouse";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				obsListWarehouses.add(new ModelWarehouse(
					rs.getInt("warehouseID"),
					rs.getString("warehouse")
				));
				
				obsListWarehousesCombo.add(rs.getString("warehouse"));
				
			}
			
			System.out.println("Alle Lager aus Datenbank geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void selectWarehouse(int _warehouseID){
		
		try{
			
			String stmt = "SELECT * FROM warehouse WHERE warehouseID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);			
			ps.setInt(1, _warehouseID);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				this.warehouseID = rs.getInt("warehouseID");
				this.warehouse = rs.getString("warehouse");
				
			}
			
			System.out.println("Lager " + warehouseID + " " + warehouse + " aus Datenbank geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void selectWarehouseID(String _warehouse){
		
		try{
			
			String stmt = "SELECT * FROM warehouse WHERE warehouse = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);			
			ps.setString(1, _warehouse);
			rs = ps.executeQuery();
			
			while(rs.next()){
				this.warehouseID = rs.getInt("warehouseID");				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void deleteWarehouse(int _warehouseID){
		
		try{
	
			String stmt = "DELETE FROM warehouse WHERE warehouseID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);			
			ps.setInt(1, _warehouseID);
			ps.execute();
			
			System.out.println("Lager " + warehouseID + " aus Datenbank gelöscht!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	private void closeConnection(){
		
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
	
	/*
	 * GETTER & SETTER
	 */
	public int getWarehouseID() {
		return warehouseID;
	}

	public String getWarehouse() {
		return warehouse;
	}
	
	public ObservableList<ModelWarehouse> getObsListWarehouses(){
		return obsListWarehouses;
	}
	
	public ObservableList<String> getObsListWarehousesCombo(){
		return obsListWarehousesCombo;
	}
	
}
