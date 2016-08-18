package de.michaprogs.crm.article.supplier;

import java.math.BigDecimal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelArticleSupplier {

	private int supplierID;
	private String supplierName1;
	private int articleID;
	private String supplierArticleID;
	private String supplierDescription1;
	private String supplierDescription2;
	private BigDecimal supplierEk;
	private int supplierPriceUnit;
	private String supplierAmountUnit;
	
	private ObservableList<ModelArticleSupplier> obsListArticleSupplier = FXCollections.observableArrayList();
	
	public ModelArticleSupplier(){}
	
	/**
	 * Constructor for ObservableList (Table ArticleSupplier)
	 * @param _supplierID
	 * @param _supplierName1
	 * @param _supplierArticleID
	 * @param _supplierDescription1
	 * @param _supplierDescription2
	 * @param _supplierEk
	 * @param _supplierPriceUnit
	 * @param _supplierAmountUnit
	 */
	public ModelArticleSupplier(	int _supplierID,
									String _supplierName1,
									String _supplierArticleID,
									String _supplierDescription1,
									String _supplierDescription2,
									BigDecimal _supplierEk,
									int _supplierPriceUnit,
									String _supplierAmountUnit){
		
		this.supplierID = _supplierID;
		this.supplierName1 = _supplierName1;
		this.supplierArticleID = _supplierArticleID;
		this.supplierDescription1 = _supplierDescription1;
		this.supplierDescription2 = _supplierDescription2;
		this.supplierEk = _supplierEk;
		this.supplierPriceUnit = _supplierPriceUnit;
		this.supplierAmountUnit = _supplierAmountUnit;
		
	}
	
	/**
	 * Constructor for Database (Insert ArticleSupplier)
	 * Constructor for Database (Update ArticleSupplier)
	 * @param _articleID
	 * @param _supplierID
	 * @param _supplierArticleID
	 * @param _supplierDescription1
	 * @param _supplierDescription2
	 * @param _supplierEk
	 * @param _supplierPriceUnit
	 * @param _supplierAmountUnit
	 */
	public ModelArticleSupplier(	int _articleID,
									int _supplierID,
									String _supplierArticleID,
									String _supplierDescription1,
									String _supplierDescription2,
									BigDecimal _supplierEk,
									int _supplierPriceUnit,
									String _supplierAmountUnit){
		
		this.articleID = _articleID;
		this.supplierID = _supplierID;
		this.supplierArticleID = _supplierArticleID;
		this.supplierDescription1 = _supplierDescription1;
		this.supplierDescription2 = _supplierDescription2;
		this.supplierEk = _supplierEk;
		this.supplierPriceUnit = _supplierPriceUnit;
		this.supplierAmountUnit = _supplierAmountUnit;
		
	}
	
	/**
	 * Constructor for Database (Select ArticleSupplier)
	 * Constructor for Database (Delete ArticleSupplier)
	 * @param _articleID
	 */
	public ModelArticleSupplier(	int _articleID){
		this.articleID = _articleID;
	}
				
	/*
	 * VALIDATION
	 */
	public boolean validateArticleSupplier(	int _supplierID,
											String _supplierDescription1){
		
		if(	_supplierID != 0 &&
			! _supplierDescription1.isEmpty()){
			return true;
		}else if(_supplierID == 0){
			System.out.println("Bitte Lieferanten wählen");
			return false;
		}else if(_supplierDescription1.isEmpty()){
			System.out.println("Bitte mindestens Bezeichnung1 eintragen.");
			return false;
		}else{
			System.out.println("***ModelArticleSupplier.java -> validation: Unbekannter Fehler");
			return false;
		}
		
	}

	/*
	 * GETTER & SETTER
	 */
	public int getSupplierID() {
		return supplierID;
	}

	public int getArticleID() {
		return articleID;
	}

	public String getSupplierArticleID() {
		return supplierArticleID;
	}

	public String getSupplierDescription1() {
		return supplierDescription1;
	}

	public String getSupplierDescription2() {
		return supplierDescription2;
	}

	public BigDecimal getSupplierEk() {
		return supplierEk;
	}

	public String getSupplierName1() {
		return supplierName1;
	}

	public int getSupplierPriceUnit() {
		return supplierPriceUnit;
	}

	public String getSupplierAmountUnit() {
		return supplierAmountUnit;
	}
	
	public ObservableList<ModelArticleSupplier> getObsListArticleSupplier(){
		return obsListArticleSupplier;
	}
	
}
