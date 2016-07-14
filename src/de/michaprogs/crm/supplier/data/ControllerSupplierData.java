package de.michaprogs.crm.supplier.data;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.add.LoadSupplierAdd;
import de.michaprogs.crm.supplier.search.LoadSupplierSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerSupplierData {

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
	
	@FXML private TextArea taLongtext;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	
	//Panels & Nodes
	
	private Stage stage;
	
	public ControllerSupplierData(){
		
	}
	
	@FXML private void initialize(){
		
		tfSupplierID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		//ComboBoxes
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboPayment(cbPayment);
		
		//Buttons
		initBtnSearch();
		initBtnNew();
		
		//disable all fields from beginning
		disableAllFields();
		
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("file:resources/search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadSupplierSearch supplierSearch = new LoadSupplierSearch(true);
				int selectedSupplierID = supplierSearch.getController().getSelectedSupplierID();
				if(selectedSupplierID != 0){
					selectSupplier(selectedSupplierID);
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("file:resources/new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadSupplierAdd supplierAdd = new LoadSupplierAdd(true);
				int createdSupplierID = supplierAdd.getController().getCreatedSupplierID();
				if(createdSupplierID != 0){
					selectSupplier(createdSupplierID);
				}
				
			}
		});
		
	}
	
	private void selectSupplier(int _supplierID){
		
		ModelSupplier supplier = new ModelSupplier();
		supplier.selectSupplier(_supplierID);
		
		if(! supplier.getName1().equals("")){
			
			this.tfSupplierID.setText(String.valueOf(supplier.getSupplierID()));
			this.tfName1.setText(supplier.getName1());
			this.tfName2.setText(supplier.getName2());
			this.tfStreet.setText(supplier.getStreet());
			this.cbLand.getSelectionModel().select(supplier.getLand());
			this.tfZip.setText(String.valueOf(supplier.getZip()));
			this.tfLocation.setText(supplier.getLocation());
			
			this.tfPhone.setText(supplier.getPhone());
			this.tfFax.setText(supplier.getFax());
			this.tfEmail.setText(supplier.getEmail());
			this.tfWeb.setText(supplier.getWeb());
			this.tfContact.setText(supplier.getContact());
			this.tfUstID.setText(supplier.getUstID());
			
			this.cbPayment.getSelectionModel().select(supplier.getPayment());
			this.tfIBAN.setText(supplier.getIBAN());
			this.tfBIC.setText(supplier.getBIC());
			this.tfBank.setText(supplier.getBank());
			this.tfPaymentSkonto.setText(String.valueOf(supplier.getPaymentSkonto()));
			this.tfPaymentNetto.setText(String.valueOf(supplier.getPaymentNetto()));
			this.tfSkonto.setText(String.valueOf(supplier.getSkonto()));
			
		}else{
			System.out.println("Keine Daten gefunden!");
		}
		
	}
	
	private void disableAllFields(){
		
		this.tfSupplierID.setDisable(true);
		this.tfName1.setDisable(true);
		this.tfName2.setDisable(true);
		this.tfStreet.setDisable(true);
		this.cbLand.setDisable(true);
		this.tfZip.setDisable(true);
		this.tfLocation.setDisable(true);
		this.tfPhone.setDisable(true);
		this.tfFax.setDisable(true);
		this.tfEmail.setDisable(true);
		this.tfWeb.setDisable(true);
		this.tfContact.setDisable(true);
		this.tfUstID.setDisable(true);
		this.cbPayment.setDisable(true);
		this.tfIBAN.setDisable(true);
		this.tfBIC.setDisable(true);
		this.tfBank.setDisable(true);
		this.tfPaymentSkonto.setDisable(true);
		this.tfPaymentNetto.setDisable(true);
		this.tfSkonto.setDisable(true);
		this.taLongtext.setDisable(true);
		
	}
	
}

