package de.michaprogs.crm.documents.invoice.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.Validate.ValidateOnlyInteger;
import de.michaprogs.crm.clerk.data.ControllerClerkData;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
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
import de.michaprogs.crm.documents.invoice.DeleteInvoice;
import de.michaprogs.crm.documents.invoice.ModelInvoice;
import de.michaprogs.crm.documents.invoice.SelectInvoice;
import de.michaprogs.crm.documents.invoice.SelectInvoice.InvoiceSelection;
import de.michaprogs.crm.documents.invoice.UpdateInvoice;
import de.michaprogs.crm.documents.invoice.ValidateInvoiceSave;
import de.michaprogs.crm.documents.invoice.add.LoadInvoiceAdd;
import de.michaprogs.crm.documents.invoice.search.LoadInvoiceSearch;
import de.michaprogs.crm.position.data.ControllerPositionData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControllerInvoiceData {

	@FXML private Label lblSubHeadline;
	@FXML private HBox hboxBtnTopbar;
	
	/* INVOICE */
	@FXML private TextFieldOnlyInteger tfInvoiceID;
	@FXML private DatePicker tfInvoiceDate;
	@FXML private TextField tfDeliverybillID;
	@FXML private DatePicker tfDeliveryDate;
	@FXML private TextArea taNotes;
	
	/* CLERK - NESTED CONTROLLER */
	@FXML private ControllerClerkData clerkDataController; //fx:id + 'Controller'
		
	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'
	
	/* BILLINGADRESS - NESTED CONTROLLER */
	@FXML private ControllerBillingAdress billingAdressController; //fx:id + 'Controller'	
	
	/* ARTICLE - NESTED CONTROLLER */
	@FXML private ControllerPositionData positionDataController; //fx:id + 'Controller'
	
	/* BUTTONS */
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern");
	      private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	@FXML private Button btnDocument;
	
	private Stage stage;
	private Main main;
	
	public ControllerInvoiceData(){}

	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
		
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
				
				LoadInvoiceSearch invoiceSearch = new LoadInvoiceSearch(true);
				if(invoiceSearch.getController().getSelectedInvoiceID() != 0){
					selectInvoice(invoiceSearch.getController().getSelectedInvoiceID(), invoiceSearch.getController().getSelectedInvoiceCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadInvoiceAdd InvoiceAdd = new LoadInvoiceAdd(true, main, 0);
				int createdInvoiceID = InvoiceAdd.getController().getCreatedInvoiceID();
				if(createdInvoiceID != 0){
					selectInvoice(createdInvoiceID, InvoiceAdd.getController().getCreatedInvoiceCustomerID());
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
				
				if(new ValidateInvoiceSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()),
											String.valueOf(tfDeliveryDate.getValue()),
											positionDataController.getObsListPositions()).isValid()){
					
					new UpdateInvoice(new ModelInvoice(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText()), 
						String.valueOf(tfInvoiceDate.getValue()), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
						String.valueOf(tfDeliveryDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getBtnClerkSearch().getText()),
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
						selectInvoice(Integer.valueOf(tfInvoiceID.getText()), Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
						
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
					new DeleteInvoice(
						new ModelInvoice( 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText())
						)
					);
					
					resetFields();
					setButtonState();
					
				}
				
			}
		});
		
	}
		
	/*
	 * DATABASE METHODS
	 */
	public void selectInvoice(int invoiceID, int customerID){
		
		ModelInvoice invoice = new SelectInvoice(new ModelInvoice(invoiceID, customerID), InvoiceSelection.SPECIFIC_INVOICE).getModelInvoice();
		
		if(invoice.getCustomerID() != 0){
		
			/* MAIN DATA */
			tfInvoiceID.setText(String.valueOf(invoice.getInvoiceID()));
			tfInvoiceDate.setValue(LocalDate.parse(invoice.getInvoiceDate()));
			tfDeliverybillID.setText(String.valueOf(invoice.getDeliverybillID()));
			tfDeliveryDate.setValue(LocalDate.parse(invoice.getDeliveryDate()));
			
			/* CUSTOMER */
			selectCustomer(invoice.getCustomerID());
			
			/* CLERK */
			selectClerk(invoice.getClerkID());
			
			/* NOTES */
			taNotes.setText(invoice.getNotes());
			
			/* ARTICLE */
			positionDataController.setObsListPositions(invoice.getObsListPositions());
			
			/* TITLE */
			lblSubHeadline.setText(	"- " + tfInvoiceID.getText() + " " + 
									deliveryAdressController.getTfName1().getText() + ", " + 
									deliveryAdressController.getTfZip().getText() + " " + 
									deliveryAdressController.getTfLocation().getText());
			
			main.getStage().setTitle(	main.getProgramName() + " - Rechnung " + 
										invoice.getInvoiceID() + " " + 
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
		tfInvoiceID.clear();;
		tfInvoiceDate.setValue(LocalDate.now());;
		tfDeliverybillID.clear();
		tfDeliveryDate.setValue(LocalDate.now());;
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
		
		/* INVOICE */
		tfInvoiceDate.setDisable(false);
		tfDeliverybillID.setDisable(false);
		tfDeliveryDate.setDisable(false);
		taNotes.setDisable(false);
		
		/* CUSTOMER */
		deliveryAdressController.getBtnCustomerSearch().setDisable(false);
		
		/* CLERK */
		clerkDataController.getBtnClerkSearch().setDisable(false);
		
	}
	
	private void disableFields(){
		
		/* DELIVERYBILL */
		tfInvoiceDate.setDisable(true);
		tfDeliverybillID.setDisable(true);
		tfDeliveryDate.setDisable(true);
		taNotes.setDisable(true);
		
		/* CUSTOMER */
		deliveryAdressController.getBtnCustomerSearch().setDisable(true);
		
		/* CLERK */
		clerkDataController.getBtnClerkSearch().setDisable(true);
		
	}
	
	private void setButtonState(){
		
		if(tfInvoiceID.getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			btnDocument.setDisable(true);
			
			positionDataController.getBtnArticleAdd().setDisable(true);
			positionDataController.getBtnArticleEdit().setDisable(true);
			positionDataController.getBtnArticleDelete().setDisable(true);
			
			deliveryAdressController.getBtnCustomerSearch().setDisable(true);
			clerkDataController.getBtnClerkSearch().setDisable(true);
			
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
				clerkDataController.getBtnClerkSearch().setDisable(false);
				
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
				clerkDataController.getBtnClerkSearch().setDisable(true);
				
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
