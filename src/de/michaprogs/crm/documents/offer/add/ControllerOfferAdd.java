package de.michaprogs.crm.documents.offer.add;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import de.michaprogs.crm.clerk.data.ControllerClerkData;
import de.michaprogs.crm.clerk.search.LoadClerkDataSearch;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
import de.michaprogs.crm.documents.offer.InsertOffer;
import de.michaprogs.crm.documents.offer.ModelOffer;
import de.michaprogs.crm.documents.offer.ValidateOfferSave;
import de.michaprogs.crm.position.add.LoadAddPosition;
import de.michaprogs.crm.position.data.ControllerPositionData;
import de.michaprogs.crm.position.edit.LoadEditPosition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
	
	/* CLERK - NESTED CONTROLLER */
	@FXML private ControllerClerkData clerkDataController; //fx:id + 'Controller'
	
	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'
	
	/* BILLINGADRESS - NESTED CONTROLLER */
	@FXML private ControllerBillingAdress billingAdressController; //fx:id + 'Controller'
	
	/* ARTICLE - NESTED CONTROLLER */
	@FXML private ControllerPositionData positionDataController; //fx:id + 'Controller'
	
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnDelete;
	
	private Stage stage;
	private int createdOfferID;
	private int createdOfferCustomerID;
	
	public ControllerOfferAdd(){}
	
	@FXML private void initialize(){
		
		tfOfferDate.setValue(LocalDate.now());
		tfRequestDate.setValue(LocalDate.now());
		
		/* BUTTONS */		
		initBtnSave();
		initBtnAbort();
			
		clerkDataController.getBtnClerkSearch().setDisable(false);
		billingAdressController.showButtons(false);		
		deliveryAdressController.getBtnCustomerSearch().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				deliveryAdressController.searchDeliveryAdress();
				billingAdressController.selectBillingAdress(deliveryAdressController.getBillingID());
				
			}
		});
		
	}
	
	/*
	 * BUTTONS
	 */	
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new ValidateOfferSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
											String.valueOf(tfOfferDate.getValue()),
											String.valueOf(tfRequestDate.getValue()),
											positionDataController.getObsListPositions()).isValid()){
					
					new InsertOffer(new ModelOffer(
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
					
					createdOfferID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOfferID.getText());
					createdOfferCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText());
					
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
	 * DATABASE METHODS
	 */
	public void selectCustomer(int customerID){
	
		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();
		
		deliveryAdressController.selectDeliveryAdress(customerID);
		
		//ALWAYS LAST - OTHERWISE THE DATA IN THE MODEL WOULD BE OVERWRITTEN
		if(customer.getBillingID() != 0){
			billingAdressController.selectBillingAdress(customer.getBillingID());
		}else{				
			clearBilling();
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
	
}
