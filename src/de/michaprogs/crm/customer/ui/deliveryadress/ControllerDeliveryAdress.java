package de.michaprogs.crm.customer.ui.deliveryadress;

import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.category.ModelCustomerCategory;
import de.michaprogs.crm.customer.category.SelectCustomerCategory;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ControllerDeliveryAdress {

	/* MAIN DATA */
	@FXML private TextField tfCustomerID;
	@FXML private ComboBox<String> cbSalutation;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextField tfZip;
	@FXML private TextField tfLocation;
	
	/* CONTACT DATA */
	@FXML private TextField tfPhone;
	@FXML private TextField tfMobile;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfWeb;
	@FXML private TextField tfTaxID;
	@FXML private TextField tfUstID;
	
	/* PAYMENT DATA */
	@FXML private ComboBox<String> cbPayment;
	@FXML private TextField tfIBAN;
	@FXML private TextField tfBIC;
	@FXML private TextField tfBank;
	@FXML private TextField tfPaymentSkonto;
	@FXML private TextField tfPaymentNetto;
	@FXML private TextField tfSkonto;
	@FXML private ComboBox<String> cbCategory;
	
	/* BILLING */
	private int billingID;
	
	/* BUTTONS */
	@FXML private Button btnCustomerSearch;
	@FXML private HBox hboxBtnSearchSmall;
	
	private int selectedCustomerID;
	
	public ControllerDeliveryAdress(){}
	
	@FXML private void initialize(){
		
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboPayment(cbPayment);
		new InitCombos().initComboSalutation(cbSalutation);
		
		cbCategory.setItems(new SelectCustomerCategory(new ModelCustomerCategory()).getModelCustomerCategory().getObsListCustomerCategoriesComboBox());
		
		initBtnCustomerSearch();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnCustomerSearch(){
		
		btnCustomerSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				searchDeliveryAdress();
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void searchDeliveryAdress(){
		
		LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
		if(customerSearch.getController().getSelectedCustomerID() != 0){
			selectDeliveryAdress(customerSearch.getController().getSelectedCustomerID());	
		}
		
	}
	
	public void selectDeliveryAdress(int customerID){
		
		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();
		
		tfCustomerID.setText(String.valueOf(customer.getCustomerID()));
		cbSalutation.getSelectionModel().select(customer.getSalutation());
		tfName1.setText(customer.getName1());
		tfName2.setText(customer.getName2());
		tfStreet.setText(customer.getStreet());
		cbLand.getSelectionModel().select(customer.getLand());
		tfZip.setText(String.valueOf(customer.getZip()));
		tfLocation.setText(customer.getLocation());
		
		tfPhone.setText(customer.getPhone());
		tfMobile.setText(customer.getMobile());
		tfFax.setText(customer.getFax());
		tfEmail.setText(customer.getEmail());
		tfWeb.setText(customer.getWeb());
		tfTaxID.setText(customer.getTaxID());
		tfUstID.setText(customer.getUstID());
		
		cbPayment.getSelectionModel().select(customer.getPayment());
		tfBank.setText(customer.getBank());
		tfIBAN.setText(customer.getIBAN());
		tfBIC.setText(customer.getBIC());
		tfBank.setText(customer.getBank());
		tfPaymentSkonto.setText(String.valueOf(customer.getPaymentSkonto()));
		tfPaymentNetto.setText(String.valueOf(customer.getPaymentNetto()));
		tfSkonto.setText(String.valueOf(customer.getSkonto()));
		cbCategory.getSelectionModel().select(customer.getCategory());
		
		billingID = customer.getBillingID();
		
	}
		
	/*
	 * UI METHODS
	 */
	public void enableFields(){
		
		tfCustomerID.setDisable(false);
		cbSalutation.setDisable(false);
		tfName1.setDisable(false);
		tfName2.setDisable(false);
		tfStreet.setDisable(false);
		cbLand.setDisable(false);
		tfZip.setDisable(false);
		tfLocation.setDisable(false);
		
		tfPhone.setDisable(false);
		tfMobile.setDisable(false);
		tfFax.setDisable(false);
		tfEmail.setDisable(false);
		tfWeb.setDisable(false);
		tfTaxID.setDisable(false);
		tfUstID.setDisable(false);
		
		cbPayment.setDisable(false);
		tfBank.setDisable(false);
		tfIBAN.setDisable(false);
		tfBIC.setDisable(false);
		tfBank.setDisable(false);
		tfPaymentSkonto.setDisable(false);
		tfPaymentNetto.setDisable(false);
		tfSkonto.setDisable(false);
		cbCategory.setDisable(false);
		
	}
	
	public void disableFields(){
		
		tfCustomerID.setDisable(true);
		cbSalutation.setDisable(true);
		tfName1.setDisable(true);
		tfName2.setDisable(true);
		tfStreet.setDisable(true);
		cbLand.setDisable(true);
		tfZip.setDisable(true);
		tfLocation.setDisable(true);
		
		tfPhone.setDisable(true);
		tfMobile.setDisable(true);
		tfFax.setDisable(true);
		tfEmail.setDisable(true);
		tfWeb.setDisable(true);
		tfTaxID.setDisable(true);
		tfUstID.setDisable(true);
		
		cbPayment.setDisable(true);
		tfBank.setDisable(true);
		tfIBAN.setDisable(true);
		tfBIC.setDisable(true);
		tfBank.setDisable(true);
		tfPaymentSkonto.setDisable(true);
		tfPaymentNetto.setDisable(true);
		tfSkonto.setDisable(true);
		cbCategory.setDisable(true);
		
	}
	
	/**
	 * shows a little search-icon beside the customerID textfield
	 * @param boolean b - true = show
	 */
	public void showSearchButtonSmall(boolean b){
		
		if(b){
			hboxBtnSearchSmall.getChildren().add(btnCustomerSearch);
		}else{
			hboxBtnSearchSmall.getChildren().remove(btnCustomerSearch);
		}
		
	}
	
	public void clearFields(){
		
		tfCustomerID.clear();
		cbSalutation.getSelectionModel().select("");
		tfName1.clear();
		tfName2.clear();
		tfStreet.clear();
		cbLand.getSelectionModel().select("");
		tfZip.clear();
		tfLocation.clear();
		
		tfPhone.clear();
		tfMobile.clear();
		tfFax.clear();
		tfEmail.clear();
		tfWeb.clear();
		tfTaxID.clear();
		tfUstID.clear();
		
		cbPayment.getSelectionModel().select("");
		tfBank.clear();
		tfIBAN.clear();
		tfBIC.clear();
		tfBank.clear();
		tfPaymentSkonto.clear();
		tfPaymentNetto.clear();
		tfSkonto.clear();
		cbCategory.getSelectionModel().select("");
		
	}

	/*
	 * GETTER & SETTER
	 */
	public TextField getTfCustomerID() {
		return tfCustomerID;
	}

	public void setTfCustomerID(TextField tfCustomerID) {
		this.tfCustomerID = tfCustomerID;
	}

	public ComboBox<String> getCbSalutation() {
		return cbSalutation;
	}

	public void setCbSalutation(ComboBox<String> cbSalutation) {
		this.cbSalutation = cbSalutation;
	}

	public TextField getTfName1() {
		return tfName1;
	}

	public void setTfName1(TextField tfName1) {
		this.tfName1 = tfName1;
	}

	public TextField getTfName2() {
		return tfName2;
	}

	public void setTfName2(TextField tfName2) {
		this.tfName2 = tfName2;
	}

	public TextField getTfStreet() {
		return tfStreet;
	}

	public void setTfStreet(TextField tfStreet) {
		this.tfStreet = tfStreet;
	}

	public ComboBox<String> getCbLand() {
		return cbLand;
	}

	public void setCbLand(ComboBox<String> cbLand) {
		this.cbLand = cbLand;
	}

	public TextField getTfZip() {
		return tfZip;
	}

	public void setTfZip(TextField tfZip) {
		this.tfZip = tfZip;
	}

	public TextField getTfLocation() {
		return tfLocation;
	}

	public void setTfLocation(TextField tfLocation) {
		this.tfLocation = tfLocation;
	}

	public TextField getTfPhone() {
		return tfPhone;
	}

	public void setTfPhone(TextField tfPhone) {
		this.tfPhone = tfPhone;
	}

	public TextField getTfMobile() {
		return tfMobile;
	}

	public void setTfMobile(TextField tfMobile) {
		this.tfMobile = tfMobile;
	}

	public TextField getTfFax() {
		return tfFax;
	}

	public void setTfFax(TextField tfFax) {
		this.tfFax = tfFax;
	}

	public TextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(TextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public TextField getTfWeb() {
		return tfWeb;
	}

	public void setTfWeb(TextField tfWeb) {
		this.tfWeb = tfWeb;
	}

	public TextField getTfTaxID() {
		return tfTaxID;
	}

	public void setTfTaxID(TextField tfTaxID) {
		this.tfTaxID = tfTaxID;
	}

	public TextField getTfUstID() {
		return tfUstID;
	}

	public void setTfUstID(TextField tfUstID) {
		this.tfUstID = tfUstID;
	}

	public ComboBox<String> getCbPayment() {
		return cbPayment;
	}

	public void setCbPayment(ComboBox<String> cbPayment) {
		this.cbPayment = cbPayment;
	}

	public TextField getTfIBAN() {
		return tfIBAN;
	}

	public void setTfIBAN(TextField tfIBAN) {
		this.tfIBAN = tfIBAN;
	}

	public TextField getTfBIC() {
		return tfBIC;
	}

	public void setTfBIC(TextField tfBIC) {
		this.tfBIC = tfBIC;
	}

	public TextField getTfBank() {
		return tfBank;
	}

	public void setTfBank(TextField tfBank) {
		this.tfBank = tfBank;
	}

	public TextField getTfPaymentSkonto() {
		return tfPaymentSkonto;
	}

	public void setTfPaymentSkonto(TextField tfPaymentSkonto) {
		this.tfPaymentSkonto = tfPaymentSkonto;
	}

	public TextField getTfPaymentNetto() {
		return tfPaymentNetto;
	}

	public void setTfPaymentNetto(TextField tfPaymentNetto) {
		this.tfPaymentNetto = tfPaymentNetto;
	}

	public TextField getTfSkonto() {
		return tfSkonto;
	}

	public void setTfSkonto(TextField tfSkonto) {
		this.tfSkonto = tfSkonto;
	}

	public ComboBox<String> getCbCategory() {
		return cbCategory;
	}

	public void setCbCategory(ComboBox<String> cbCategory) {
		this.cbCategory = cbCategory;
	}

	public Button getBtnCustomerSearch() {
		return btnCustomerSearch;
	}

	public void setBtnCustomerSearch(Button btnCustomerSearch) {
		this.btnCustomerSearch = btnCustomerSearch;
	}

	public int getSelectedCustomerID() {
		return selectedCustomerID;
	}

	public void setSelectedCustomerID(int selectedCustomerID) {
		this.selectedCustomerID = selectedCustomerID;
	}

	public int getBillingID() {
		return billingID;
	}

	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}
	
	
	
}
