package de.michaprogs.crm.customer.ui.billingadress;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.category.ModelCustomerCategory;
import de.michaprogs.crm.customer.category.SelectCustomerCategory;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ControllerBillingAdress {

	/* BILLING MAIN DATA */
	@FXML private TextField tfCustomerIDBilling;
	@FXML private ComboBox<String> cbSalutationBilling;
	@FXML private TextField tfName1Billing;
	@FXML private TextField tfName2Billing;
	@FXML private TextField tfStreetBilling;
	@FXML private ComboBox<String> cbLandBilling;
	@FXML private TextField tfZipBilling;
	@FXML private TextField tfLocationBilling;
	
	/* BILLING CONTACT DATA */
	@FXML private TextField tfPhoneBilling;
	@FXML private TextField tfMobileBilling;
	@FXML private TextField tfFaxBilling;
	@FXML private TextField tfEmailBilling;
	@FXML private TextField tfWebBilling;
	@FXML private TextField tfTaxIDBilling;
	@FXML private TextField tfUstIDBilling;
	
	/* BILLING PAYMENT DATA */
	@FXML private ComboBox<String> cbPaymentBilling;
	@FXML private TextField tfIBANBilling;
	@FXML private TextField tfBICBilling;
	@FXML private TextField tfBankBilling;
	@FXML private TextField tfPaymentSkontoBilling;
	@FXML private TextField tfSkontoBilling;
	@FXML private TextField tfPaymentNettoBilling;
	@FXML private ComboBox<String> cbCategoryBilling;
	
	/* BUTTONS */
	@FXML private HBox hboxButtons;
	@FXML private Button btnBillingGoTo;
	@FXML private Button btnBillingAdd;
	@FXML private Button btnBillingDelete;
	
	public ControllerBillingAdress(){}

	@FXML private void initialize(){
		
		new InitCombos().initComboLand(cbLandBilling);
		new InitCombos().initComboPayment(cbPaymentBilling);
		new InitCombos().initComboSalutation(cbSalutationBilling);
		
		cbCategoryBilling.setItems(new SelectCustomerCategory(new ModelCustomerCategory()).getModelCustomerCategory().getObsListCustomerCategoriesComboBox());
		
		initBtnBillingAdd();
		initBtnBillingDelete();
		
		setButtonState();
		
	}
	
	/*
	 * BUTTONS
	 */	
	private void initBtnBillingAdd(){
		
		btnBillingAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
				if(customerSearch.getController().getSelectedCustomerID() != 0){
					selectBillingAdress(customerSearch.getController().getSelectedCustomerID());
					setButtonState();
				}
				
			}
		});
		
	}
	
	private void initBtnBillingDelete(){
		
		btnBillingDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DeleteAlert delete = new DeleteAlert();
				if(delete.getDelete()){
					clearFields();
					setButtonState();
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectBillingAdress(int customerID){
		
		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();
			
		if(customer.getCustomerID() != 0){
			tfCustomerIDBilling.setText(String.valueOf(customer.getCustomerID()));
			cbSalutationBilling.getSelectionModel().select(customer.getSalutation());
			tfName1Billing.setText(customer.getName1());
			tfName2Billing.setText(customer.getName2());
			tfStreetBilling.setText(customer.getStreet());
			cbLandBilling.getSelectionModel().select(customer.getLand());
			tfZipBilling.setText(String.valueOf(customer.getZip()));
			tfLocationBilling.setText(customer.getLocation());
			
			tfPhoneBilling.setText(customer.getPhone());
			tfMobileBilling.setText(customer.getMobile());
			tfFaxBilling.setText(customer.getFax());
			tfEmailBilling.setText(customer.getEmail());
			tfWebBilling.setText(customer.getWeb());
			tfTaxIDBilling.setText(customer.getTaxID());
			tfUstIDBilling.setText(customer.getUstID());
			
			cbPaymentBilling.getSelectionModel().select(customer.getPayment());
			tfIBANBilling.setText(customer.getIBAN());
			tfBICBilling.setText(customer.getBIC());
			tfBankBilling.setText(customer.getBank());
			tfPaymentNettoBilling.setText(String.valueOf(customer.getPaymentNetto()));
			tfPaymentSkontoBilling.setText(String.valueOf(customer.getPaymentSkonto()));
			tfSkontoBilling.setText(String.valueOf(customer.getSkonto()));
			cbCategoryBilling.getSelectionModel().select(customer.getCategory());
		}else{
			clearFields();
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	public void enableFields(){
		
		tfCustomerIDBilling.setDisable(false);
		cbSalutationBilling.setDisable(false);
		tfName1Billing.setDisable(false);
		tfName2Billing.setDisable(false);
		tfStreetBilling.setDisable(false);
		cbLandBilling.setDisable(false);
		tfZipBilling.setDisable(false);
		tfLocationBilling.setDisable(false);
		
		tfPhoneBilling.setDisable(false);
		tfMobileBilling.setDisable(false);
		tfFaxBilling.setDisable(false);
		tfEmailBilling.setDisable(false);
		tfWebBilling.setDisable(false);
		tfTaxIDBilling.setDisable(false);
		tfUstIDBilling.setDisable(false);
		
		cbPaymentBilling.setDisable(false);
		tfBankBilling.setDisable(false);
		tfIBANBilling.setDisable(false);
		tfBICBilling.setDisable(false);
		tfBankBilling.setDisable(false);
		tfPaymentSkontoBilling.setDisable(false);
		tfPaymentNettoBilling.setDisable(false);
		tfSkontoBilling.setDisable(false);
		cbCategoryBilling.setDisable(false);
		
	}
	
	public void disableFields(){
		
		tfCustomerIDBilling.setDisable(true);
		cbSalutationBilling.setDisable(true);
		tfName1Billing.setDisable(true);
		tfName2Billing.setDisable(true);
		tfStreetBilling.setDisable(true);
		cbLandBilling.setDisable(true);
		tfZipBilling.setDisable(true);
		tfLocationBilling.setDisable(true);
		
		tfPhoneBilling.setDisable(true);
		tfMobileBilling.setDisable(true);
		tfFaxBilling.setDisable(true);
		tfEmailBilling.setDisable(true);
		tfWebBilling.setDisable(true);
		tfTaxIDBilling.setDisable(true);
		tfUstIDBilling.setDisable(true);
		
		cbPaymentBilling.setDisable(true);
		tfBankBilling.setDisable(true);
		tfIBANBilling.setDisable(true);
		tfBICBilling.setDisable(true);
		tfBankBilling.setDisable(true);
		tfPaymentSkontoBilling.setDisable(true);
		tfPaymentNettoBilling.setDisable(true);
		tfSkontoBilling.setDisable(true);
		cbCategoryBilling.setDisable(true);
		
	}
	
	public void clearFields(){
		
		tfCustomerIDBilling.clear();
		cbSalutationBilling.getSelectionModel().select("");
		tfName1Billing.clear();
		tfName2Billing.clear();
		tfStreetBilling.clear();
		cbLandBilling.getSelectionModel().select("");
		tfZipBilling.clear();
		tfLocationBilling.clear();
		
		tfPhoneBilling.clear();
		tfMobileBilling.clear();
		tfFaxBilling.clear();
		tfEmailBilling.clear();
		tfWebBilling.clear();
		tfTaxIDBilling.clear();
		tfUstIDBilling.clear();
		
		cbPaymentBilling.getSelectionModel().select("");
		tfBankBilling.clear();
		tfIBANBilling.clear();
		tfBICBilling.clear();
		tfBankBilling.clear();
		tfPaymentSkontoBilling.clear();
		tfPaymentNettoBilling.clear();
		tfSkontoBilling.clear();
		cbCategoryBilling.getSelectionModel().select("");
		
	}
	
	public void setButtonState(){
		
		if(tfCustomerIDBilling.getText().equals("")){
			btnBillingAdd.setDisable(false);
			btnBillingDelete.setDisable(true);
		}else{
			btnBillingAdd.setDisable(false);
			btnBillingDelete.setDisable(false);
		}
		
	}
	
	/**
	 * true = shows the buttons to delete or add the billing-adress
	 * @param b
	 */
	public void showButtons(boolean b){
		
		if(b){
			hboxButtons.setVisible(true);
		}else{
			hboxButtons.setVisible(false);
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public TextField getTfCustomerIDBilling() {
		return tfCustomerIDBilling;
	}

	public void setTfCustomerIDBilling(TextField tfCustomerIDBilling) {
		this.tfCustomerIDBilling = tfCustomerIDBilling;
	}

	public ComboBox<String> getCbSalutationBilling() {
		return cbSalutationBilling;
	}

	public void setCbSalutationBilling(ComboBox<String> cbSalutationBilling) {
		this.cbSalutationBilling = cbSalutationBilling;
	}

	public TextField getTfName1Billing() {
		return tfName1Billing;
	}

	public void setTfName1Billing(TextField tfName1Billing) {
		this.tfName1Billing = tfName1Billing;
	}

	public TextField getTfName2Billing() {
		return tfName2Billing;
	}

	public void setTfName2Billing(TextField tfName2Billing) {
		this.tfName2Billing = tfName2Billing;
	}

	public TextField getTfStreetBilling() {
		return tfStreetBilling;
	}

	public void setTfStreetBilling(TextField tfStreetBilling) {
		this.tfStreetBilling = tfStreetBilling;
	}

	public ComboBox<String> getCbLandBilling() {
		return cbLandBilling;
	}

	public void setCbLandBilling(ComboBox<String> cbLandBilling) {
		this.cbLandBilling = cbLandBilling;
	}

	public TextField getTfZipBilling() {
		return tfZipBilling;
	}

	public void setTfZipBilling(TextField tfZipBilling) {
		this.tfZipBilling = tfZipBilling;
	}

	public TextField getTfLocationBilling() {
		return tfLocationBilling;
	}

	public void setTfLocationBilling(TextField tfLocationBilling) {
		this.tfLocationBilling = tfLocationBilling;
	}

	public TextField getTfPhoneBilling() {
		return tfPhoneBilling;
	}

	public void setTfPhoneBilling(TextField tfPhoneBilling) {
		this.tfPhoneBilling = tfPhoneBilling;
	}

	public TextField getTfMobileBilling() {
		return tfMobileBilling;
	}

	public void setTfMobileBilling(TextField tfMobileBilling) {
		this.tfMobileBilling = tfMobileBilling;
	}

	public TextField getTfFaxBilling() {
		return tfFaxBilling;
	}

	public void setTfFaxBilling(TextField tfFaxBilling) {
		this.tfFaxBilling = tfFaxBilling;
	}

	public TextField getTfEmailBilling() {
		return tfEmailBilling;
	}

	public void setTfEmailBilling(TextField tfEmailBilling) {
		this.tfEmailBilling = tfEmailBilling;
	}

	public TextField getTfWebBilling() {
		return tfWebBilling;
	}

	public void setTfWebBilling(TextField tfWebBilling) {
		this.tfWebBilling = tfWebBilling;
	}

	public TextField getTfTaxIDBilling() {
		return tfTaxIDBilling;
	}

	public void setTfTaxIDBilling(TextField tfTaxIDBilling) {
		this.tfTaxIDBilling = tfTaxIDBilling;
	}

	public TextField getTfUstIDBilling() {
		return tfUstIDBilling;
	}

	public void setTfUstIDBilling(TextField tfUstIDBilling) {
		this.tfUstIDBilling = tfUstIDBilling;
	}

	public ComboBox<String> getCbPaymentBilling() {
		return cbPaymentBilling;
	}

	public void setCbPaymentBilling(ComboBox<String> cbPaymentBilling) {
		this.cbPaymentBilling = cbPaymentBilling;
	}

	public TextField getTfIBANBilling() {
		return tfIBANBilling;
	}

	public void setTfIBANBilling(TextField tfIBANBilling) {
		this.tfIBANBilling = tfIBANBilling;
	}

	public TextField getTfBICBilling() {
		return tfBICBilling;
	}

	public void setTfBICBilling(TextField tfBICBilling) {
		this.tfBICBilling = tfBICBilling;
	}

	public TextField getTfBankBilling() {
		return tfBankBilling;
	}

	public void setTfBankBilling(TextField tfBankBilling) {
		this.tfBankBilling = tfBankBilling;
	}

	public TextField getTfPaymentSkontoBilling() {
		return tfPaymentSkontoBilling;
	}

	public void setTfPaymentSkontoBilling(TextField tfPaymentSkontoBilling) {
		this.tfPaymentSkontoBilling = tfPaymentSkontoBilling;
	}

	public TextField getTfSkontoBilling() {
		return tfSkontoBilling;
	}

	public void setTfSkontoBilling(TextField tfSkontoBilling) {
		this.tfSkontoBilling = tfSkontoBilling;
	}

	public TextField getTfPaymentNettoBilling() {
		return tfPaymentNettoBilling;
	}

	public void setTfPaymentNettoBilling(TextField tfPaymentNettoBilling) {
		this.tfPaymentNettoBilling = tfPaymentNettoBilling;
	}

	public ComboBox<String> getCbCategoryBilling() {
		return cbCategoryBilling;
	}

	public void setCbCategoryBilling(ComboBox<String> cbCategoryBilling) {
		this.cbCategoryBilling = cbCategoryBilling;
	}

	
	public Button getBtnBillingGoTo() {
		return btnBillingGoTo;
	}

	
	public void setBtnBillingGoTo(Button btnBillingGoTo) {
		this.btnBillingGoTo = btnBillingGoTo;
	}


	public Button getBtnBillingAdd() {
		return btnBillingAdd;
	}

	
	public void setBtnBillingAdd(Button btnBillingAdd) {
		this.btnBillingAdd = btnBillingAdd;
	}

	
	public Button getBtnBillingDelete() {
		return btnBillingDelete;
	}

	
	public void setBtnBillingDelete(Button btnBillingDelete) {
		this.btnBillingDelete = btnBillingDelete;
	}
	
}
