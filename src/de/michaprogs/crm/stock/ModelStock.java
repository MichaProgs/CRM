package de.michaprogs.crm.stock;

import java.math.BigDecimal;

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
	
	
	
}
