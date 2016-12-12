package de.michaprogs.crm.documents.offer.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.ParseDateDDMMYYYY;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.data.ControllerClerkData;
import de.michaprogs.crm.clerk.search.LoadClerkDataSearch;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
import de.michaprogs.crm.documents.offer.DeleteOffer;
import de.michaprogs.crm.documents.offer.DocumentOffer;
import de.michaprogs.crm.documents.offer.ModelOffer;
import de.michaprogs.crm.documents.offer.SelectOffer;
import de.michaprogs.crm.documents.offer.UpdateOffer;
import de.michaprogs.crm.documents.offer.ValidateOfferSave;
import de.michaprogs.crm.documents.offer.SelectOffer.OfferSelection;
import de.michaprogs.crm.documents.offer.add.LoadOfferAdd;
import de.michaprogs.crm.documents.offer.search.LoadOfferSearch;
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

public class ControllerOfferData {

	@FXML private Label lblSubHeadline;
	@FXML private HBox hboxBtnTopbar;
	
	/* OFFER */
	@FXML private TextFieldOnlyInteger tfOfferID;
	@FXML private DatePicker tfOfferDate;
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
	
	public ControllerOfferData(){}
	
	@FXML private void initialize(){
		
		/* DATE FIELDS */
		tfOfferDate.setValue(LocalDate.now());
		tfRequestDate.setValue(LocalDate.now());
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
		initBtnDocument();
		
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
				
				LoadOfferSearch offerSearch = new LoadOfferSearch(true);
				if(offerSearch.getController().getSelectedOfferID() != 0){
					selectOffer(offerSearch.getController().getSelectedOfferID(), offerSearch.getController().getSelectedOfferCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadOfferAdd offerAdd = new LoadOfferAdd(true, 0);
				int createdOfferID = offerAdd.getController().getCreatedOfferID();
				if(createdOfferID != 0){
					selectOffer(createdOfferID, offerAdd.getController().getCreatedOfferCustomerID());
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
				
				if(new ValidateOfferSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
											String.valueOf(tfOfferDate.getValue()),
											String.valueOf(tfRequestDate.getValue()),
											positionDataController.getObsListPositions()).isValid()){
					
					new UpdateOffer(new ModelOffer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
						String.valueOf(tfOfferDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getTfClerkID().getText()),
						positionDataController.getObsListPositions().size(),
						new BigDecimal(positionDataController.getLblTotal().getText()),
						positionDataController.getObsListPositions()
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
						selectOffer(Integer.valueOf(tfOfferID.getText()), Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
						
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
					new DeleteOffer(
						new ModelOffer( 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText())
						)
					);
					
					resetFields();
					setButtonState();
					
				}
				
			}
		});
		
	}
	
	private void initBtnDocument(){
		
		btnDocument.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				new DocumentOffer(
					/* BILLING */
					new ModelCustomer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfCustomerIDBilling().getText()), 
						billingAdressController.getCbSalutationBilling().getSelectionModel().getSelectedItem(), 
						billingAdressController.getTfName1Billing().getText(), 
						billingAdressController.getTfName2Billing().getText(), 
						billingAdressController.getTfStreetBilling().getText(), 
						billingAdressController.getCbLandBilling().getSelectionModel().getSelectedItem(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfZipBilling().getText()), 
						billingAdressController.getTfLocationBilling().getText(), 
						
						billingAdressController.getTfPhoneBilling().getText(), 
						billingAdressController.getTfMobileBilling().getText(), 
						billingAdressController.getTfFaxBilling().getText(), 
						billingAdressController.getTfEmailBilling().getText(), 
						billingAdressController.getTfWebBilling().getText(), 
						billingAdressController.getTfTaxIDBilling().getText(), 
						billingAdressController.getTfUstIDBilling().getText(), 
						
						billingAdressController.getCbPaymentBilling().getSelectionModel().getSelectedItem(), 
						billingAdressController.getTfIBANBilling().getText(), 
						billingAdressController.getTfBICBilling().getText(), 
						billingAdressController.getTfBankBilling().getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfPaymentSkontoBilling().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfPaymentNettoBilling().getText()), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfSkontoBilling().getText()), 
						billingAdressController.getCbCategoryBilling().getSelectionModel().getSelectedItem(),
						"", 
						"", 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfCustomerIDBilling().getText())), 
					
					/* DELIVERY */
					new ModelCustomer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()), 
						deliveryAdressController.getCbSalutation().getSelectionModel().getSelectedItem(), 
						deliveryAdressController.getTfName1().getText(), 
						deliveryAdressController.getTfName2().getText(), 
						deliveryAdressController.getTfStreet().getText(), 
						deliveryAdressController.getCbLand().getSelectionModel().getSelectedItem(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfZip().getText()), 
						deliveryAdressController.getTfLocation().getText(), 
						
						deliveryAdressController.getTfPhone().getText(), 
						deliveryAdressController.getTfMobile().getText(), 
						deliveryAdressController.getTfFax().getText(), 
						deliveryAdressController.getTfEmail().getText(), 
						deliveryAdressController.getTfWeb().getText(), 
						deliveryAdressController.getTfTaxID().getText(), 
						deliveryAdressController.getTfUstID().getText(), 
						
						deliveryAdressController.getCbPayment().getSelectionModel().getSelectedItem(), 
						deliveryAdressController.getTfIBAN().getText(), 
						deliveryAdressController.getTfBIC().getText(), 
						deliveryAdressController.getTfBank().getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfPaymentSkonto().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfPaymentNetto().getText()), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfSkonto().getText()), 
						deliveryAdressController.getCbCategory().getSelectionModel().getSelectedItem(),
						"", //TODO CHECK
						"",  //TODO CHECK
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfCustomerIDBilling().getText())), 
					
					/* OFFER AND ARTICLE */
					new ModelOffer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
						new ParseDateDDMMYYYY(String.valueOf(tfOfferDate.getValue())).getDateDDMMYYYY(), 
						tfRequest.getText(), 
						new ParseDateDDMMYYYY(String.valueOf(tfRequestDate.getValue())).getDateDDMMYYYY(), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getTfClerkID().getText()), 
						positionDataController.getObsListPositions().size(),
						new BigDecimal(positionDataController.getLblTotal().getText()),
						positionDataController.getObsListPositions()), 
					
					/* CLERK */
					new ModelClerk(
						"", 
						clerkDataController.getTfClerk().getText(), 
						clerkDataController.getTfClerkPhone().getText(),
						clerkDataController.getTfClerkFax().getText(), 
						clerkDataController.getTfClerkEmail().getText(), 
						""));
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectOffer(int offerID, int customerID){
		
		ModelOffer offer = new SelectOffer(new ModelOffer(offerID, customerID), OfferSelection.SPECIFIC_OFFER).getModelOffer();
		
		if(offer.getCustomerID() != 0){
		
			/* MAIN DATA */
			tfOfferID.setText(String.valueOf(offer.getOfferID()));
			tfOfferDate.setValue(LocalDate.parse(offer.getOfferDate()));
			selectCustomer(offer.getCustomerID());
			tfRequest.setText(offer.getRequest());
			tfRequestDate.setValue(LocalDate.parse(offer.getRequestDate()));
			
			/* CLERK */
			clerkDataController.selectClerk(offer.getClerkID());
			
			/* NOTES */
			taNotes.setText(offer.getNotes());
			
			/* ARTICLE */
			positionDataController.setObsListPositions(offer.getObsListArticle());
			
			/* TITLE */
			lblSubHeadline.setText(	"- " + 
									tfOfferID.getText() + " " + 
									deliveryAdressController.getTfName1().getText() + ", " + 
									deliveryAdressController.getTfZip().getText() + " " + 
									deliveryAdressController.getTfLocation().getText());
			
			main.getStage().setTitle(	main.getProgramName() + " - Angebot " + 
										offer.getOfferID() + " " + 
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
			billingAdressController.clearFields();				
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
		
		/* DELIVERYADRESS */
		deliveryAdressController.clearFields();
		
		/* BILLINGADRESS */
		billingAdressController.clearFields();
		
		/* ARTICLE */
		positionDataController.getObsListPositions().clear();
		
	}
	
	private void enableFields(){
		
		/* OFFER */
		tfOfferDate.setDisable(false);
		tfRequest.setDisable(false);
		tfRequestDate.setDisable(false);
		taNotes.setDisable(false);
		
		/* DELIVERYADRESS */
		deliveryAdressController.getBtnCustomerSearch().setDisable(false);
		
		/* BILLINGADRESS */
		//never enable
		
		/* CLERK */
		clerkDataController.getBtnClerkSearch().setDisable(false);
		
	}
	
	private void disableFields(){
		
		/* OFFER */
		tfOfferDate.setDisable(true);
		tfRequest.setDisable(true);
		tfRequestDate.setDisable(true);
		taNotes.setDisable(true);
		
		/* DELIVERYADRESS */
		deliveryAdressController.getBtnCustomerSearch().setDisable(true);
		
		/* BILLINGADRESS */
		billingAdressController.disableFields();
		
		/* CLERK */
		clerkDataController.getBtnClerkSearch().setDisable(true);
		
	}
	
	private void setButtonState(){
		
		if(tfOfferID.getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			btnDocument.setDisable(true);
			
			deliveryAdressController.getBtnCustomerSearch().setDisable(true);
			
			billingAdressController.getBtnBillingAdd().setDisable(true); //never enable
			billingAdressController.getBtnBillingDelete().setDisable(true); //never enable
			
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
