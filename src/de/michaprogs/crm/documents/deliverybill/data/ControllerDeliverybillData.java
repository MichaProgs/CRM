package de.michaprogs.crm.documents.deliverybill.data;

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
import de.michaprogs.crm.clerk.data.ControllerClerkData;
import de.michaprogs.crm.clerk.search.LoadClerkDataSearch;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
import de.michaprogs.crm.documents.deliverybill.DeleteDeliverybill;
import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill;
import de.michaprogs.crm.documents.deliverybill.UpdateDeliverybill;
import de.michaprogs.crm.documents.deliverybill.ValidateDeliverybillSave;
import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill.DeliverybillSelection;
import de.michaprogs.crm.documents.deliverybill.add.LoadDeliverybillAdd;
import de.michaprogs.crm.documents.deliverybill.search.LoadDeliverybillSearch;
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
	
	/* CLERK - NESTED CONTROLLER */
	@FXML private ControllerClerkData clerkDataController; //fx:id + 'Controller'
		
	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'
	
	/* BILLINGADRESS - NESTED CONTROLLER */
	@FXML private ControllerBillingAdress billingAdressController; //fx:id + 'Controller'	
	
	/* ARTICLE - NESTED CONTROLLER */
	@FXML private ControllerPositionData positionDataController; //fx:id + 'Controller'
	
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern");
	      private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	@FXML private Button btnDocument;
	
	private Stage stage;
	private Main main;
	
	public ControllerDeliverybillData(){}
	
	@FXML private void initialize(){
		
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
		
		billingAdressController.showButtons(false);
		deliveryAdressController.getBtnCustomerSearch().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				deliveryAdressController.searchDeliveryAdress();
				billingAdressController.selectBillingAdress(deliveryAdressController.getBillingID());
				
			}
		});
		
		setButtonState();
		
	}
	
	/*
	 * BUTTONS
	 */		
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
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
											String.valueOf(tfDeliverybillDate.getValue()),
											String.valueOf(tfRequestDate.getValue()),
											positionDataController.getObsListPositions()).isValid()){
					
					new UpdateDeliverybill(new ModelDeliverybill(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
						String.valueOf(tfDeliverybillDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getBtnClerkSearch().getText()),
						positionDataController.getObsListPositions().size(),
						new BigDecimal(positionDataController.getLblTotal().getText()),
						positionDataController.getObsListPositions(),
						false //not delivered yet
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
						selectDeliverybill(Integer.valueOf(tfDeliverybillID.getText()), Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
						
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
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText())
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
//					new ModelInvoice(
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
		
		ModelDeliverybill deliverybill = new SelectDeliverybill(new ModelDeliverybill(_DeliverybillID, _customerID), DeliverybillSelection.SPECIFIC_DELIVERYBILL).getModelDeliverybill();
		
		if(deliverybill.getCustomerID() != 0){
		
			/* MAIN DATA */
			tfDeliverybillID.setText(String.valueOf(deliverybill.getDeliverybillID()));
			tfDeliverybillDate.setValue(LocalDate.parse(deliverybill.getDeliverybillDate()));
			tfRequest.setText(deliverybill.getRequest());
			tfRequestDate.setValue(LocalDate.parse(deliverybill.getRequestDate()));
			
			/* CUSTOMER */
			selectCustomer(deliverybill.getCustomerID());
			
			/* CLERK */
			selectClerk(deliverybill.getClerkID());
			
			/* NOTES */
			taNotes.setText(deliverybill.getNotes());
			
			/* ARTICLE */
			positionDataController.setObsListPositions(deliverybill.getObsListArticle());
			
			/* TITLE */
			lblSubHeadline.setText(	"- " + tfDeliverybillID.getText() + " " + 
									deliveryAdressController.getTfName1().getText() + ", " + 
									deliveryAdressController.getTfZip().getText() + " " + 
									deliveryAdressController.getTfLocation().getText());
			
			main.getStage().setTitle(	main.getProgramName() + " - Angebot " + 
										deliverybill.getDeliverybillID() + " " + 
										deliveryAdressController.getTfName1().getText() + ", " + 
										deliveryAdressController.getTfZip().getText() + " " + 
										deliveryAdressController.getTfLocation().getText());
			
			setButtonState();
			positionDataController.calculateTotal();
			
		}else{
			resetFields();
		}
		
	}
	
	private void selectCustomer(int customerID){
		
		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();
		
		deliveryAdressController.selectDeliveryAdress(customerID);
		
		//ALWAYS LAST - OTHERWISE THE DATA IN THE MODEL WOULD BE OVERWRITTEN
		if(customer.getBillingID() != 0){
			billingAdressController.selectBillingAdress(customer.getBillingID());
		}else{				
			clearBilling();				
		}
		
	}
	
	private void selectClerk(int clerkID){
		
		clerkDataController.selectClerk(clerkID);	
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		lblSubHeadline.setText("");
		
		/* DELIVERYBILL */
		tfDeliverybillID.clear();;
		tfDeliverybillDate.setValue(LocalDate.now());;
		tfRequest.clear();
		tfRequestDate.setValue(LocalDate.now());;
		taNotes.clear();
		
		/* DELIVERYADRESS */
		deliveryAdressController.clearFields();
		
		/* BILLINGADRESS */
		clearBilling();
		
		/* ARTICLE */
		positionDataController.getObsListPositions().clear();
		
	}

	private void clearBilling(){
		billingAdressController.clearFields();
	}
	
	private void enableFields(){
		
		/* DELIVERYBILL */
		tfDeliverybillDate.setDisable(false);
		tfRequest.setDisable(false);
		tfRequestDate.setDisable(false);
		taNotes.setDisable(false);
		
		/* CUSTOMER */
		deliveryAdressController.getBtnCustomerSearch().setDisable(false);
		
		/* CLERK */
		clerkDataController.getBtnClerkSearch().setDisable(false);
		
	}
	
	private void disableFields(){
		
		/* DELIVERYBILL */
		tfDeliverybillDate.setDisable(true);
		tfRequest.setDisable(true);
		tfRequestDate.setDisable(true);
		taNotes.setDisable(true);
		
		/* CUSTOMER */
		deliveryAdressController.getBtnCustomerSearch().setDisable(true);
		
		/* CLERK */
		clerkDataController.getBtnClerkSearch().setDisable(true);
		
	}
	
	private void setButtonState(){
		
		if(tfDeliverybillID.getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			btnDocument.setDisable(true);
			
			positionDataController.getBtnArticleAdd().setDisable(true);
			positionDataController.getBtnArticleEdit().setDisable(true);
			positionDataController.getBtnArticleDelete().setDisable(true);
			
			deliveryAdressController.getBtnCustomerSearch().setDisable(true);
			
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
				
				deliveryAdressController.getBtnCustomerSearch().setDisable(false);
				
				positionDataController.getBtnArticleAdd().setDisable(false);
				if(positionDataController.getObsListPositions().size() > 0){
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
				
				deliveryAdressController.getBtnCustomerSearch().setDisable(true);
				
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
