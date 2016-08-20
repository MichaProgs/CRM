package de.michaprogs.crm.article.supplier.add;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;
import de.michaprogs.crm.supplier.search.LoadSupplierSearch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerArticleSupplierAdd {

	@FXML private TextField tfSupplierID;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextField tfZip;
	@FXML private TextField tfLocation;
	
	@FXML private TextField tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	@FXML private TextField tfBarrelsize;
	@FXML private TextField tfBolting;
	@FXML private TextField tfEk;
	@FXML private ComboBox<String> cbPriceUnit;
	@FXML private ComboBox<String> cbAmountUnit;
	
	/* BUTTONS */
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	@FXML private Button btnSupplierSearch;
	
	private Stage stage;
	private ObservableList<ModelArticleSupplier> obsListArticleSupplier = FXCollections.observableArrayList();
	
	public ControllerArticleSupplierAdd(){}
	
	@FXML private void initialize(){
		
		tfSupplierID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		//ComboBoxes
		new InitCombos().initComboAmountUnit(cbAmountUnit);
		new InitCombos().initComboPriceUnit(cbPriceUnit);
		new InitCombos().initComboLand(cbLand);
		
		//Buttons
		initBtnSupplierSearch();
		initBtnSave();
		initBtnAbort();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSupplierSearch(){
		
		btnSupplierSearch.setOnAction(new EventHandler<ActionEvent>() {
			
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
	
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ModelArticleSupplier articleSupplier = new ModelArticleSupplier();			
				
				if(articleSupplier.validateArticleSupplier(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
						tfDescription1.getText())){
					
					obsListArticleSupplier.add(new ModelArticleSupplier(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()),
						tfName1.getText(),
						tfArticleID.getText(),
						tfDescription1.getText(),
						tfDescription2.getText(),
						new Validate().new ValidateCurrency().validateCurrency(tfEk.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbPriceUnit.getSelectionModel().getSelectedItem()),
						cbAmountUnit.getSelectionModel().getSelectedItem()
					));
					
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
	 * DATABASE METHODS
	 */
	private void selectSupplier(int _supplierID){
		
		ModelSupplier supplier = new SelectSupplier(new ModelSupplier(_supplierID)).getModelSupplier();
		
		if(! supplier.getName1().equals("")){
			
			this.tfSupplierID.setText(String.valueOf(supplier.getSupplierID()));
			this.tfName1.setText(supplier.getName1());
			this.tfName2.setText(supplier.getName2());
			this.tfStreet.setText(supplier.getStreet());
			this.cbLand.getSelectionModel().select(supplier.getLand());
			this.tfZip.setText(String.valueOf(supplier.getZip()));
			this.tfLocation.setText(supplier.getLocation());
			
		}else{
			System.out.println("Keine Daten gefunden!");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	/* Setted by Loader-Class */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/* Setted by Loader-Class */
	public void setArticleData(	String description1, 
								String description2,
								String barrelsize,
								String bolting,
								int priceUnit,
								String amountUnit,
								ObservableList<ModelArticleSupplier> obsListArticleSupplier){
		
		this.tfDescription1.setText(description1);
		this.tfDescription2.setText(description2);
		this.tfBarrelsize.setText(barrelsize);
		this.tfBolting.setText(bolting);
		this.cbPriceUnit.getSelectionModel().select(String.valueOf(priceUnit));
		this.cbAmountUnit.getSelectionModel().select(amountUnit);
		this.obsListArticleSupplier = obsListArticleSupplier;
		
	}
	
	public ObservableList<ModelArticleSupplier> getObsListArticleSupplier(){
		return obsListArticleSupplier;
	}
	
}
