package de.michaprogs.crm.customer.add;

import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.contact.data.ControllerContactData;
import de.michaprogs.crm.customer.InsertCustomer;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.ValidateCustomerSave;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCustomerAdd {

	/* MAIN DATA */
	@FXML private TextField tfCustomerID;
	@FXML private ComboBox<String> cbSalutation;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextField tfZip;
	@FXML private TextField tfLocation;
	
	@FXML private TextField tfPhone;
	@FXML private TextField tfMobile;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfWeb;
	@FXML private TextField tfContact;
	@FXML private TextField tfUstID;
	
	@FXML private ComboBox<String> cbPayment;
	@FXML private TextField tfIBAN;
	@FXML private TextField tfBIC;
	@FXML private TextField tfBank;
	@FXML private TextField tfPaymentSkonto;
	@FXML private TextField tfSkonto;
	@FXML private TextField tfPaymentNetto;
	
	/* BILLING */
	@FXML private TextField tfCustomerIDBilling;
	@FXML private ComboBox<String> cbSalutationBilling;
	@FXML private TextField tfName1Billing;
	@FXML private TextField tfName2Billing;
	@FXML private TextField tfStreetBilling;
	@FXML private ComboBox<String> cbLandBilling;
	@FXML private TextField tfZipBilling;
	@FXML private TextField tfLocationBilling;
	
	@FXML private TextField tfPhoneBilling;
	@FXML private TextField tfMobileBilling;
	@FXML private TextField tfFaxBilling;
	@FXML private TextField tfEmailBilling;
	@FXML private TextField tfWebBilling;
	@FXML private TextField tfContactBilling;
	@FXML private TextField tfUstIDBilling;
	
	@FXML private ComboBox<String> cbPaymentBilling;
	@FXML private TextField tfIBANBilling;
	@FXML private TextField tfBICBilling;
	@FXML private TextField tfBankBilling;
	@FXML private TextField tfPaymentSkontoBilling;
	@FXML private TextField tfSkontoBilling;
	@FXML private TextField tfPaymentNettoBilling;
	
	/* CONTACTS - NESTED CONTROLLER */
	@FXML private ControllerContactData contactDataController; //fx:id + 'Controller'
	
	/* NOTES */
	@FXML private TextArea taNotes;
	
	/* LAST CHANGE */
	@FXML private Label lblLastChange;
	
	/* BUTTONS */
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBillingAdd;
	@FXML private Button btnBillingDelete;
	
	private Stage stage;
	private int createdCustomerID = 0;
	
	public ControllerCustomerAdd(){}
	
	@FXML private void initialize(){
		
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboPayment(cbPayment);
		new InitCombos().initComboSalutation(cbSalutation);
		
		new InitCombos().initComboLand(cbLandBilling);
		new InitCombos().initComboPayment(cbPaymentBilling);
		new InitCombos().initComboSalutation(cbSalutationBilling);
		
		initBtnSave();
		initBtnAbort();
		
		initBtnBillingAdd();
		initBtnBillingDelete();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
	
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new ValidateCustomerSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
												tfName1.getText()).isValid()){
				
					InsertCustomer insert = new InsertCustomer(
						new ModelCustomer(
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
							cbSalutation.getSelectionModel().getSelectedItem(),
							tfName1.getText(), 
							tfName2.getText(), 
							tfStreet.getText(), 
							cbLand.getSelectionModel().getSelectedItem(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZip.getText()), 
							tfLocation.getText(), 
							
							tfPhone.getText(), 
							tfMobile.getText(),
							tfFax.getText(), 
							tfEmail.getText(), 
							tfWeb.getText(), 
							tfContact.getText(), 
							tfUstID.getText(), 
							
							cbPayment.getSelectionModel().getSelectedItem(), 
							tfIBAN.getText(), 
							tfBIC.getText(), 
							tfBank.getText(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentSkonto.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentNetto.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSkonto.getText()),
							
							String.valueOf(LocalDate.now()),
							taNotes.getText(),
							
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerIDBilling.getText())),
						contactDataController.getObsListContact()
					);					
					
					if(insert.wasSuccessful()){
						
						createdCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText());						
						if(stage != null){
							stage.close();
						}else{
							//TODO RESET FIELDS
						}
						
					}
					
				}
				
			}
			
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						//TODO RESET FIELDS
					}
				}
				
			}
		});
		
	}
	
	private void initBtnBillingAdd(){
		
		btnBillingAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
				if(customerSearch.getController().getSelectedCustomerID() != 0){
					
					ModelCustomer customer = new SelectCustomer(new ModelCustomer()).getModelCustomer();
					
					tfCustomerIDBilling.setText(String.valueOf(customer.getCustomerID()));
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
					tfContactBilling.setText(customer.getContact());
					tfUstIDBilling.setText(customer.getUstID());
					
					cbPaymentBilling.getSelectionModel().select(customer.getPayment());
					tfIBANBilling.setText(customer.getIBAN());
					tfBICBilling.setText(customer.getBIC());
					tfBankBilling.setText(customer.getBank());
					tfPaymentNettoBilling.setText(String.valueOf(customer.getPaymentNetto()));
					tfPaymentSkontoBilling.setText(String.valueOf(customer.getPaymentSkonto()));
					tfSkontoBilling.setText(String.valueOf(customer.getSkonto()));
					
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
					
					tfCustomerIDBilling.clear();
					cbSalutationBilling.getSelectionModel().selectFirst();
					tfName1Billing.clear();
					tfName2Billing.clear();
					tfStreetBilling.clear();
					cbLandBilling.getSelectionModel().selectFirst();
					tfZipBilling.clear();
					tfLocationBilling.clear();
					
					tfPhoneBilling.clear();
					tfMobileBilling.clear();
					tfFaxBilling.clear();
					tfEmailBilling.clear();
					tfWebBilling.clear();
					tfContactBilling.clear();
					tfUstIDBilling.clear();
					
					cbPaymentBilling.getSelectionModel().selectFirst();
					tfBankBilling.clear();
					tfIBANBilling.clear();
					tfBICBilling.clear();
					tfBankBilling.clear();
					tfPaymentSkontoBilling.clear();
					tfPaymentNettoBilling.clear();
					tfSkontoBilling.clear();
					
				}
				
			}
		});
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getCreatedCustomerID(){
		return createdCustomerID;
	}
	
}
