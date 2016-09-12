package de.michaprogs.crm.supplier.article;

import java.math.BigDecimal;

public class ModelSupplierArticle {

	private int supplierID;
	private int articleID;
	private String description1;
	private String descrtipion2;
	private String barrelsize;
	private String bolting;
	private BigDecimal ek;
	private int priceUnit;
	private String amountUnit;
	
	public ModelSupplierArticle(){}

	public ModelSupplierArticle(int supplierID, 
								int articleID, 
								String description1, 
								String descrtipion2,
								String barrelsize, 
								String bolting, 
								BigDecimal ek, 
								int priceUnit, 
								String amountUnit) {
		
		this.supplierID = supplierID;
		this.articleID = articleID;
		this.description1 = description1;
		this.descrtipion2 = descrtipion2;
		this.barrelsize = barrelsize;
		this.bolting = bolting;
		this.ek = ek;
		this.priceUnit = priceUnit;
		this.amountUnit = amountUnit;
		
	}

	/*
	 * GETTER & SETTER
	 */
	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescrtipion2() {
		return descrtipion2;
	}

	public void setDescrtipion2(String descrtipion2) {
		this.descrtipion2 = descrtipion2;
	}

	public String getBarrelsize() {
		return barrelsize;
	}

	public void setBarrelsize(String barrelsize) {
		this.barrelsize = barrelsize;
	}

	public String getBolting() {
		return bolting;
	}

	public void setBolting(String bolting) {
		this.bolting = bolting;
	}

	public BigDecimal getEk() {
		return ek;
	}

	public void setEk(BigDecimal ek) {
		this.ek = ek;
	}

	public int getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(int priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	
	
	
}
