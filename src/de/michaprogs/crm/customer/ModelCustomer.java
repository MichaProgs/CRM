package de.michaprogs.crm.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelCustomer {

	private int customerID;
	private String salutation;
	private String name1;
	private String name2;
	private String street;
	private String land;
	private int zip;
	private String location;
	
	private String phone;
	private String mobile;
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
	private String notes;
	
	private int billingID;

	private ObservableList<ModelCustomer> obsListCustomer = FXCollections.observableArrayList();
	
	//DATABASE
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ModelCustomer(){}
	
	/**
	 * Constructor for ObservableList (Customer Search)
	 * @param customerID
	 * @param name1
	 * @param name2
	 * @param street
	 * @param land
	 * @param zip
	 * @param location
	 * @param phone
	 */
	public ModelCustomer(	int customerID, 
							String name1, 
							String name2, 
							String street, 
							String land, 
							int zip,
							String location, 
							String phone) {
		
		this.customerID = customerID;
		this.name1 = name1;
		this.name2 = name2;
		this.street = street;
		this.land = land;
		this.zip = zip;
		this.location = location;
		this.phone = phone;
		
	}



	public void insertCustomer(	int _customerID, 
								String _salutation,
								String _name1, 
								String _name2, 
								String _street, 
								String _land, 
								int _zip,
								String _location, 
								
								String _phone, 
								String _mobile, 
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
								
								String _lastChange,
								String _notes,
								
								int _billingID) {
		
		try{
			
			String stmt = "INSERT INTO customer ("
					+ "customerID,"
					+ "salutation,"
					+ "name1,"
					+ "name2,"
					+ "street,"
					+ "land,"
					+ "zip,"
					+ "location,"
					+ "phone,"
					+ "mobile,"
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
					+ "lastChange,"
					+ "notes,"
					+ "billingID)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setInt(i, _customerID);
			i++;
			ps.setString(i, _salutation);
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
			ps.setString(i, _mobile);
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
			i++;
			ps.setString(i, _notes);
			i++;
			ps.setInt(i, _billingID);
			i++;
			
			ps.execute();
			
			System.out.println("Kunde " + _customerID + " " + _name1 + " wurde zur Datenbank hinzugefügt");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void selectCustomer(int _customerID){
		
		try{
			
			String stmt = "SELECT * FROM customer WHERE customerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _customerID);
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				this.customerID = rs.getInt("customerID");
				this.salutation = rs.getString("salutation");
				this.name1 = rs.getString("name1");
				this.name2 = rs.getString("name2");
				this.street = rs.getString("street");
				this.land = rs.getString("land");
				this.zip = rs.getInt("zip");
				this.location = rs.getString("location");
				
				this.phone = rs.getString("phone");
				this.mobile = rs.getString("mobile");
				this.fax = rs.getString("fax");
				this.email = rs.getString("email");
				this.web = rs.getString("web");
				this.contact = rs.getString("contactperson");
				this.ustID = rs.getString("ustID");
				
				this.payment = rs.getString("payment");
				this.IBAN = rs.getString("IBAN");
				this.BIC = rs.getString("BIC");
				this.bank = rs.getString("bank");
				this.paymentSkonto = rs.getInt("paymentSkonto");
				this.paymentNetto = rs.getInt("paymentNetto");
				this.skonto = rs.getInt("skonto");
				
				this.notes = rs.getString("notes");
				this.lastChange = rs.getString("lastChange");
				
				this.billingID = rs.getInt("billingID");
				
			}
			
			System.out.println("Kunde " + customerID + " " + name1 + " aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void searchCustomer(	String _customerID, //Search as String
								String _name1,
								String _name2,
								String _street,
								String _land,
								String _zip, //Search as String
								String _location,
								
								String _phone,
								String _mobile,
								String _fax,
								String _email
								){
		
		try{
			
			String stmt = "SELECT * FROM customer WHERE customerID LIKE ? AND "
													+ "name1 LIKE ? AND "
													+ "name2 LIKE ? AND "
													+ "street LIKE ? AND "
													+ "land LIKE ? AND "
													+ "zip LIKE ? AND "
													+ "location LIKE ? AND "
													+ "phone LIKE ? AND "
													+ "mobile LIKE ? AND "
													+ "fax LIKE ? AND "
													+ "email LIKE ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, _customerID + "%");
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
			i++;
			ps.setString(i, _mobile + "%");
			i++;
			ps.setString(i, _fax + "%");
			i++;
			ps.setString(i, _email + "%");
			i++;
			
			rs = ps.executeQuery();
			while(rs.next()){
								
				this.obsListCustomer.add(new ModelCustomer(
					rs.getInt("customerID"), 
					rs.getString("name1"), 
					rs.getString("name2"), 
					rs.getString("street"), 
					rs.getString("land"), 
					rs.getInt("zip"), 
					rs.getString("location"), 
					rs.getString("phone")
				));
				
			}
			
			System.out.println("Alle Kunden mit übereinstimmenden Suchfaktoren aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void updateCustomer(	int _customerID, 
								String _salutation,
								String _name1, 
								String _name2, 
								String _street, 
								String _land, 
								int _zip,
								String _location, 
								
								String _phone, 
								String _mobile, 
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
								
								String _lastChange,
								String _notes,
								
								int _billingID
								){
	
		try{
			
			String stmt = "UPDATE customer SET salutation = ?,"
											+ "name1 = ?,"
											+ "name2 = ?,"
											+ "street = ?,"
											+ "land = ?,"
											+ "zip = ?,"
											+ "location = ?,"
											+ "phone = ?,"
											+ "mobile = ?,"
											+ "fax = ?,"
											+ "email = ?,"
											+ "web = ?,"
											+ "contactperson = ?,"
											+ "ustID = ?,"
											+ "payment = ?,"
											+ "iban = ?,"
											+ "bic = ?,"
											+ "bank = ?,"
											+ "paymentSkonto = ?,"
											+ "paymentNetto = ?,"
											+ "skonto = ?,"
											+ "lastChange = ?,"
											+ "notes = ?,"
											+ "billingID = ? "
											
											+ "WHERE customerID = ?"; //ALWAYS LAST!
											

			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			
			int i = 1;
			ps.setString(i, _salutation);
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
			ps.setString(i, _mobile);
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
			i++;
			ps.setString(i, _notes);
			i++;
			ps.setInt(i, _billingID);
			i++;
			
			//ALWAYS LAST
			ps.setInt(i, _customerID);
			i++;
			
			ps.execute();
			
			System.out.println("Änderungen an Kunde " + _customerID + " " + _name1 + " wurden in Datenbank gespeichert!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	public void deleteCustomer( int _customerID, String _name1){
		
		try{
			
			String stmt = "DELETE FROM customer WHERE customerID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			ps.setInt(1, _customerID);
			ps.execute();
			
			System.out.println("Kunde " + _customerID + " " + _name1 + " wurde aus Datenbank gelöscht!" );
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
	}
	
	/*
	 * VALIDATION
	 */
	public boolean validate(	int _customerID,
								String _name1){
		
		if( 	_customerID != 0 &&
			! 	_name1.isEmpty() ){
			return true;
		}else if(_customerID == 0){
			System.out.println("Bitte gültige Kunden-Nr. wählen!");
			return false;
		}else if(_name1.isEmpty()){
			System.out.println("Bitte gültigen Name1 wählen!");
			return false;
		}else{
			System.err.println("***ModelCustomer.java -> validate: Unbekannter Fehler");
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
	public int getCustomerID() {
		return customerID;
	}

	public String getSalutation(){
		return salutation;
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

	public String getMobile() {
		return mobile;
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

	public String getNotes() {
		return notes;
	}
	
	public int getBillingID(){
		return billingID;
	}

	public ObservableList<ModelCustomer> getObsListCustomer() {
		return obsListCustomer;
	}
	
	
	
}