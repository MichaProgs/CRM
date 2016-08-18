package de.michaprogs.crm.offer.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.Validate.ValidateOnlyInteger;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.article.add.LoadArticleAdd;
import de.michaprogs.crm.article.search.LoadArticleSearch;
import de.michaprogs.crm.components.TextFieldDouble;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.offer.ModelOffer;
import de.michaprogs.crm.offer.add.LoadOfferAdd;
import de.michaprogs.crm.offer.search.LoadOfferSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class ControllerOfferData {

	@FXML private Label lblSubHeadline;
	@FXML private HBox hboxBtnTopbar;
	
	/* OFFER */
	@FXML private TextField tfOfferID;
	@FXML private DatePicker tfOfferDate;
	@FXML private TextField tfRequest;
	@FXML private DatePicker tfRequestDate;
	@FXML private TextArea taNotes;
	
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
	
	/* ARTICLEDATA */
	@FXML private TextField tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	@FXML private TextField tfBarrelsize;
	@FXML private TextField tfBolting;
	
	@FXML private TextFieldDouble tfAmount;
	@FXML private ComboBox<String> cbAmountUnit;
	@FXML private TextFieldDouble tfEk;
	@FXML private ComboBox<String> cbPriceUnitEk;
	@FXML private TextFieldDouble tfVk;
	@FXML private ComboBox<String> cbPriceUnitVk;
	@FXML private ComboBox<String> cbTax;
	
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
	
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern");
	      private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	
	@FXML private Button btnCustomerSearch;
	@FXML private Button btnArticleSearch;
	@FXML private Button btnArticleAdd;
	
	private Stage stage;
	
	public ControllerOfferData(){}
	
	@FXML private void initialize(){
		
		new InitCombos().initComboSalutation(cbSalutation);
		new InitCombos().initComboSalutation(cbSalutationBilling);
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboLand(cbLandBilling);
		new InitCombos().initComboAmountUnit(cbAmountUnit);
		new InitCombos().initComboPriceUnit(cbPriceUnitEk);
		new InitCombos().initComboPriceUnit(cbPriceUnitVk);
		new InitCombos().initComboTax(cbTax);
		
		tfOfferDate.setValue(LocalDate.now());
		tfRequestDate.setValue(LocalDate.now());
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		
		initBtnCustomerSearch();
		initBtnArticleSearch();
		initBtnArticleAdd();
		
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
	
	private void initBtnArticleSearch(){
		
		btnArticleSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadArticleSearch articleSearch = new LoadArticleSearch(true);
				if(articleSearch.getController().getSelectedArticleID() != 0){
					
					ModelArticle article = new SelectArticle(new ModelArticle(articleSearch.getController().getSelectedArticleID())).getModelArticle();
					
					tfArticleID.setText(String.valueOf(article.getArticleID()));
					tfDescription1.setText(article.getDescription1());
					tfDescription2.setText(article.getDescription2());
					tfBarrelsize.setText(article.getBarrelsize());
					tfBolting.setText(article.getBolting());
					tfEk.setText(String.valueOf(article.getEk()));
					cbPriceUnitEk.getSelectionModel().select(String.valueOf(article.getPriceUnit()));					
					cbPriceUnitVk.getSelectionModel().select(String.valueOf(article.getPriceUnit()));
					cbAmountUnit.getSelectionModel().select(article.getAmountUnit());
					
					//ACTIVATE TEXTFIELDS
					tfAmount.setDisable(false);
					tfEk.setDisable(false);
					tfVk.setDisable(false);
					
					btnArticleAdd.setDisable(false);
					
				}
				
			}
		});
		
	}
	
	private void initBtnArticleAdd(){
		
		btnArticleAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(! tfArticleID.getText().equals("")){
					
					/* CALULATE TOTAL PRICE */
					if(tfAmount.getText().isEmpty()){
						tfAmount.setText("0.00");
					}else if(tfEk.getText().isEmpty()){
						tfEk.setText("0.00");
					}else if(tfVk.getText().isEmpty()){
						tfVk.setText("0.00");
					}
					
					double amount = Double.parseDouble(tfAmount.getText());
					int priceUnit = Integer.parseInt(cbPriceUnitVk.getSelectionModel().getSelectedItem());
					BigDecimal vk = new BigDecimal(tfVk.getText());					
					BigDecimal total = new BigDecimal(String.valueOf(vk.multiply(new BigDecimal(amount)).divide(new BigDecimal(priceUnit))));
					total = total.setScale(2, RoundingMode.CEILING);
					/*----------------------*/
					
					tvArticle.getItems().add(new ModelArticle(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()),
						tfDescription1.getText(),
						tfDescription2.getText(),
						tfBarrelsize.getText(),
						tfBolting.getText(),
						new Validate().new ValidateDoubleTwoDigits().validateDouble(tfAmount.getText()),
						cbAmountUnit.getSelectionModel().getSelectedItem(),
						new Validate().new ValidateCurrency().validateCurrency(tfVk.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbPriceUnitVk.getSelectionModel().getSelectedItem()),
						total,
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbTax.getSelectionModel().getSelectedItem())
					));
					
					resetFieldsArticle();			
					tfAmount.setDisable(true);
					tfEk.setDisable(true);
					tfVk.setDisable(true);
					btnArticleAdd.setDisable(true);
					
				}
				
			}
		});
		
	}
	
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("file:resources/search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadOfferSearch offerSearch = new LoadOfferSearch(true);
				if(offerSearch.getController().getSelectedOfferID() != 0){
					selectOffer(offerSearch.getController().getSelectedOfferID());
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("file:resources/new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadOfferAdd offerAdd = new LoadOfferAdd(true);
				int createdOfferID = offerAdd.getController().getCreatedOfferID();
				if(createdOfferID != 0){
					selectOffer(createdOfferID);
				}
				
			}
		});
		
	}
	
	private void initBtnEdit(){
		
		btnEdit.setGraphic(new GraphicButton("file:resources/edit_32.png").getGraphicButton());
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				hboxBtnTopbar.getChildren().add(hboxBtnTopbar.getChildren().indexOf(btnEdit) + 1, btnEditSave);
				hboxBtnTopbar.getChildren().add(hboxBtnTopbar.getChildren().indexOf(btnEdit) + 2, btnEditAbort);
				
				enableFields();				
				setButtonState();
				
			}
		});
		
	}
	
	private void initBtnEditSave(){
		
		btnEditSave.getStyleClass().add("btnTopbar");
		btnEditSave.setGraphic(new GraphicButton("file:resources/save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ModelOffer offer = new ModelOffer();
				if(offer.validate(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
									new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
									String.valueOf(tfOfferDate.getValue()),
									String.valueOf(tfRequestDate.getValue()),
									tvArticle.getItems())){
					
					offer.updateOffer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
						String.valueOf(tfOfferDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
						tvArticle.getItems()
					);
					
					if(stage != null){
						stage.close();
					}else{
						setButtonState();
					}
					
				}
				
			}
		});
		
	}
	
	private void initBtnEditAbort(){
		
		btnEditAbort.getStyleClass().add("btnTopbar");
		btnEditAbort.setGraphic(new GraphicButton("file:resources/cancel_32.png").getGraphicButton());
		btnEditAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						setButtonState();
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
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectOffer(int _offerID){
		
		ModelOffer offer = new ModelOffer();
		offer.selectOffer(_offerID);
		
		if(offer.getCustomerID() != 0){
		
			tfOfferID.setText(String.valueOf(offer.getOfferID()));
			tfOfferDate.setValue(LocalDate.parse(offer.getOfferDate()));
			selectCustomer(offer.getCustomerID());
			tfRequest.setText(offer.getRequest());
			tfRequestDate.setValue(LocalDate.parse(offer.getRequestDate()));	
			
			tvArticle.setItems(offer.getObsListArticle());
			
			lblSubHeadline.setText(" - " + tfOfferID.getText() + " " + tfName1.getText() + ", " + tfZip.getText() + " " + tfLocation.getText());
			
		}else{
			resetFields();
		}
		
	}
	
	private void selectCustomer(int _customerID){
		
		ModelCustomer customer = new ModelCustomer();
		customer.selectCustomer(_customerID);
		
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
			
			customer.selectCustomer(customer.getBillingID());
			
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
			tfContactBilling.setText(customer.getContact());
			tfUstIDBilling.setText(customer.getUstID());
			
			cbPaymentBilling.getSelectionModel().select(customer.getPayment());
			tfBankBilling.setText(customer.getBank());
			tfIBANBilling.setText(customer.getIBAN());
			tfBICBilling.setText(customer.getBIC());
			tfBankBilling.setText(customer.getBank());
			tfPaymentSkontoBilling.setText(String.valueOf(customer.getPaymentSkonto()));
			tfPaymentNettoBilling.setText(String.valueOf(customer.getPaymentNetto()));
			tfSkontoBilling.setText(String.valueOf(customer.getSkonto()));
			
		}else{				
			resetFieldsBilling();				
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		lblSubHeadline.setText("");
		
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
		resetFieldsArticle();
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
	
	private void resetFieldsArticle(){
		
		tfArticleID.clear();
		tfDescription1.clear();
		tfDescription2.clear();
		tfBarrelsize.clear();
		tfBolting.clear();
		tfAmount.clear();
		cbAmountUnit.getSelectionModel().selectFirst();
		tfEk.clear();
		cbPriceUnitEk.getSelectionModel().selectFirst();
		tfVk.clear();
		cbPriceUnitVk.getSelectionModel().selectFirst();
		cbTax.getSelectionModel().selectFirst();
		
	}
	
	private void enableFields(){
		
		/* OFFER */
		tfOfferDate.setDisable(false);
		tfRequest.setDisable(false);
		tfRequestDate.setDisable(false);
		
		/* CUSTOMER */
		btnCustomerSearch.setDisable(false);
		
		/* ARTICLE */
		btnArticleSearch.setDisable(false);
		
	}
	
	private void disableFields(){
		
	}
	
	private void setButtonState(){
		
		if(tfArticleID.getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			
		}else{
			
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			
			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
				
				btnDelete.setDisable(true);
				btnNew.setDisable(true);
				btnSearch.setDisable(true);
				btnEdit.setDisable(true);
												
			}else{
				
				btnSearch.setDisable(false);
				btnNew.setDisable(false);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				
			}
		}
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
