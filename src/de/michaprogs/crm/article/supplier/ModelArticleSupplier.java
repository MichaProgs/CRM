package de.michaprogs.crm.article.supplier;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import de.michaprogs.crm.supplier.ModelSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelArticleSupplier {

	private int supplierID;
	private String supplierName1;
	private String articleID;
	private String supplierArticleID;
	private String supplierDescription1;
	private String supplierDescription2;
	private BigDecimal supplierEk;
	private int supplierPriceUnit;
	private String supplierAmountUnit;
	
	private ObservableList<ModelArticleSupplier> obsListArticleSupplier = FXCollections.observableArrayList();
	
	//Database
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
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
	public ModelArticleSupplier(int _supplierID,
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
	
	public void insertArticleSupplier(	int _articleID,
										int _supplierID,
										String _supplierArticleID,
										String _supplierDescription1,
										String _supplierDescription2,
										BigDecimal _supplierEk,
										int _supplierPriceUnit,
										String _supplierAmountUnit){
		
		try{
			
			String stmt = "INSERT INTO articlesupplier ("
					+ "articleID,"
					+ "supplierID,"
					+ "supplierArticleID,"
					+ "supplierDescription1,"
					+ "supplierDescription2,"
					+ "supplierEk,"
					+ "supplierPriceUnit,"
					+ "supplierAmountUnit)"
					+ "VALUES (?,?,?,?,?,?,?,?)"; //8
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			ps.setInt(i, _supplierID);
			i++;
			ps.setString(i, _supplierArticleID);
			i++;
			ps.setString(i, _supplierDescription1);
			i++;
			ps.setString(i, _supplierDescription2);
			i++;
			ps.setBigDecimal(i, _supplierEk);
			i++;
			ps.setInt(i, _supplierPriceUnit);
			i++;
			ps.setString(i, _supplierAmountUnit);
			i++;
			
			ps.execute();
			
			System.out.println("Hersteller " + _supplierID + " zu Artikel " + _articleID + " wurde der Datenbank hinzugefügt!");
			
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
	
	public void selectArticleSupplier(int _articleID){
		
		try{
			
			String stmt = "SELECT * FROM articlesupplier WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			
			ModelSupplier supplier = new ModelSupplier();
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				supplier.selectSupplier(rs.getInt("supplierID"));
				
				obsListArticleSupplier.add(new ModelArticleSupplier(
					rs.getInt("supplierID"),
					supplier.getName1(),
					rs.getString("supplierArticleID"),
					rs.getString("supplierDescription1"),
					rs.getString("supplierDescription2"),
					rs.getBigDecimal("supplierEk"),
					rs.getInt("supplierPriceUnit"),
					rs.getString("supplierAmountUnit")
				));
				
			}
			
			System.out.println("Alle Artikelhersteller zu Artikel " + _articleID + " geladen");
			
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
	
	public void deleteArticleSupplier(int _articleID){
		
		try{
			
			String stmt = "DELETE FROM articlesupplier WHERE articleID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _articleID);
			i++;
			
			ps.execute();
			
			System.out.println("Alle Artikelhersteller zu Artikel " + _articleID + " gelöscht");
			
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
	
	//CLOSE CONNECTION
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

	public String getArticleID() {
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
