package de.michaprogs.crm.article;

import java.math.BigDecimal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelArticle {

	/* First Block (Main-Data) */
	private int articleID;
	private String description1;
	private String description2;
	private String category;
	private int eanID;
	
	/* Second Block (Weight & Size) */
	private String barrelsize;
	private String bolting;
	private int length;
	private int width;
	private int height;
	private double weight;
	private double desity;
	
	/* Third Block (Price) */
	private BigDecimal ek;
	private BigDecimal vk;
	private BigDecimal total;
	private int priceUnit;
	private double amount;
	private String amountUnit;
	private int tax;
	
	/* Longtext */
	private String longtext;
	
	/* Stock */
	private int stockMinUnit;
	private int stockMaxUnit;
	private int stockAlertUnit;
	
	private String lastChange;
	private String imageFilepath;
	
	private ObservableList<ModelArticle> obsListSearch = FXCollections.observableArrayList();
	
	public ModelArticle(){}
	
	/**
	 * Constructor for Database (Insert Article)
	 * Constructor for Database (Update Article)
	 * @param articleID
	 * @param description1
	 * @param description2
	 * @param category
	 * @param eanID
	 * @param barrelsize
	 * @param bolting
	 * @param length
	 * @param width
	 * @param height
	 * @param weight
	 * @param desity
	 * @param ek
	 * @param vk
	 * @param priceUnit
	 * @param amountUnit
	 * @param tax
	 * @param longtext
	 * @param stockMinUnit
	 * @param stockMaxUnit
	 * @param stockAlertUnit
	 * @param lastChange
	 * @param imageFilepath
	 */
	public ModelArticle(	int articleID, 
							String description1, 
							String description2, 
							String category, 
							int eanID,
							String barrelsize, 
							String bolting, 
							int length, 
							int width, 
							int height, 
							double weight, 
							double desity,
							BigDecimal ek, 
							BigDecimal vk, 
							int priceUnit, 
							String amountUnit, 
							int tax,
							String longtext, 
							String imageFilepath,
							int stockMinUnit, 
							int stockMaxUnit, 
							int stockAlertUnit, 
							String lastChange) {
		
		this.articleID = articleID;
		this.description1 = description1;
		this.description2 = description2;
		this.category = category;
		this.eanID = eanID;
		this.barrelsize = barrelsize;
		this.bolting = bolting;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.desity = desity;
		this.ek = ek;
		this.vk = vk;
		this.priceUnit = priceUnit;
		this.amountUnit = amountUnit;
		this.tax = tax;
		this.longtext = longtext;
		this.stockMinUnit = stockMinUnit;
		this.stockMaxUnit = stockMaxUnit;
		this.stockAlertUnit = stockAlertUnit;
		this.lastChange = lastChange;
		this.imageFilepath = imageFilepath;
		
	}

	/**
	 * Constructor for Database (Select Article)
	 * Constructor for Database (Delete Article)
	 * @param _articleID
	 */
	public ModelArticle(	int _articleID){
		this.articleID = _articleID;
	}
	
	/**
	 * Constructor for ObservableList (Article Search)
	 * @param _articleID
	 * @param _description1
	 * @param _description2
	 * @param _barrelsize
	 * @param _bolting
	 */
	public ModelArticle(	int _articleID,
							String _description1,
							String _description2,
							String _barrelsize,
							String _bolting){
		
		this.articleID = _articleID;
		this.description1 = _description1;
		this.description2 = _description2;
		this.barrelsize = _barrelsize;
		this.bolting = _bolting;
		
	}
	
	/**
	 * Constructor for ObservableList (Offer Article)
	 * @param _articleID
	 * @param _description1
	 * @param _description2
	 * @param _barrelsize
	 * @param _bolting
	 * @param _amount
	 * @param _amountUnit
	 * @param _vk
	 * @param _priceUnit
	 * @param _total
	 * @param _tax
	 */
	public ModelArticle(	int _articleID,
							String _description1,
							String _description2,
							String _barrelsize,
							String _bolting,
							double _amount,
							String _amountUnit,
							BigDecimal _vk,
							int _priceUnit,
							BigDecimal _total,
							int _tax
							){
		
		this.articleID = _articleID;
		this.description1 = _description1;
		this.description2 = _description2;
		this.barrelsize = _barrelsize;
		this.bolting = _bolting;
		this.amount = _amount;
		this.amountUnit = _amountUnit;
		this.vk = _vk;
		this.priceUnit = _priceUnit;
		this.total = _total;
		this.tax = _tax;
		
	}
	
	/*
	 * GETTER
	 */
	public int getArticleID() {
		return articleID;
	}

	public String getDescription1() {
		return description1;
	}

	public String getDescription2() {
		return description2;
	}

	public String getCategory() {
		return category;
	}

	public int getEanID() {
		return eanID;
	}

	public String getBarrelsize() {
		return barrelsize;
	}

	public String getBolting() {
		return bolting;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public double getDesity() {
		return desity;
	}

	public BigDecimal getEk() {
		return ek;
	}

	public BigDecimal getVk() {
		return vk;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public int getPriceUnit() {
		return priceUnit;
	}

	public double getAmount(){
		return amount;
	}
	
	public String getAmountUnit() {
		return amountUnit;
	}

	public int getTax() {
		return tax;
	}

	public String getLongtext() {
		return longtext;
	}

	public int getStockMinUnit() {
		return stockMinUnit;
	}

	public int getStockMaxUnit() {
		return stockMaxUnit;
	}

	public int getStockAlertUnit() {
		return stockAlertUnit;
	}

	public String getLastChange() {
		return lastChange;
	}

	public String getImageFilepath() {
		return imageFilepath;
	}

	public ObservableList<ModelArticle> getObsListSearch() {
		return obsListSearch;
	}

	
	/*
	 * SETTER
	 */
	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setEanID(int eanID) {
		this.eanID = eanID;
	}

	public void setBarrelsize(String barrelsize) {
		this.barrelsize = barrelsize;
	}

	public void setBolting(String bolting) {
		this.bolting = bolting;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setDesity(double desity) {
		this.desity = desity;
	}

	public void setEk(BigDecimal ek) {
		this.ek = ek;
	}

	public void setVk(BigDecimal vk) {
		this.vk = vk;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setPriceUnit(int priceUnit) {
		this.priceUnit = priceUnit;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public void setLongtext(String longtext) {
		this.longtext = longtext;
	}

	public void setStockMinUnit(int stockMinUnit) {
		this.stockMinUnit = stockMinUnit;
	}

	public void setStockMaxUnit(int stockMaxUnit) {
		this.stockMaxUnit = stockMaxUnit;
	}

	public void setStockAlertUnit(int stockAlertUnit) {
		this.stockAlertUnit = stockAlertUnit;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public void setImageFilepath(String imageFilepath) {
		this.imageFilepath = imageFilepath;
	}

	public void setObsListSearch(ObservableList<ModelArticle> obsListSearch) {
		this.obsListSearch = obsListSearch;
	}
	
}
