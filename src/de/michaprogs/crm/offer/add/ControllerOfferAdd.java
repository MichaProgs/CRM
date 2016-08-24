package de.michaprogs.crm.offer.add;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.clerk.data.LoadClerkData;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.offer.InsertOffer;
import de.michaprogs.crm.offer.ModelOffer;
import de.michaprogs.crm.offer.ValidateOfferSave;
import de.michaprogs.crm.position.add.LoadAddPosition;
import de.michaprogs.crm.position.edit.LoadEditPosition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerOfferAdd {

	/* OFFER */
	@FXML private TextField tfOfferID;
	@FXML private DatePicker tfOfferDate;
	@FXML private TextField tfRequest;
	@FXML private DatePicker tfRequestDate;
	@FXML private TextArea taNotes;
	
	/* CLERK */
	@FXML private TextField tfClerkID;
	@FXML private TextField tfClerk;
	@FXML private TextField tfClerkPhone;
	@FXML private TextField tfClerkFax;
	@FXML private TextField tfClerkEmail;
	
	/* CUSTOMER DELIVERYADRESS */
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
	@FXML private TextField tfPaymentNetto;
	@FXML private TextField tfSkonto;
	
	/* CUSTOMER BILLINGADRESS */
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
	
	/* ARTICLETABLE */
	@FXML private TableView<ModelArticle> tvArticle;
	@FXML private TableColumn<Integer, ModelArticle> tcArticleID;
	@FXML private TableColumn<String, ModelArticle> tcDescription1;
	@FXML private TableColumn<String, ModelArticle> tcDescription2;
	@FXML private TableColumn<String, ModelArticle> tcBarrelsize;
	@FXML private TableColumn<String, ModelArticle> tcBolting;
	@FXML private TableColumn<Double, ModelArticle> tcAmount;
	@FXML private TableColumn<String, ModelArticle> tcAmountUnit;
	@FXML private TableColumn<BigDecimal, ModelArticle> tcVk;
	@FXML private TableColumn<Integer, ModelArticle> tcPriceUnit;
	@FXML private TableColumn<BigDecimal, ModelArticle> tcTotal;
	@FXML private TableColumn<Integer, ModelArticle> tcTax;	
	
	@FXML private Button btnCustomerSearch;
	@FXML private Button btnClerkSearch;
	@FXML private Button btnArticleAdd;
	@FXML private Button btnArticleEdit;
	@FXML private Button btnArticleDelete;
	
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnDelete;
	
	private Stage stage;
	private int createdOfferID;
	private int createdOfferCustomerID;
	
	public ControllerOfferAdd(){}
	
	@FXML private void initialize(){
		
		new InitCombos().initComboSalutation(cbSalutation);
		new InitCombos().initComboSalutation(cbSalutationBilling);
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboLand(cbLandBilling);
		
		tfOfferDate.setValue(LocalDate.now());
		tfRequestDate.setValue(LocalDate.now());
		
		/* BUTTONS */
		initBtnCustomerSearch();
		initBtnClerkSearch();
		initBtnArticleAdd();
		initBtnArticleEdit();
		initBtnArticleDelete();
		
		initBtnSave();
		initBtnAbort();
		
		/* TABLES */
		initTableArticle();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnCustomerSearch(){
		
		btnCustomerSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
				if(customerSearch.getController().getSelectedCustomerID() != 0){
					selectCustomer(customerSearch.getController().getSelectedCustomerID());					
				}
				
			}
		});
		
	}
		
	private void initBtnClerkSearch(){
		
		btnClerkSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadClerkData clerkData = new LoadClerkData(true);
				if(clerkData.getController().getSelectedClerkID() != 0){
					
					ModelClerk clerk = new SelectClerk(new ModelClerk(clerkData.getController().getSelectedClerkID()), Selection.SELECT_SPECIFIC).getModelClerk();
					tfClerkID.setText(String.valueOf(clerk.getClerkID()));
					tfClerk.setText(clerk.getName());
					tfClerkPhone.setText(clerk.getPhone());
					tfClerkFax.setText(clerk.getFax());
					tfClerkEmail.setText(clerk.getEmail());
					
				}
				
			}
		});
		
	}
	
	private void initBtnArticleAdd(){
		
		btnArticleAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addArticle();				
			}
		});
		
	}	
	
	private void initBtnArticleEdit(){
		
		btnArticleEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				editArticle();
			}
		});
		
	}
	
	private void initBtnArticleDelete(){
		
		btnArticleDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteArticle();
			}
		});
		
	}
	
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new ValidateOfferSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
											String.valueOf(tfOfferDate.getValue()),
											String.valueOf(tfRequestDate.getValue()),
											tvArticle.getItems()).isValid()){
					
					new InsertOffer(new ModelOffer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
						String.valueOf(tfOfferDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfClerkID.getText()),
						tvArticle.getItems()
					));
					
					createdOfferID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText());
					createdOfferCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText());
					
					if(stage != null){
						stage.close();
					}else{
						resetFields();
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
						resetFields();
					}
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableArticle(){
		
		this.tcArticleID.setCellValueFactory(new PropertyValueFactory<>("articleID"));
		this.tcDescription1.setCellValueFactory(new PropertyValueFactory<>("description1"));
		this.tcDescription2.setCellValueFactory(new PropertyValueFactory<>("description2"));
		this.tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		this.tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		this.tcAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		this.tcVk.setCellValueFactory(new PropertyValueFactory<>("vk"));
		this.tcPriceUnit.setCellValueFactory(new PropertyValueFactory<>("priceUnit"));
		this.tcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		this.tcTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		
		tvArticle.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					editArticle();
				}
				
			}
		});
		
		tvArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getButton().equals(MouseButton.SECONDARY)){
					tvArticle.setContextMenu(new ContextMenuTableArticle());
				}
				
				if(event.getClickCount() == 2){
					editArticle();
				}
				
			}
		});
		
	}
	
	private void editArticle(){
		
		if(tvArticle.getSelectionModel().getSelectedItems().size() > 0){
			
			LoadEditPosition editPos = new LoadEditPosition(true, tvArticle.getItems(), tvArticle.getSelectionModel().getSelectedIndex());
			tvArticle.setItems(editPos.getController().getObsListArticle());
			if(tvArticle.getItems().size() > 0){
				tvArticle.getSelectionModel().selectFirst();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void addArticle(){
		
		LoadAddPosition addPos = new LoadAddPosition(true, tvArticle.getItems());
		tvArticle.setItems(addPos.getController().getObsListArticle());
		if(tvArticle.getItems().size() > 0){
			tvArticle.getSelectionModel().selectFirst();
		}
		
	}
	
	private void deleteArticle(){
		
		if(tvArticle.getSelectionModel().getSelectedItems().size() == 1){
			DeleteAlert delete = new DeleteAlert();
			if(delete.getDelete()){
				tvArticle.getItems().remove(tvArticle.getSelectionModel().getSelectedIndex());
			}
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
				
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectCustomer(int _customerID){
	
		ModelCustomer customer = new SelectCustomer(new ModelCustomer(_customerID)).getModelCustomer();
		
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
		tfContact.setText(customer.getContact());
		tfUstID.setText(customer.getUstID());
		
		cbPayment.getSelectionModel().select(customer.getPayment());
		tfBank.setText(customer.getBank());
		tfIBAN.setText(customer.getIBAN());
		tfBIC.setText(customer.getBIC());
		tfBank.setText(customer.getBank());
		tfPaymentSkonto.setText(String.valueOf(customer.getPaymentSkonto()));
		tfPaymentNetto.setText(String.valueOf(customer.getPaymentNetto()));
		tfSkonto.setText(String.valueOf(customer.getSkonto()));
		
		//ALWAYS LAST - OTHERWISE THE DATA IN THE MODEL WOULD BE OVERWRITTEN
		if(customer.getBillingID() != 0){
			
			ModelCustomer customerBilling = new SelectCustomer(new ModelCustomer(customer.getBillingID())).getModelCustomer();
			
			tfCustomerIDBilling.setText(String.valueOf(customerBilling.getCustomerID()));
			cbSalutationBilling.getSelectionModel().select(customerBilling.getSalutation());
			tfName1Billing.setText(customerBilling.getName1());
			tfName2Billing.setText(customerBilling.getName2());
			tfStreetBilling.setText(customerBilling.getStreet());
			cbLandBilling.getSelectionModel().select(customerBilling.getLand());
			tfZipBilling.setText(String.valueOf(customerBilling.getZip()));
			tfLocationBilling.setText(customerBilling.getLocation());
			
			tfPhoneBilling.setText(customerBilling.getPhone());
			tfMobileBilling.setText(customerBilling.getMobile());
			tfFaxBilling.setText(customerBilling.getFax());
			tfEmailBilling.setText(customerBilling.getEmail());
			tfWebBilling.setText(customerBilling.getWeb());
			tfContactBilling.setText(customerBilling.getContact());
			tfUstIDBilling.setText(customerBilling.getUstID());
			
			cbPaymentBilling.getSelectionModel().select(customerBilling.getPayment());
			tfBankBilling.setText(customerBilling.getBank());
			tfIBANBilling.setText(customerBilling.getIBAN());
			tfBICBilling.setText(customerBilling.getBIC());
			tfBankBilling.setText(customerBilling.getBank());
			tfPaymentSkontoBilling.setText(String.valueOf(customerBilling.getPaymentSkonto()));
			tfPaymentNettoBilling.setText(String.valueOf(customerBilling.getPaymentNetto()));
			tfSkontoBilling.setText(String.valueOf(customerBilling.getSkonto()));
			
		}else{				
			resetFieldsBilling();				
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		/* OFFER */
		tfOfferID.clear();;
		tfOfferDate.setValue(LocalDate.now());;
		tfRequest.clear();
		tfRequestDate.setValue(LocalDate.now());;
		taNotes.clear();
		
		/* CUSTOMER */
		tfCustomerID.clear();
		cbSalutation.getSelectionModel().selectFirst();
		tfName1.clear();
		tfName2.clear();
		tfStreet.clear();
		cbLand.getSelectionModel().selectFirst();
		tfZip.clear();
		tfLocation.clear();
		
		tfPhone.clear();
		tfMobile.clear();
		tfFax.clear();
		tfEmail.clear();
		tfWeb.clear();
		tfContact.clear();
		tfUstID.clear();
		
		cbPayment.getSelectionModel().selectFirst();
		tfBank.clear();
		tfIBAN.clear();
		tfBIC.clear();
		tfBank.clear();
		tfPaymentSkonto.clear();
		tfPaymentNetto.clear();
		tfSkonto.clear();
		
		resetFieldsBilling();
		
		/* ARTICLE */
		tvArticle.getItems().clear();
		
	}

	private void resetFieldsBilling(){
		
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
			
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getCreatedOfferID(){
		return createdOfferID;
	}
	
	public int getCreatedOfferCustomerID(){
		return createdOfferCustomerID;
	}
	
	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableArticle extends ContextMenu{
		
		private MenuItem miAdd = new MenuItem("Hinzufügen..");
		private MenuItem miEdit = new MenuItem("Bearbeiten..");
		private MenuItem miDelete = new MenuItem("Löschen");
		
		public ContextMenuTableArticle(){
			
			//initialize
			initMiAdd();
			initMiEdit();
			initMiDelete();
			
			this.getItems().addAll(miAdd, miEdit, miDelete);			
			
		}
		
		private void initMiAdd(){
			
			miAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					addArticle();
				}
			});
			
		}
		
		private void initMiEdit(){
			
			miEdit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					editArticle();
				}
			});
			
		}

		private void initMiDelete(){
	
			miDelete.setOnAction(new EventHandler<ActionEvent>() {
		
				@Override
				public void handle(ActionEvent event) {
					deleteArticle();
				}
			});
	
		}
		
	}
	
}
