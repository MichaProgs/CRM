package de.michaprogs.crm.article;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
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
	private int priceUnit;
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
	
	//Database
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public ModelArticle(){}
	
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
							String _bolting
							){
		
		this.articleID = _articleID;
		this.description1 = _description1;
		this.description2 = _description2;
		this.barrelsize = _barrelsize;
		this.bolting = _bolting;
		
	}
	
	public void insertArticle(	int _articleID,
								String _description1,
								String _description2,
								String _category,
								int _eanID,
								String _barrelsize,
								String _bolting,
								int _length,
								int _width,
								int _height,
								double _weight,
								double _desity,
								BigDecimal _ek,
								BigDecimal _vk,
								int _priceUnit,
								String _amountUnit,
								int _tax,
								String _longtext,
								String _imageFilepath,
								int _stockMinUnit,
								int _stockMaxUnit,
								int _stockAlertUnit,
								String _lastChange){
		
		try{
			
			String stmt = "INSERT INTO article ("
					+ "articleID,"
					+ "description1,"
					+ "description2,"
					+ "category,"
					+ "eanID,"
					+ "barrelsize,"
					+ "bolting,"
					+ "length,"
					+ "width,"
					+ "height,"
					+ "weight,"
					+ "desity,"
					+ "ek,"
					+ "vk,"
					+ "priceunit,"
					+ "amountunit,"
					+ "tax,"
					+ "longtext,"
					+ "imagefilepath,"
					+ "stockminunit,"
					+ "stockmaxunit,"
					+ "stockalertunit,"
					+ "lastchange)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //23
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			ps.setString(i, _description1);
			i++;
			ps.setString(i, _description2);
			i++;
			ps.setString(i, _category);
			i++;
			ps.setInt(i, _eanID);
			i++;
			ps.setString(i, _barrelsize);
			i++;
			ps.setString(i, _bolting);
			i++;
			ps.setInt(i, _length);
			i++;
			ps.setInt(i, _width);
			i++;
			ps.setInt(i, _height);
			i++;
			ps.setDouble(i, _weight);
			i++;
			ps.setDouble(i, _desity);
			i++;
			ps.setBigDecimal(i, _ek);
			i++;
			ps.setBigDecimal(i, _vk);
			i++;
			ps.setInt(i, _priceUnit);
			i++;
			ps.setString(i, _amountUnit);
			i++;
			ps.setInt(i, _tax);
			i++;
			ps.setString(i, _longtext);
			i++;
			ps.setString(i, _imageFilepath);
			i++;
			ps.setInt(i, _stockMinUnit);
			i++;
			ps.setInt(i, _stockMaxUnit);
			i++;
			ps.setInt(i, _stockAlertUnit);
			i++;
			ps.setString(i, _lastChange);
			i++;
			
			ps.execute();
			
			System.out.println("Artikel " + _articleID + " " + _description1 + " wurde der Datenbank hinzugefügt!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	public void searchArticle(	String _articleID, //Search as String
								String _description1,
								String _description2,
								String _barrelsize,
								String _bolting){
		
		try{
			
			String stmt = "SELECT * FROM article WHERE 	articleID LIKE ? AND"
													+ "	description1 LIKE ? AND"
													+ " description2 LIKE ? AND"
													+ " barrelsize LIKE ? AND"
													+ " bolting LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _articleID + "%");
			i++;
			ps.setString(i, _description1 + "%");
			i++;
			ps.setString(i, _description2 + "%");
			i++;
			ps.setString(i, _barrelsize + "%");
			i++;
			ps.setString(i, _bolting + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListSearch.add(new ModelArticle(
					rs.getInt("articleID"), 
					rs.getString("description1"), 
					rs.getString("description2"), 
					rs.getString("barrelsize"), 
					rs.getString("bolting")
				));
				
			}
			
			System.out.println("Alle Artikel mit übereinstimmenden Suchfaktoren aus Datenbank geladen");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void selectArticle(int _articleID){
		
		try{
			
			String stmt = "SELECT * FROM article WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				this.articleID = rs.getInt("articleID");
				this.description1 = rs.getString("description1");
				this.description2 = rs.getString("description2");
				this.category = rs.getString("category");
				this.eanID = rs.getInt("eanID");
				this.barrelsize = rs.getString("barrelsize");
				this.bolting = rs.getString("bolting");
				this.length = rs.getInt("length");
				this.width = rs.getInt("width");
				this.height = rs.getInt("height");
				this.weight = rs.getDouble("weight");
				this.desity = rs.getDouble("desity");
				this.ek = rs.getBigDecimal("ek");
				this.vk = rs.getBigDecimal("vk");
				this.priceUnit = rs.getInt("priceunit");
				this.amountUnit = rs.getString("amountunit");
				this.tax = rs.getInt("tax");
				this.longtext = rs.getString("longtext");
				
			}
			
			System.out.println("Artikel " + articleID + " " + description1 + " aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	public void updateArticle(	int _articleID,
								String _description1,
								String _description2,
								String _category,
								int _eanID,
								String _barrelsize,
								String _bolting,
								int _length,
								int _width,
								int _height,
								double _weight,
								double _desity,
								BigDecimal _ek,
								BigDecimal _vk,
								int _priceUnit,
								String _amountUnit,
								int _tax,
								String _longtext,
								String _imageFilepath,
								int _stockMinUnit,
								int _stockMaxUnit,
								int _stockAlertUnit,
								String _lastChange){
		
		try{
			
			String stmt = "UPDATE article SET  description1 = ?,"
											+ "description2 = ?,"
											+ "category = ?,"
											+ "eanID = ?,"
											+ "barrelsize = ?,"
											+ "bolting = ?,"
											+ "length = ?,"
											+ "width = ?,"
											+ "height = ?,"
											+ "weight = ?,"
											+ "desity = ?,"
											+ "ek = ?,"
											+ "vk = ?,"
											+ "priceunit = ?,"
											+ "amountunit = ?,"
											+ "tax = ?,"
											+ "longtext = ?,"
											+ "imagefilepath = ?,"
											+ "stockminunit = ?,"
											+ "stockmaxunit = ?,"
											+ "stockalertunit = ?,"
											+ "lastchange = ? "
											+ "WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setString(i, _description1);
			i++;
			ps.setString(i, _description2);
			i++;
			ps.setString(i, _category);
			i++;
			ps.setInt(i, _eanID);
			i++;
			ps.setString(i, _barrelsize);
			i++;
			ps.setString(i, _bolting);
			i++;
			ps.setInt(i, _length);
			i++;
			ps.setInt(i, _width);
			i++;
			ps.setInt(i, _height);
			i++;
			ps.setDouble(i, _weight);
			i++;
			ps.setDouble(i, _desity);
			i++;
			ps.setBigDecimal(i, _ek);
			i++;
			ps.setBigDecimal(i, _vk);
			i++;
			ps.setInt(i, _priceUnit);
			i++;
			ps.setString(i, _amountUnit);
			i++;
			ps.setInt(i, _tax);
			i++;
			ps.setString(i, _longtext);
			i++;
			ps.setString(i, _imageFilepath);
			i++;
			ps.setInt(i, _stockMinUnit);
			i++;
			ps.setInt(i, _stockMaxUnit);
			i++;
			ps.setInt(i, _stockAlertUnit);
			i++;
			ps.setString(i, _lastChange);
			i++;
			
			//Always Last
			ps.setInt(i, _articleID);
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Artikel " + _articleID + " " + _description1 + " wurde in Datenbank gespeichert!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteArticle(int _articleID){
		
		try{
			
			String stmt = "DELETE FROM article WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _articleID);
			ps.execute();
			
			System.out.println("Artikel " + _articleID + " wurde aus der Datenbank gelöscht");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * CloseConnection
	 */
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
	 * VALIDATION
	 */
	public boolean validate(	int _articleID,
								String _description1){
		
		if(	_articleID != 0 &&
			! _description1.isEmpty()){
			return true;
		}else if(_articleID == 0){
			System.out.println("Bitte gültige 'Artikelnummer' wählen!");
			return false;
		}else if(_description1.isEmpty()){
			System.out.println("Bitte gültige 'Bezeichnung1' wählen!");
			return false;
		}else{
			System.err.println("***ModelArticle.java -> validate: Unbekannter Fehler!");
			return false;
		}
		
	}

	/*
	 * GETTER & SETTER
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

	public int getPriceUnit() {
		return priceUnit;
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
	
}
