package de.michaprogs.crm.supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelSupplier {

	private int supplierID;
	private String name1;
	private String name2;
	private String street;
	private String land;
	private int zip;
	private String location;
	
	private String phone;
	private String fax;
	private String email;
	private String web;
	private String contact;
	private String ustID;
	
	private String payment;
	private String IBAN;
	private String BIC;
	private String bank;
	private int paymentSkonto;
	private int paymentNetto;
	private int skonto;
	
	private String lastChange;
	
	private ObservableList<ModelSupplier> obsListSearch = FXCollections.observableArrayList();
	
	//Database
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ModelSupplier(){}
	
	/**
	 * Constructor for ObservableList (Supplier Search)
	 * @param _supplierID
	 * @param _name1
	 * @param _name2
	 * @param _street
	 * @param _land
	 * @param _zip
	 * @param _location
	 * @param _phone
	 */
	public ModelSupplier(	int _supplierID,
							String _name1,
							String _name2,
							String _street,
							String _land,
							int _zip,
							String _location,
							String _phone){
		
		this.supplierID = _supplierID;
		this.name1 = _name1;
		this.name2 = _name2;
		this.street = _street;
		this.land = _land;
		this.zip = _zip;
		this.location = _location;
		this.phone = _phone;
		
	}
	
	public void insertSupplier(	int _supplierID,
								String _name1,
								String _name2,
								String _street,
								String _land,
								int _zip,
								String _location,
								String _phone,
								String _fax,
								String _email,
								String _web,
								String _contact,
								String _ustID,
								String _payment,
								String _IBAN,
								String _BIC,
								String _bank,
								int _paymentSkonto,
								int _paymentNetto,
								int _skonto,
								String _lastChange){
		
		try{
			
			String stmt = "INSERT INTO supplier ("
												+ "supplierID,"
												+ "name1,"
												+ "name2,"
												+ "street,"
												+ "land,"
												+ "zip,"
												+ "location,"
												+ "phone,"
												+ "fax,"
												+ "email,"
												+ "web,"
												+ "contactperson,"
												+ "ustID,"
												+ "payment,"
												+ "iban,"
												+ "bic,"
												+ "bank,"
												+ "paymentskonto,"
												+ "paymentnetto,"
												+ "skonto,"
												+ "lastChange)"
												+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, _supplierID);
			i++;
			ps.setString(i, _name1);
			i++;
			ps.setString(i, _name2);
			i++;
			ps.setString(i, _street);
			i++;
			if(_land == null){ //if the combobox is empty the string would be 'null'
				_land = "";
			}
			ps.setString(i, _land);
			i++;
			ps.setInt(i, _zip);
			i++;
			ps.setString(i, _location);
			i++;
			ps.setString(i, _phone);
			i++;
			ps.setString(i, _fax);
			i++;
			ps.setString(i, _email);
			i++;
			ps.setString(i, _web);
			i++;
			ps.setString(i, _contact);
			i++;
			ps.setString(i, _ustID);
			i++;
			if(_payment == null){ //if the combobox is empty the string would be 'null'
				_payment = "";
			}
			ps.setString(i, _payment);
			i++;
			ps.setString(i, _IBAN);
			i++;
			ps.setString(i, _BIC);
			i++;
			ps.setString(i, _bank);
			i++;
			ps.setInt(i, _paymentSkonto);
			i++;
			ps.setInt(i, _paymentNetto);
			i++;
			ps.setInt(i, _skonto);
			i++;
			ps.setString(i, _lastChange);
			
			ps.execute();
			
			System.out.println("Lieferant " + _supplierID + " " + _name1 + " wurde zur Datenbank hinzugefügt");
			
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
	
	public void selectSupplier(int _supplierID){
	
		try{
			
			String stmt = "SELECT * FROM supplier WHERE supplierID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, _supplierID);
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				this.supplierID = rs.getInt("supplierID");
				this.name1 = rs.getString("name1");
				this.name2 = rs.getString("name2");
				this.street = rs.getString("street");
				this.land = rs.getString("land");
				this.zip = rs.getInt("zip");
				this.location = rs.getString("location");
				
				this.phone = rs.getString("phone");
				this.fax = rs.getString("fax");
				this.email = rs.getString("email");
				this.web = rs.getString("web");
				this.contact = rs.getString("contactperson");
				this.ustID = rs.getString("ustID");
				
				this.payment = rs.getString("payment");
				this.IBAN = rs.getString("iban");
				this.BIC = rs.getString("bic");
				this.bank = rs.getString("bank");
				this.paymentSkonto = rs.getInt("paymentSkonto");
				this.paymentNetto = rs.getInt("paymentNetto");
				this.skonto = rs.getInt("skonto");
				
				this.lastChange = rs.getString("lastChange");
				
			}
			
			System.out.println("Lieferant " + supplierID + " " + name1 + " wurde aus Datenbank geladen");
			
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
	
	public void searchSupplier(	String _supplierID, //Search as String
								String _name1,
								String _name2,
								String _street,
								String _land,
								String _zip, //Search as String
								String _location,
								String _phone){
		
		try{
			
			String stmt = "SELECT * FROM supplier WHERE    supplierID LIKE ? AND "
														+ "name1 LIKE ? AND "
														+ "name2 LIKE ? AND "
														+ "street LIKE ? AND "
														+ "land LIKE ? AND "
														+ "zip LIKE ? AND "
														+ "location LIKE ? AND "
														+ "phone LIKE ? ";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setString(i, _supplierID + "%");
			i++;
			ps.setString(i, _name1 + "%");
			i++;
			ps.setString(i, _name2 + "%");
			i++;
			ps.setString(i, _street + "%");
			i++;
			if(_land == null){ 
				_land = "";
			}
			ps.setString(i, _land + "%");
			i++;
			ps.setString(i, _zip + "%");
			i++;
			ps.setString(i, _location + "%");
			i++;
			ps.setString(i, _phone + "%"); 
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				obsListSearch.add(new ModelSupplier(
					rs.getInt("supplierID"), 
					rs.getString("name1"), 
					rs.getString("name2"),
					rs.getString("street"),
					rs.getString("land"), 
					rs.getInt("zip"), 
					rs.getString("location"),
					rs.getString("phone")
				));
				
			}
			
			System.out.println("Alle Lieferanten mit übereinstimmenden Suchfaktoren aus Datenbank geladen");
			
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
	 * CLOSE CONNECTION
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
	public boolean validate(	int _supplierID,
								String _name1){
		
		if( 	_supplierID != 0 &&
			! 	_name1.isEmpty() ){
			return true;
		}else if(_supplierID == 0){
			System.out.println("Bitte gültige Lieferanten-Nr. wählen!");
			return false;
		}else if(_name1.isEmpty()){
			System.out.println("Bitte gültigen Name1 wählen!");
			return false;
		}else{
			System.err.println("***ModelSupplier.java -> validate: Unbekannter Fehler");
			return false;
		}
		
	}

	/*
	 * GETTER & SETTER
	 */
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

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public String getWeb() {
		return web;
	}

	public String getContact() {
		return contact;
	}

	public String getUstID() {
		return ustID;
	}

	public String getPayment() {
		return payment;
	}

	public String getIBAN() {
		return IBAN;
	}

	public String getBIC() {
		return BIC;
	}

	public String getBank() {
		return bank;
	}

	public int getPaymentSkonto() {
		return paymentSkonto;
	}

	public int getPaymentNetto() {
		return paymentNetto;
	}

	public int getSkonto() {
		return skonto;
	}

	public String getLastChange() {
		return lastChange;
	}	
	
	public ObservableList<ModelSupplier> getObsListSearch(){
		return obsListSearch;
	}
	
}
