package de.michaprogs.crm.supplier.add;

import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.supplier.InsertSupplier;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.ValidateSupplierSave;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerSupplierAdd {

	@FXML private TextFieldOnlyInteger tfSupplierID;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextFieldOnlyInteger tfZip;
	@FXML private TextField tfLocation;
	
	@FXML private TextField tfPhone;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfWeb;
	@FXML private TextField tfContact;
	@FXML private TextField tfUstID;
	
	@FXML private ComboBox<String> cbPayment;
	@FXML private TextField tfIBAN;
	@FXML private TextField tfBIC;
	@FXML private TextField tfBank;
	@FXML private TextFieldOnlyInteger tfPaymentSkonto;
	@FXML private TextFieldOnlyInteger tfPaymentNetto;
	@FXML private TextFieldOnlyInteger tfSkonto;
	
	@FXML private TextArea taNotes;
	
	//Buttons
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private int createdSupplierID = 0;
	
	public ControllerSupplierAdd(){
		
	}
	
	@FXML private void initialize(){
		
		tfSupplierID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		//ComboBoxes
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboPayment(cbPayment);
		
		//Buttons
		initBtnSave();
		initBtnAbort();
		
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("file:resources/save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(	new ValidateSupplierSave(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
					tfName1.getText()).isValid()){
					
					new InsertSupplier(new ModelSupplier(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
						tfName1.getText(), 
						tfName2.getText(), 
						tfStreet.getText(), 
						cbLand.getSelectionModel().getSelectedItem(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfZip.getText()), 
						tfLocation.getText(), 
						tfPhone.getText(), 
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
					));					
					
					createdSupplierID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText());
					
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
	public int getCreatedSupplierID(){
		return createdSupplierID;
	}
	
}

