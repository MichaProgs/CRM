package de.michaprogs.crm.supplier;

import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.supplier.article.ModelSupplierArticle;
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
	private String notes;
	
	private ObservableList<ModelContact> obsListContacts = FXCollections.observableArrayList();
	private ObservableList<ModelSupplierArticle> obsListSupplierArticle = FXCollections.observableArrayList();
	
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
	
	/**
	 * Constructor for Database (Insert Supplier)
	 * Constructor for Database (Update Supplier)
	 * @param _supplierID
	 * @param _name1
	 * @param _name2
	 * @param _street
	 * @param _land
	 * @param _zip
	 * @param _location
	 * @param _phone
	 * @param _fax
	 * @param _email
	 * @param _web
	 * @param _contact
	 * @param _ustID
	 * @param _payment
	 * @param _IBAN
	 * @param _BIC
	 * @param _bank
	 * @param _paymentSkonto
	 * @param _paymentNetto
	 * @param _skonto
	 * @param _lastChange
	 * @param _notes
	 */
	public ModelSupplier(	int _supplierID,
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
							String _lastChange,
							String _notes){
		
		this.supplierID = _supplierID;
		this.name1 = _name1;
		this.name2 = _name2;
		this.street = _street;
		this.land = _land;
		this.zip = _zip;
		this.location = _location;
		this.phone = _phone;
		this.fax = _fax;
		this.email = _email;
		this.web = _web;
		this.contact = _contact;
		this.ustID = _ustID;
		this.payment = _payment;
		this.IBAN = _IBAN;
		this.BIC = _BIC;
		this.bank = _bank;
		this.paymentSkonto = _paymentSkonto;
		this.paymentNetto = _paymentNetto;
		this.skonto = _skonto;
		this.lastChange = _lastChange;
		this.notes = _notes;
		
	}
	
	/**
	 * Constructor for Database (Select Supplier)
	 * Constructor for Database (Delete Supplier)
	 * @param _supplierID
	 */
	public ModelSupplier(	int _supplierID){
		this.supplierID = _supplierID;
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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLand() {
		//Empty ComboBox = null
		if(land == null){
			land = "";
		}
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUstID() {
		return ustID;
	}

	public void setUstID(String ustID) {
		this.ustID = ustID;
	}

	public String getPayment() {
		//Empty ComboBox = null
		if(payment == null){
			payment = "";
		}
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getBIC() {
		return BIC;
	}

	public void setBIC(String bIC) {
		BIC = bIC;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getPaymentSkonto() {
		return paymentSkonto;
	}

	public void setPaymentSkonto(int paymentSkonto) {
		this.paymentSkonto = paymentSkonto;
	}

	public int getPaymentNetto() {
		return paymentNetto;
	}

	public void setPaymentNetto(int paymentNetto) {
		this.paymentNetto = paymentNetto;
	}

	public int getSkonto() {
		return skonto;
	}

	public void setSkonto(int skonto) {
		this.skonto = skonto;
	}

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ObservableList<ModelContact> getObsListContacts() {
		return obsListContacts;
	}

	public void setObsListContacts(ObservableList<ModelContact> obsListContacts) {
		this.obsListContacts = obsListContacts;
	}

	
	public ObservableList<ModelSupplierArticle> getObsListSupplierArticle() {
		return obsListSupplierArticle;
	}
	

	public void setObsListSupplierArticle(ObservableList<ModelSupplierArticle> obsListSupplierArticle) {
		this.obsListSupplierArticle = obsListSupplierArticle;
	}
	
	
	
}
	


