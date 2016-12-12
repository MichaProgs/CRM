package de.michaprogs.crm.documents.deliverybill.add;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.clerk.data.ControllerClerkData;
import de.michaprogs.crm.clerk.search.LoadClerkDataSearch;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
import de.michaprogs.crm.documents.deliverybill.InsertDeliverybill;
import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.documents.deliverybill.ValidateDeliverybillSave;
import de.michaprogs.crm.position.data.ControllerPositionData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerDeliverybillAdd {

	/* DELIVERYBILL */
	@FXML private TextField tfDeliverybillID;
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
	
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnDelete;
	
	private Stage stage;
	private Main main;
	private int createdDeliverybillID;
	private int createdDeliverybillCustomerID;
	
	public ControllerDeliverybillAdd(){}
	
	@FXML private void initialize(){
		
		tfDeliverybillDate.setValue(LocalDate.now());
		tfRequestDate.setValue(LocalDate.now());
		
		/* BUTTONS */
		initBtnSave();
		initBtnAbort();
		
		billingAdressController.showButtons(false);
		clerkDataController.getBtnClerkSearch().setDisable(false);
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
				
				if(new ValidateDeliverybillSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
											String.valueOf(tfDeliverybillDate.getValue()),
											String.valueOf(tfRequestDate.getValue()),
											positionDataController.getObsListPositions()).isValid()){
					
					new InsertDeliverybill(new ModelDeliverybill(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()), 
						String.valueOf(tfDeliverybillDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getTfClerkID().getText()),
						positionDataController.getObsListPositions().size(),
						new BigDecimal(positionDataController.getLblTotal().getText()),
						positionDataController.getObsListPositions(),
						false //not delivered yet
					));
					
					createdDeliverybillID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText());
					createdDeliverybillCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText());
					
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
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	public int getCreatedDeliverybillID(){
		return createdDeliverybillID;
	}
	
	public int getCreatedDeliverybillCustomerID(){
		return createdDeliverybillCustomerID;
	}
	
}
