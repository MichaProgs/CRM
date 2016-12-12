package de.michaprogs.crm.documents.invoice.add;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.Validate.ValidateOnlyInteger;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.clerk.data.ControllerClerkData;
import de.michaprogs.crm.clerk.search.LoadClerkDataSearch;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill;
import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill.DeliverybillSelection;
import de.michaprogs.crm.documents.deliverybill.search.LoadDeliverybillSearch;
import de.michaprogs.crm.documents.invoice.InsertInvoice;
import de.michaprogs.crm.documents.invoice.ModelInvoice;
import de.michaprogs.crm.documents.invoice.ValidateInvoiceSave;
import de.michaprogs.crm.position.data.ControllerPositionData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerInvoiceAdd {

	/* Invoice */
	@FXML private TextField tfInvoiceID;
	@FXML private DatePicker tfInvoiceDate;
	@FXML private TextField tfDeliverybillID;
	@FXML private DatePicker tfDeliveryDate;
	@FXML private TextArea taNotes;

	/* CLERK - NESTED CONTROLLER */
	@FXML
	private ControllerClerkData clerkDataController; // fx:id + 'Controller'

	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML
	private ControllerDeliveryAdress deliveryAdressController; // fx:id +
																// 'Controller'

	/* BILLINGADRESS - NESTED CONTROLLER */
	@FXML
	private ControllerBillingAdress billingAdressController; // fx:id +
																// 'Controller'

	/* ARTICLE - NESTED CONTROLLER */
	@FXML
	private ControllerPositionData positionDataController; // fx:id +
															// 'Controller'

	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnDelete;

	@FXML private Button btnSearchDeliverybill;
	
	private Stage stage;
	private Main main;
	private int createdInvoiceID;
	private int createdInvoiceCustomerID;

	public ControllerInvoiceAdd() {}

	@FXML
	private void initialize() {

		tfInvoiceDate.setValue(LocalDate.now());
		tfDeliveryDate.setValue(LocalDate.now());

		/* BUTTONS */
		initBtnSave();
		initBtnAbort();
		initBtnSearchDeliverybill();
		
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
	private void initBtnSave() {

		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (	new ValidateInvoiceSave(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText()), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getTfClerkID().getText()),
						String.valueOf(tfDeliveryDate.getValue()),
						positionDataController.getObsListPositions()).isValid()){

					new InsertInvoice(new ModelInvoice(
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText()),
							String.valueOf(tfInvoiceDate.getValue()), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()),
							String.valueOf(tfDeliveryDate.getValue()), 
							taNotes.getText(),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(clerkDataController.getTfClerkID().getText()),
							positionDataController.getObsListPositions().size(),
							new BigDecimal(positionDataController.getLblTotal().getText()),
							positionDataController.getObsListPositions()
						)
					);

					createdInvoiceID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText());
					createdInvoiceCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText());

					if (stage != null) {
						stage.close();
					} else {
						resetFields();
					}

				}

			}
		});

	}

	private void initBtnAbort() {

		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				AbortAlert abort = new AbortAlert();
				if (abort.getAbort()) {
					if (stage != null) {
						stage.close();
					} else {
						resetFields();
					}
				}

			}
		});

	}

	private void initBtnSearchDeliverybill(){
		
		btnSearchDeliverybill.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				LoadDeliverybillSearch search = new LoadDeliverybillSearch(true);
				if(search.getController().getSelectedDeliverybillID() != 0){
					selectDeliverybillData(search.getController().getSelectedDeliverybillID());
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectCustomer(int customerID) {

		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();

		deliveryAdressController.selectDeliveryAdress(customerID);

		// ALWAYS LAST - OTHERWISE THE DATA IN THE MODEL WOULD BE OVERWRITTEN
		if (customer.getBillingID() != 0) {
			billingAdressController.selectBillingAdress(customer.getBillingID());
		} else {
			clearBilling();
		}

	}

	/**
	 * the data of the deliverybill will be selected and can directly made to a invoice
	 * @param deliverybillID
	 */
	private void selectDeliverybillData(int deliverybillID){
		
		ModelDeliverybill deliverybill = new SelectDeliverybill(new ModelDeliverybill(deliverybillID, 0), DeliverybillSelection.SPECIFIC_DELIVERYBILL).getModelDeliverybill();
		tfDeliverybillID.setText(String.valueOf(deliverybill.getDeliverybillID()));
		selectCustomer(deliverybill.getCustomerID());
//		clerkDataController.selectClerk(deliverybill.getClerkID()); not required - clerk deliverybill != clerk invoice!
//		positionDataController.getTableArticle().setItems(deliverybill.getObsListArticle());
//		positionDataController.calculateTotal();
//		positionDataController.setButtonState();
		positionDataController.setObsListPositions(deliverybill.getObsListArticle());
		
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields() {

		/* Invoice */
		tfInvoiceID.clear();
		tfInvoiceDate.setValue(LocalDate.now());
		tfDeliverybillID.clear();
		tfDeliveryDate.setValue(LocalDate.now());
		taNotes.clear();

		/* DELIVERYADRESS */
		deliveryAdressController.clearFields();

		/* BILLINGADRESS */
		clearBilling();

		/* ARTICLE */
		positionDataController.getObsListPositions().clear();

	}

	private void clearBilling() {
		billingAdressController.clearFields();
	}

	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public int getCreatedInvoiceID() {
		return createdInvoiceID;
	}

	public int getCreatedInvoiceCustomerID() {
		return createdInvoiceCustomerID;
	}

}
