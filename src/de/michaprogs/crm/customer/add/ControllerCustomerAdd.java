package de.michaprogs.crm.customer.add;

import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.articlecategory.ModelArticleCategory;
import de.michaprogs.crm.articlecategory.SelectArticleCategory;
import de.michaprogs.crm.contact.data.ControllerContactData;
import de.michaprogs.crm.customer.InsertCustomer;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.ValidateCustomerSave;
import de.michaprogs.crm.customer.category.ModelCustomerCategory;
import de.michaprogs.crm.customer.category.SelectCustomerCategory;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
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
	
	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'
	
	/* BILLINGADRESS - NESTED CONTROLLER */
	@FXML private ControllerBillingAdress billingAdressController; //fx:id + 'Controller'
	
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
		
		deliveryAdressController.showSearchButtonSmall(false);
		deliveryAdressController.enableFields();
		
		initBtnSave();
		initBtnAbort();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
	
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new ValidateCustomerSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()), 
												deliveryAdressController.getTfName1().getText()).isValid()){
					
					InsertCustomer insert = new InsertCustomer(
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
							
							String.valueOf(LocalDate.now()),
							taNotes.getText(),
							
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfCustomerIDBilling().getText())),
						contactDataController.getObsListContact()
					);					
					
					if(insert.wasSuccessful()){
						
						createdCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText());						
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
