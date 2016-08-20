package de.michaprogs.crm.offer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelOffer {

	private int offerID;
	private String offerDate;
	private String request;
	private String requestDate;
	
	//TODO CLERK INSERT
	
	private String notes;
	
	/* CUSTOMER */
	private int customerID;
	
	/* ARTICLE */
	private int articleID;
	private double amount;
	private BigDecimal ek;
	private BigDecimal vk;
	
	private ObservableList<ModelOffer> obsListOfferSearch = FXCollections.observableArrayList();
	private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	
	/* DATEBASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ModelOffer(){}
	
	/**
	 * Constructor for ObservableList (Offer Search)
	 * @param _offerID
	 * @param _offerDate
	 * @param _customerID
	 * @param _request
	 * @param _requestDate
	 */
	public ModelOffer(	int _offerID,
						String _offerDate,
						int _customerID,
						String _request,
						String _requestDate){
		
		this.offerID = _offerID;
		this.offerDate = _offerDate;
		this.customerID = _customerID;
		this.request = _request;
		this.requestDate = _requestDate;
		
	}
	
	public void insertOffer(	int _offerID,
								String _offerDate,
								String _request,
								String _requestDate,
								String _notes,
								int _customerID,
								ObservableList<ModelArticle> _obsListArticle){
		
		try{
			
			String stmt = "INSERT INTO offer ("
					+ "offerID,"
					+ "offerDate,"
					+ "request,"
					+ "requestDate,"
					+ "notes,"
					+ "customerID"
					+ ")"
					+ "VALUES(?,?,?,?,?,?)"; //6
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _offerID);
			i++;
			ps.setString(i, _offerDate);
			i++;
			ps.setString(i, _request);
			i++;
			ps.setString(i, _requestDate);
			i++;
			ps.setString(i, _notes);
			i++;
			ps.setInt(i, _customerID);
			i++;
			
			ps.execute();
			
			System.out.println("Angebot " + _offerID + " wurde der Datenbank hinzugefügt!");
			
			/* OFFER ARTICLE */
			String stmtOfferArticle = "INSERT INTO offerarticle ("
													+ "offerID,"
													+ "articleID,"
													+ "amount,"
													+ "ek,"
													+ "vk,"
													+ "total"
													+ ")"
													+ "VALUES(?,?,?,?,?,?)"; //6
			
			for(int index = 0; index < _obsListArticle.size(); index++){
				
				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtOfferArticle);
				int count = 1;
				ps.setInt(count, _offerID);
				count++;
				ps.setInt(count, _obsListArticle.get(index).getArticleID());
				count++;
				ps.setDouble(count, _obsListArticle.get(index).getAmount());
				count++;
				ps.setBigDecimal(count, _obsListArticle.get(index).getEk());
				count++;
				ps.setBigDecimal(count, _obsListArticle.get(index).getVk());
				count++;
				ps.setBigDecimal(count, _obsListArticle.get(index).getTotal());
				count++;
				
				ps.execute();
				
				System.out.println("Angebots-Artikel " + _obsListArticle.get(index).getArticleID() + " " + _obsListArticle.get(index).getDescription1() + " wurde der Datenbank hinzugefügt!");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void selectOffer(	int _offerID){
		
		try{
		
			String stmt = "SELECT * FROM offer WHERE offerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, _offerID);
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				this.offerID = rs.getInt("offerID");
				this.offerDate = rs.getString("offerDate");
				this.request = rs.getString("request");
				this.requestDate = rs.getString("requestDate");
				this.notes = rs.getString("notes");
				this.customerID = rs.getInt("customerID");
				
			}
			
			System.out.println("Angebot " + _offerID + " wurde aus Datenbank geladen!");
			
			/* OFFER ARTICLE */
			String stmtOfferArticle = "SELECT * FROM offerArticle WHERE offerID = ?";
			ps = con.prepareStatement(stmtOfferArticle);
			ps.setInt(1, _offerID);
			rs = ps.executeQuery();
			while(rs.next()){
				
				ModelArticle article = new SelectArticle(new ModelArticle(rs.getInt("articleID"))).getModelArticle();
				
				obsListArticle.add(new ModelArticle(
					rs.getInt("articleID"), 
					article.getDescription1(), 
					article.getDescription2(),
					article.getBarrelsize(), 
					article.getBolting(), 
					rs.getDouble("amount"), 
					article.getAmountUnit(), 
					rs.getBigDecimal("vk"),
					rs.getBigDecimal("ek"),
					article.getPriceUnit(), 
					rs.getBigDecimal("total"),
					article.getTax()
				));
						
				System.out.println("Angebots-Artikel " + articleID + " wurde aus Datenbank geladen!");
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void searchOffer(	String _offerID, //Search as String
								String _offerDate,
								String _customerID, //Search as String
								String _request,
								String _requestDate
								){
		
		try{
			
			String stmt = "SELECT * FROM offer WHERE   offerID LIKE ? AND "
													+ "offerDate LIKE ? AND "
													+ "customerID LIKE ? AND "
													+ "request LIKE ? AND "
													+ "requestDate LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _offerID + "%");
			i++;
			if(_offerDate.equals("null")){
				_offerDate = "";
			}
			ps.setString(i, _offerDate + "%");
			i++;
			ps.setString(i, _customerID + "%");
			i++;
			ps.setString(i, _request + "%");
			i++;
			if(_requestDate.equals("null")){
				_requestDate = "";
			}
			ps.setString(i, _requestDate + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListOfferSearch.add(new ModelOffer(
					rs.getInt("offerID"),
					rs.getString("offerDate"),
					rs.getInt("customerID"),
					rs.getString("request"),
					rs.getString("requestDate")
				));
				
			}
			
			System.out.println("Alle Angebote mit Übereinstimmenden Suchkriterien wurden geladen!");
					
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void updateOffer(	int _offerID,
								String _offerDate,
								String _request,
								String _requestDate,
								String _notes,
								int _customerID,
								ObservableList<ModelArticle> _obsListArticle){
		
		try{
			
			String stmt = "UPDATE offer SET offerDate = ?,"
										+ "request = ?,"
										+ "requestDate = ?,"
										+ "notes = ?,"
										+ "customerID = ? "
										
										+ "WHERE offerID = ?"; //ALWAYS LAST!
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _offerDate);
			i++;
			ps.setString(i, _request);
			i++;
			ps.setString(i, _requestDate);
			i++;
			ps.setString(i, _notes);
			i++;
			ps.setInt(i, _customerID);
			i++;
			ps.setInt(i, _offerID);
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Angebot " + _offerID + " wurden in Datenbank gespeichert!");
			
			/* OFFER ARTICLE */
			String stmtDeleteOfferArticle = "DELETE FROM offerarticle WHERE offerID = ?";
			ps = con.prepareStatement(stmtDeleteOfferArticle);
			ps.setInt(1, _offerID);
			ps.execute();
			
			System.out.println("Angebots-Artikel zu Angebot " + _offerID + " wurden aus Datenbank gelöscht!");
			
			String stmtOfferArticle = "INSERT INTO offerarticle ("
													+ "offerID,"
													+ "articleID,"
													+ "amount,"
													+ "ek,"
													+ "vk,"
													+ "total"
													+ ")"
													+ "VALUES(?,?,?,?,?,?)"; //6
			
			for(int index = 0; index < _obsListArticle.size(); index++){
				
				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtOfferArticle);
				int count = 1;
				ps.setInt(count, _offerID);
				count++;
				ps.setInt(count, _obsListArticle.get(index).getArticleID());
				count++;
				ps.setDouble(count, _obsListArticle.get(index).getAmount());
				count++;
				ps.setBigDecimal(count, _obsListArticle.get(index).getEk());
				count++;
				ps.setBigDecimal(count, _obsListArticle.get(index).getVk());
				count++;
				ps.setBigDecimal(count, _obsListArticle.get(index).getTotal());
				count++;
				
				ps.execute();
				
				System.out.println("Angebots-Artikel " + _obsListArticle.get(index).getArticleID() + " " + _obsListArticle.get(index).getDescription1() + " wurde der Datenbank hinzugefügt!");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	/*
	 * VALIDATION
	 */
	public boolean validate(	int _offerID,
								int _customerID,
								String _offerDate,
								String _requestDate,
								ObservableList<ModelArticle> _obsListArticle){
		
		if(_offerID != 0 && _customerID != 0 && _obsListArticle.size() > 0 && ! _offerDate.equals("") && ! _requestDate.equals("")){
			return true;
		}else if(_offerID == 0){
			System.out.println("Bitte gültige Angebots-Nr. verwenden!");
			return false;
		}else if(_customerID == 0){
			System.out.println("Bitte gültige Kunden-Nr. wählen!");
			return false;
		}else if(_offerDate.equals("")){
			System.out.println("Bitte gültiges Angebots-Datum wählen!");
			return false;
		}else if(_requestDate.equals("")){
			System.out.println("Bitte gülties Anfrage-Datum wählen!");
			return false;
		}else if(_obsListArticle.size() == 0){
			System.out.println("Bitte mindestens 1 Artikel angeben!");
			return false;
		}else{
			System.out.println("***ModelOffer.java -> validate(): Unbekannter Fehler");
			return false;
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

	
	/*
	 * GETTER & SETTER
	 */
	public int getOfferID() {
		return offerID;
	}

	public String getOfferDate() {
		return offerDate;
	}

	public String getRequest() {
		return request;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public String getNotes() {
		return notes;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getArticleID() {
		return articleID;
	}

	public double getAmount() {
		return amount;
	}

	public BigDecimal getEk() {
		return ek;
	}

	public BigDecimal getVk() {
		return vk;
	}

	public ObservableList<ModelOffer> getObsListOfferSearch() {
		return obsListOfferSearch;
	}
	
	public ObservableList<ModelArticle> getObsListArticle(){
		return obsListArticle;
	}
	
	
}
