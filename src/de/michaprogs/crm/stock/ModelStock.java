package de.michaprogs.crm.stock;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.database.DBConnect;
import de.michaprogs.crm.supplier.ModelSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelStock {

	private int articleID;
	private String description1;
	private String description2;
	private String barrelsize;
	private String bolting;
	
	private int supplierID;
	private String name1;
	private String name2;
	private String street;
	private String land;
	private int zip;
	private String location;
	
	private int warehouseID;
	private String warehouse;
	private double amount;
	private String amountUnit;
	private BigDecimal ek;
	private String priceUnit;
	private String date;
	
	private ObservableList<ModelStock> obsListStock = FXCollections.observableArrayList();
	
	/* DATEBASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ModelStock(){}
	
	/**
	 * Constructor for ObservableList (Stock Add)
	 * @param _articleID
	 * @param _description1
	 * @param _description2
	 * @param _barrelsize
	 * @param _bolting
	 * @param _supplierID
	 * @param _name1
	 * @param _stockID
	 * @param _stock
	 * @param _amount
	 * @param _amountUnit
	 * @param _ek
	 * @param _priceUnit
	 * @param _date
	 */
	public ModelStock(	int _articleID,
						String _description1,
						String _description2,
						String _barrelsize,
						String _bolting,
						int _supplierID,
						String _name1,
						int _warehouseID,
						String _warehouse,
						double _amount,
						String _amountUnit,
						BigDecimal _ek,
						String _priceUnit,
						String _date
						){
		
		this.articleID = _articleID;
		this.description1 = _description1;
		this.description2 = _description2;
		this.barrelsize = _barrelsize;
		this.bolting = _bolting;
		this.supplierID = _supplierID;
		this.name1 = _name1;
		this.warehouseID = _warehouseID;
		this.warehouse = _warehouse;
		this.amount = _amount;
		this.amountUnit = _amountUnit;
		this.ek = _ek;
		this.priceUnit = _priceUnit;
		this.date = _date;
		
	}
	
	/**
	 * Constructor for ObservableList (Article Stock)
	 * @param _supplierID
	 * @param _name1
	 * @param _amount
	 * @param _amountUnit
	 * @param _ek
	 * @param _priceUnit
	 * @param _date
	 */
	public ModelStock(	int _supplierID,
						String _name1,
						double _amount,
						String _amountUnit,
						BigDecimal _ek,
						String _priceUnit,
						String _date){
	
		this.supplierID = _supplierID;
		this.name1 = _name1;
		this.amount = _amount;
		this.amountUnit = _amountUnit;
		this.ek = _ek;
		this.priceUnit = _priceUnit;
		this.date = _date;
		
	}

	public void insertStock(	int _articleID,
								int _supplierID,
								int _warehouseID,
								double _amount,
								BigDecimal _ek,
								String _date){
		
		try{
			
			String stmt = "INSERT INTO stock( articleID, "
											+ "supplierID,"
											+ "warehouseID,"
											+ "amount,"
											+ "ek,"
											+ "date) "
											+ "VALUES(?,?,?,?,?,?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			ps.setInt(i, _supplierID);
			i++;
			ps.setInt(i, _warehouseID);
			i++;
			ps.setDouble(i, _amount);
			i++;
			ps.setBigDecimal(i, _ek);
			i++;
			ps.setString(i, _date);
			i++;
			
			ps.execute();
			
			System.out.println("Wareneingang zu Aritkel " + _articleID + " zu Lager " + _warehouseID + " hinzugefügt!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void selectStock(int _articleID, int _warehouseID){
		
		try{
			
			String stmt = "SELECT * FROM stock WHERE articleID = ? AND warehouseID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			ps.setInt(i, _warehouseID);
			i++;
			
			rs = ps.executeQuery();
			
			ModelSupplier supplier = new ModelSupplier();
			ModelArticle article = new ModelArticle();
			
			while(rs.next()){
				
				supplier.selectSupplier(rs.getInt("supplierID"));
				article.selectArticle(rs.getInt("articleID"));
				
				obsListStock.add(new ModelStock(
					rs.getInt("supplierID"), 
					supplier.getName1(),
					rs.getDouble("amount"), 
					article.getAmountUnit(), 
					rs.getBigDecimal("ek"), 
					String.valueOf(article.getPriceUnit()), 
					rs.getString("date")
				));
				
			}
			
			System.out.println("Bestand zu Artikel " + _articleID + " in Lager " + _warehouseID + " aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	
	/*
	 * CLOSE CONNECTION
	 */
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

	public int getArticleID() {
		return articleID;
	}


	public String getDescription1() {
		return description1;
	}


	public String getDescription2() {
		return description2;
	}


	public String getBarrelsize() {
		return barrelsize;
	}


	public String getBolting() {
		return bolting;
	}


	public int getSupplierID() {
		return supplierID;
	}


	public String getName1() {
		return name1;
	}


	public String getName2() {
		return name2;
	}


	public String getStreet() {
		return street;
	}


	public String getLand() {
		return land;
	}


	public int getZip() {
		return zip;
	}


	public String getLocation() {
		return location;
	}


	public int getWarehouseID() {
		return warehouseID;
	}


	public String getWarehouse() {
		return warehouse;
	}


	public double getAmount() {
		return amount;
	}


	public String getAmountUnit() {
		return amountUnit;
	}


	public BigDecimal getEk() {
		return ek;
	}


	public String getPriceUnit() {
		return priceUnit;
	}


	public String getDate() {
		return date;
	}
	
	public ObservableList<ModelStock> getObsListStock(){
		return obsListStock;
	}
	
	
}
