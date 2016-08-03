package de.michaprogs.crm.customer.add;

import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.customer.ModelCustomer;
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

	@FXML private TextField tfCustomerID;
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
	@FXML private TextField tfSkonto;
	@FXML private TextField tfPaymentNetto;
	
	@FXML private Label lblLastChange;
	@FXML private TextArea taNotes;
	
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private int createdCustomerID = 0;
	
	public ControllerCustomerAdd(){}
	
	@FXML private void initialize(){
		
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboPayment(cbPayment);
		
		tfCustomerID.setText("");
		tfZip.setText("");
		
		initBtnSave();
		initBtnAbort();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
	
		btnSave.setGraphic(new GraphicButton("file:resources/save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ModelCustomer customer = new ModelCustomer();
				
				if(	customer.validate(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
					tfName1.getText())){
					
					customer.insertCustomer(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()), 
						tfName1.getText(), 
						tfName2.getText(), 
						tfStreet.getText(), 
						cbLand.getSelectionModel().getSelectedItem(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZip.getText()), 
						tfLocation.getText(), 
						
						tfPhone.getText(), 
						tfMobile.getText(),
						tfFax.getText(), 
						tfEmail.getText(), 
						tfWeb.getText(), 
						tfContact.getText(), 
						tfUstID.getText(), 
						
						cbPayment.getSelectionModel().getSelectedItem(), 
						tfIBAN.getText(), 
						tfBIC.getText(), 
						tfBank.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentSkonto.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfPaymentNetto.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSkonto.getText()),
						
						String.valueOf(LocalDate.now()),
						taNotes.getText()
					);					
					
					createdCustomerID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText());
					
					if(stage != null){
						stage.close();
					}else{
						//TODO RESET FIELDS
					}
					
				}
				
			}
			
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("file:resources/cancel_32.png").getGraphicButton());
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
