package de.michaprogs.crm.deliverybill.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.data.LoadClerkData;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.deliverybill.DeleteDeliverybill;
import de.michaprogs.crm.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.deliverybill.SelectDeliverybill;
import de.michaprogs.crm.deliverybill.SelectDeliverybill.DeliverybillSelection;
import de.michaprogs.crm.deliverybill.UpdateDeliverybill;
import de.michaprogs.crm.deliverybill.ValidateDeliverybillSave;
import de.michaprogs.crm.deliverybill.add.LoadDeliverybillAdd;
import de.michaprogs.crm.deliverybill.search.LoadDeliverybillSearch;
import de.michaprogs.crm.position.data.ControllerPositionData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControllerDeliverybillData {

	@FXML private Label lblSubHeadline;
	@FXML private HBox hboxBtnTopbar;
	
	/* Deliverybill */
	@FXML private TextFieldOnlyInteger tfDeliverybillID;
	@FXML private DatePicker tfDeliverybillDate;
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
	@FXML private TextField tfTaxID;
	@FXML private TextField tfUstID;
	
	@FXML private ComboBox<String> cbPayment;
	@FXML private TextField tfIBAN;
	@FXML private TextField tfBIC;
	@FXML private TextField tfBank;
	@FXML private TextField tfPaymentSkonto;
	@FXML private TextField tfPaymentNetto;
	@FXML private TextField tfSkonto;
	@FXML private ComboBox<String> cbCategory;
	
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
	@FXML private TextField tfTaxIDBilling;
	@FXML private TextField tfUstIDBilling;
	
	@FXML private ComboBox<String> cbPaymentBilling;
	@FXML private TextField tfIBANBilling;
	@FXML private TextField tfBICBilling;
	@FXML private TextField tfBankBilling;
	@FXML private TextField tfPaymentSkontoBilling;
	@FXML private TextField tfSkontoBilling;
	@FXML private TextField tfPaymentNettoBilling;
	@FXML private ComboBox<String> cbCategoryBilling;
	
	/* ARTICLE - NESTED CONTROLLER */
	@FXML private ControllerPositionData positionDataController; //fx:id + 'Controller'
	
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern");
	      private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	@FXML private Button btnDocument;
	
	@FXML private Button btnCustomerSearch;
	@FXML private Button btnClerkSearch;
	
	private Stage stage;
	private Main main;
	
	public ControllerDeliverybillData(){}
	
	@FXML private void initialize(){
		
		/* COMBO BOX */
		new InitCombos().initComboSalutation(cbSalutation);
		new InitCombos().initComboSalutation(cbSalutationBilling);
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboLand(cbLandBilling);
		
		/* DATE FIELDS */
		tfDeliverybillDate.setValue(LocalDate.now());
		tfRequestDate.setValue(LocalDate.now());
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
//		initBtnDocument();
		
		initBtnCustomerSearch();
		initBtnClerkSearch();
		
		setButtonState();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnCustomerSearch(){
		
		//btnSupplierSearch.setGraphic() -> see CSS #btnSearchSmall
		btnCustomerSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
				if(customerSearch.getController().getSelectedCustomerID() != 0){
					selectCustomer(customerSearch.getController().getSelectedCustomerID());		
					positionDataController.calculateTotal();
				}
				
			}
		});
		
	}
	
	private void initBtnClerkSearch(){
		
		//btnSupplierSearch.setGraphic() -> see CSS #btnSearchSmall
		btnClerkSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadClerkData clerkData = new LoadClerkData(true);
				if(clerkData.getController().getSelectedClerkID() != 0){
					selectClerk(clerkData.getController().getSelectedClerkID());
				}
				
			}
		});
		
	}
	
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadDeliverybillSearch DeliverybillSearch = new LoadDeliverybillSearch(true);
				if(DeliverybillSearch.getController().getSelectedDeliverybillID() != 0){
					selectDeliverybill(DeliverybillSearch.getController().getSelectedDeliverybillID(), DeliverybillSearch.getController().getSelectedDeliverybillCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadDeliverybillAdd DeliverybillAdd = new LoadDeliverybillAdd(true, main, 0);
				int createdDeliverybillID = DeliverybillAdd.getController().getCreatedDeliverybillID();
				if(createdDeliverybillID != 0){
					selectDeliverybill(createdDeliverybillID, DeliverybillAdd.getController().getCreatedDeliverybillCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnEdit(){
		
		btnEdit.setGraphic(new GraphicButton("edit_32.png").getGraphicButton());
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
		btnEditSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new ValidateDeliverybillSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
											String.valueOf(tfDeliverybillDate.getValue()),
											String.valueOf(tfRequestDate.getValue()),
											positionDataController.getTableArticle().getItems()).isValid()){
					
					new UpdateDeliverybill(new ModelDeliverybill(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
						String.valueOf(tfDeliverybillDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfClerkID.getText()),
						positionDataController.getTableArticle().getItems().size(),
						new BigDecimal(positionDataController.getLblTotal().getText()),
						positionDataController.getTableArticle().getItems()
					));
					
					if(stage != null){
						stage.close();
					}else{						
						hboxBtnTopbar.getChildren().remove(btnEditSave);
						hboxBtnTopbar.getChildren().remove(btnEditAbort);
						
						disableFields();
						setButtonState();
					}
					
				}
				
			}
		});
		
	}
	
	private void initBtnEditAbort(){
		
		btnEditAbort.getStyleClass().add("btnTopbar");
		btnEditAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnEditAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						
						hboxBtnTopbar.getChildren().remove(btnEditSave);
						hboxBtnTopbar.getChildren().remove(btnEditAbort);
						
						disableFields();
						setButtonState();
						selectDeliverybill(Integer.valueOf(tfDeliverybillID.getText()), Integer.valueOf(tfCustomerID.getText()));
						
					}					
				}
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setGraphic(new GraphicButton("delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new DeleteAlert().getDelete()){
					new DeleteDeliverybill(
						new ModelDeliverybill( 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText())
						)
					);
					
					resetFields();
					setButtonState();
					
				}
				
			}
		});
		
	}
	
//	private void initBtnDocument(){
//		
//		btnDocument.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				
//				new DocumentDeliverybill(
//					/* BILLING */
//					new ModelCustomer(
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerIDBilling.getText()), 
//						cbSalutationBilling.getSelectionModel().getSelectedItem(), 
//						tfName1Billing.getText(), 
//						tfName2Billing.getText(), 
//						tfStreetBilling.getText(), 
//						cbLandBilling.getSelectionModel().getSelectedItem(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZipBilling.getText()), 
//						tfLocationBilling.getText(), 
//						
//						tfPhoneBilling.getText(), 
//						tfMobileBilling.getText(), 
//						tfFaxBilling.getText(), 
//						tfEmailBilling.getText(), 
//						tfWebBilling.getText(), 
//						tfTaxIDBilling.getText(), 
//						tfUstIDBilling.getText(), 
//						
//						cbPaymentBilling.getSelectionModel().getSelectedItem(), 
//						tfIBANBilling.getText(), 
//						tfBICBilling.getText(), 
//						tfBankBilling.getText(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentSkontoBilling.getText()),
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentNettoBilling.getText()), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSkontoBilling.getText()), 
//						cbCategoryBilling.getSelectionModel().getSelectedItem(),
//						"", 
//						"", 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerIDBilling.getText())), 
//					
//					/* DELIVERY */
//					new ModelCustomer(
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
//						cbSalutation.getSelectionModel().getSelectedItem(), 
//						tfName1.getText(), 
//						tfName2.getText(), 
//						tfStreet.getText(), 
//						cbLand.getSelectionModel().getSelectedItem(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZip.getText()), 
//						tfLocation.getText(), 
//						
//						tfPhone.getText(), 
//						tfMobile.getText(), 
//						tfFax.getText(), 
//						tfEmail.getText(), 
//						tfWeb.getText(), 
//						tfTaxID.getText(), 
//						tfUstID.getText(), 
//						
//						cbPayment.getSelectionModel().getSelectedItem(), 
//						tfIBAN.getText(), 
//						tfBIC.getText(), 
//						tfBank.getText(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentSkonto.getText()),
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentNetto.getText()), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSkonto.getText()), 
//						cbCategory.getSelectionModel().getSelectedItem(),
//						"", 
//						"", 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerIDBilling.getText())), 
//					
//					/* Deliverybill AND ARTICLE */
//					new ModelDeliverybill(
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
//						new ParseDateDDMMYYYY(String.valueOf(tfDeliverybillDate.getValue())).getDateDDMMYYYY(), 
//						tfRequest.getText(), 
//						new ParseDateDDMMYYYY(String.valueOf(tfRequestDate.getValue())).getDateDDMMYYYY(), 
//						taNotes.getText(), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
//						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfClerkID.getText()), 
//						positionDataController.getTableArticle().getItems()), 
//					
//					/* CLERK */
//					new ModelClerk(
//						"", 
//						tfClerk.getText(), 
//						tfClerkPhone.getText(),
//						tfClerkFax.getText(), 
//						tfClerkEmail.getText(), 
//						""));
//				
//			}
//		});
//		
//	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectDeliverybill(int _DeliverybillID, int _customerID){
		
		ModelDeliverybill Deliverybill = new SelectDeliverybill(new ModelDeliverybill(_DeliverybillID, _customerID), DeliverybillSelection.SPECIFIC_DELIVERYBILL).getModelDeliverybill();
		
		if(Deliverybill.getCustomerID() != 0){
		
			/* MAIN DATA */
			tfDeliverybillID.setText(String.valueOf(Deliverybill.getDeliverybillID()));
			tfDeliverybillDate.setValue(LocalDate.parse(Deliverybill.getDeliverybillDate()));
			tfRequest.setText(Deliverybill.getRequest());
			tfRequestDate.setValue(LocalDate.parse(Deliverybill.getRequestDate()));
			
			/* CUSTOMER */
			selectCustomer(Deliverybill.getCustomerID());
			
			/* CLERK */
			selectClerk(Deliverybill.getClerkID());
			
			/* NOTES */
			taNotes.setText(Deliverybill.getNotes());
			
			/* ARTICLE */
			positionDataController.getTableArticle().setItems(Deliverybill.getObsListArticle());
			
			/* TITLE */
			lblSubHeadline.setText("- " + tfDeliverybillID.getText() + " " + tfName1.getText() + ", " + tfZip.getText() + " " + tfLocation.getText());
			main.getStage().setTitle(main.getProgramName() + " - Angebot " + Deliverybill.getDeliverybillID() + " " + tfName1.getText() + ", " + tfZip.getText() + " " + tfLocation.getText());
			
			setButtonState();
			positionDataController.calculateTotal();
			
		}else{
			resetFields();
		}
		
	}
	
	private void selectCustomer(int _customerID){
		
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
			tfTaxIDBilling.setText(customerBilling.getTaxID());
			tfUstIDBilling.setText(customerBilling.getUstID());
			
			cbPaymentBilling.getSelectionModel().select(customerBilling.getPayment());
			tfBankBilling.setText(customerBilling.getBank());
			tfIBANBilling.setText(customerBilling.getIBAN());
			tfBICBilling.setText(customerBilling.getBIC());
			tfBankBilling.setText(customerBilling.getBank());
			tfPaymentSkontoBilling.setText(String.valueOf(customerBilling.getPaymentSkonto()));
			tfPaymentNettoBilling.setText(String.valueOf(customerBilling.getPaymentNetto()));
			tfSkontoBilling.setText(String.valueOf(customerBilling.getSkonto()));
			cbCategoryBilling.getSelectionModel().select(customerBilling.getCategory());
			
		}else{				
			resetFieldsBilling();				
		}
		
	}
	
	private void selectClerk(	int _clerkID){
		
		ModelClerk clerk = new SelectClerk(new ModelClerk(_clerkID), de.michaprogs.crm.clerk.SelectClerk.Selection.SELECT_SPECIFIC).getModelClerk();
		tfClerkID.setText(String.valueOf(clerk.getClerkID()));
		tfClerk.setText(clerk.getName());
		tfClerkPhone.setText(clerk.getPhone());
		tfClerkFax.setText(clerk.getFax());
		tfClerkEmail.setText(clerk.getEmail());		
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		lblSubHeadline.setText("");
		
		/* Deliverybill */
		tfDeliverybillID.clear();;
		tfDeliverybillDate.setValue(LocalDate.now());;
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
		tfTaxID.clear();
		tfUstID.clear();
		
		cbPayment.getSelectionModel().selectFirst();
		tfBank.clear();
		tfIBAN.clear();
		tfBIC.clear();
		tfBank.clear();
		tfPaymentSkonto.clear();
		tfPaymentNetto.clear();
		tfSkonto.clear();
		cbCategory.getSelectionModel().select("");
		
		resetFieldsBilling();
		
		/* ARTICLE */
		positionDataController.getTableArticle().getItems().clear();
		
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
		tfTaxIDBilling.clear();
		tfUstIDBilling.clear();
		
		cbPaymentBilling.getSelectionModel().selectFirst();
		tfBankBilling.clear();
		tfIBANBilling.clear();
		tfBICBilling.clear();
		tfBankBilling.clear();
		tfPaymentSkontoBilling.clear();
		tfPaymentNettoBilling.clear();
		tfSkontoBilling.clear();
		cbCategoryBilling.getSelectionModel().select("");
		
	}
	
	private void enableFields(){
		
		/* Deliverybill */
		tfDeliverybillDate.setDisable(false);
		tfRequest.setDisable(false);
		tfRequestDate.setDisable(false);
		taNotes.setDisable(false);
		
		/* CUSTOMER */
		btnCustomerSearch.setDisable(false);
		
		/* CLERK */
		btnClerkSearch.setDisable(false);
		
	}
	
	private void disableFields(){
		
		/* Deliverybill */
		tfDeliverybillDate.setDisable(true);
		tfRequest.setDisable(true);
		tfRequestDate.setDisable(true);
		taNotes.setDisable(true);
		
		/* CUSTOMER */
		btnCustomerSearch.setDisable(true);
		
		/* CLERK */
		btnClerkSearch.setDisable(true);
		
	}
	
	private void setButtonState(){
		
		if(tfDeliverybillID.getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			btnDocument.setDisable(true);
			
			positionDataController.getBtnArticleAdd().setDisable(true);
			positionDataController.getBtnArticleEdit().setDisable(true);
			positionDataController.getBtnArticleDelete().setDisable(true);
			
		}else{
			
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			btnDocument.setDisable(false);
			
			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
				
				btnDelete.setDisable(true);
				btnNew.setDisable(true);
				btnSearch.setDisable(true);
				btnEdit.setDisable(true);
				btnDocument.setDisable(true);
				
				positionDataController.getBtnArticleAdd().setDisable(false);
				if(positionDataController.getTableArticle().getItems().size() > 0){
					positionDataController.getBtnArticleDelete().setDisable(false);
					positionDataController.getBtnArticleEdit().setDisable(false);
				}else{
					positionDataController.getBtnArticleDelete().setDisable(true);
					positionDataController.getBtnArticleEdit().setDisable(true);
				}
				
												
			}else{
				
				btnSearch.setDisable(false);
				btnNew.setDisable(false);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				btnDocument.setDisable(false);
				
				positionDataController.getBtnArticleAdd().setDisable(true);
				positionDataController.getBtnArticleEdit().setDisable(true);
				positionDataController.getBtnArticleDelete().setDisable(true);
				
			}
		}
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
}
