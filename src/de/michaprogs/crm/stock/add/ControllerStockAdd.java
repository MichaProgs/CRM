package de.michaprogs.crm.stock.add;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.search.LoadArticleSearch;
import de.michaprogs.crm.components.TextFieldDouble;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.stock.ModelStock;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.search.LoadSupplierSearch;
import de.michaprogs.crm.warehouse.ModelWarehouse;
import de.michaprogs.crm.warehouse.add.LoadWarehouseAdd;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerStockAdd {

	@FXML private TextFieldOnlyInteger tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	@FXML private TextField tfBarrelsize;
	@FXML private TextField tfBolting;
	
	@FXML private TextFieldOnlyInteger tfSupplierID;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextFieldOnlyInteger tfZip;
	@FXML private TextField tfLocation;
	
	@FXML private TextFieldOnlyInteger tfWarehouseID;
	@FXML private TextField tfWarehouse;
	@FXML private TextFieldDouble tfAmount;
	@FXML private ComboBox<String> cbAmountUnit;
	@FXML private TextFieldDouble tfEk;
	@FXML private ComboBox<String> cbPriceUnit;
	@FXML private DatePicker tfDate;
	
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	@FXML private Button btnAdd;
	@FXML private Button btnReset;
	@FXML private Button btnArticleSearch;
	@FXML private Button btnSupplierSearch;
	@FXML private Button btnWarehouseSearch;
	
	@FXML private TableView<ModelStock> tvStock;
	@FXML private TableColumn<ModelStock, Integer> tcArticleID;
	@FXML private TableColumn<ModelStock, String> tcDescription1;
	@FXML private TableColumn<ModelStock, String> tcDescription2;
	@FXML private TableColumn<ModelStock, String> tcBarrelsize;
	@FXML private TableColumn<ModelStock, String> tcBolting;
	@FXML private TableColumn<ModelStock, Integer> tcSupplierID;
	@FXML private TableColumn<ModelStock, String> tcName1;
	@FXML private TableColumn<ModelStock, Integer> tcWarehouseID;
	@FXML private TableColumn<ModelStock, String> tcWarehouse;
	@FXML private TableColumn<ModelStock, Double> tcAmount;
	@FXML private TableColumn<ModelStock, String> tcAmountUnit;
	@FXML private TableColumn<ModelStock, BigDecimal> tcEk;
	@FXML private TableColumn<ModelStock, String> tcPriceUnit;
	@FXML private TableColumn<ModelStock, String> tcDate;
	
	private Stage stage;
	
	public ControllerStockAdd(){}
	
	@FXML private void initialize(){
		
		tfArticleID.setText("");
		tfSupplierID.setText("");
		tfDate.setValue(LocalDate.now());
		
		initBtnSave();
		initBtnAbort();
		initBtnAdd();
		initBtnReset();
		initBtnArticleSearch();
		initBtnSupplierSearch();
		initBtnWarehouseSearch();
		
		initTableStock();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("file:resources/save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(tvStock.getItems().size() != 0){
					
					ModelStock stock = new ModelStock();
					
					for(int i = 0; i < tvStock.getItems().size(); i++){
						
						stock.insertStock(
							tvStock.getItems().get(i).getArticleID(), 
							tvStock.getItems().get(i).getSupplierID(),
							tvStock.getItems().get(i).getWarehouseID(),
							tvStock.getItems().get(i).getAmount(),
							tvStock.getItems().get(i).getEk(),
							tvStock.getItems().get(i).getDate()
						);
						
					}
					
					if(stage != null){
						stage.close();
					}else{
						resetFields();
						tvStock.getItems().clear();
					}
					
				}else{
					System.out.println("Bitte die Tabelle mit Werten füllen!");
				}
				
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("file:resources/cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(stage != null){
					stage.close();
				}else{
					resetFields();
					tvStock.getItems().clear();
				}
				
			}
		});
		
	}
	
	private void initBtnAdd(){
	
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(	! tfDescription1.getText().isEmpty() &&
					! tfName1.getText().isEmpty() &&
					! tfWarehouse.getText().isEmpty() &&
					! tfDate.getValue().equals("")){
					
					tvStock.getItems().add(new ModelStock(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()),
						tfDescription1.getText(), 
						tfDescription2.getText(), 
						tfBarrelsize.getText(), 
						tfBolting.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()),
						tfName1.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfWarehouseID.getText()), 
						tfWarehouse.getText(), 
						new Validate().new ValidateDoubleTwoDigits().validateDouble(tfAmount.getText()),
						cbAmountUnit.getSelectionModel().getSelectedItem(), 
						new Validate().new ValidateCurrency().validateCurrency(tfEk.getText()), 
						cbPriceUnit.getSelectionModel().getSelectedItem(),
						String.valueOf(tfDate.getValue())
					));
					
					resetFields();
					
				}else if(tfDescription1.getText().equals("")){
					System.out.println("Bitte Artikel auswählen!");
				}else if(tfName1.getText().equals("")){
					System.out.println("Bitte Lieferant auswählen!");
				}else if(tfWarehouse.getText().equals("")){
					System.out.println("Bitte Lager auswählen!");
				}else if(tfDate.getValue().equals("")){
					System.out.println("Bitte Datum auswählen!");
				}
				
			}
		});
	
	}
	
	private void initBtnReset(){
		
		btnReset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//TODO Reset Warning-Dialog
				resetFields();
			}
			
		});
		
	}
	
	private void initBtnArticleSearch(){
		
		btnArticleSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadArticleSearch articleSearch = new LoadArticleSearch(true);
				if(articleSearch.getController().getSelectedArticleID() != 0){
					selectArticle(articleSearch.getController().getSelectedArticleID());
				}
				
			}
		});
		
	}
	
	private void initBtnSupplierSearch(){
		
		btnSupplierSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadSupplierSearch supplierSearch = new LoadSupplierSearch(true);
				if(supplierSearch.getController().getSelectedSupplierID() != 0){
					selectSupplier(supplierSearch.getController().getSelectedSupplierID());
				}
				
			}
		});
		
	}
	
	private void initBtnWarehouseSearch(){
		
		btnWarehouseSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadWarehouseAdd warehouse = new LoadWarehouseAdd(true);
				if(warehouse.getController().getSelectedWarehouseID() != 0){
					selectWarehouse(warehouse.getController().getSelectedWarehouseID());
				}
				
			}
		});
		
	}
	
	private void initTableStock(){
		
		this.tcArticleID.setCellValueFactory(new PropertyValueFactory<>("articleID"));
		this.tcDescription1.setCellValueFactory(new PropertyValueFactory<>("description1"));
		this.tcDescription2.setCellValueFactory(new PropertyValueFactory<>("description2"));
		this.tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		this.tcSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
		this.tcName1.setCellValueFactory(new PropertyValueFactory<>("name1"));
		this.tcWarehouseID.setCellValueFactory(new PropertyValueFactory<>("warehouseID"));
		this.tcWarehouse.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
		this.tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		this.tcAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		this.tcEk.setCellValueFactory(new PropertyValueFactory<>("ek"));
		this.tcPriceUnit.setCellValueFactory(new PropertyValueFactory<>("priceUnit"));
		this.tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectArticle(int articleID){
		
		ModelArticle article = new ModelArticle();
		article.selectArticle(articleID);
		
		if(! article.getDescription1().isEmpty()){
			
			tfArticleID.setText(String.valueOf(article.getArticleID()));
			tfDescription1.setText(article.getDescription1());
			tfDescription2.setText(article.getDescription2());
			tfBarrelsize.setText(article.getBarrelsize());
			tfBolting.setText(article.getBolting());
			
			cbAmountUnit.getSelectionModel().select(article.getAmountUnit());
			cbPriceUnit.getSelectionModel().select(String.valueOf(article.getPriceUnit()));
			
		}else{
			System.out.println("Keine Daten gefunden");
		}
		
	}
	
	private void selectSupplier(int supplierID){
		
		ModelSupplier supplier = new ModelSupplier();
		supplier.selectSupplier(supplierID);
		
		if(! supplier.getName1().isEmpty()){
			
			tfSupplierID.setText(String.valueOf(supplier.getSupplierID()));
			tfName1.setText(supplier.getName1());
			tfName2.setText(supplier.getName2());
			tfStreet.setText(supplier.getStreet());
			cbLand.getSelectionModel().select(supplier.getLand());
			tfZip.setText(String.valueOf(supplier.getZip()));
			tfLocation.setText(supplier.getLocation());
			
		}else{
			System.out.println("Keine Daten gefunden");
		}
		
	}
	
	private void selectWarehouse(int warehouseID){
		
		ModelWarehouse warehouse = new ModelWarehouse();
		warehouse.selectWarehouse(warehouseID);
		if(! warehouse.getWarehouse().isEmpty()){
			tfWarehouseID.setText(String.valueOf(warehouse.getWarehouseID()));
			tfWarehouse.setText(warehouse.getWarehouse());
		}else{
			System.out.println("Keine Daten gefunden");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		tfArticleID.clear();
		tfDescription1.clear();
		tfDescription2.clear();
		tfBarrelsize.clear();
		tfBolting.clear();
		
		tfSupplierID.clear();
		tfName1.clear();
		tfName2.clear();
		tfStreet.clear();
		cbLand.getSelectionModel().selectFirst();
		tfZip.clear();
		tfLocation.clear();
		
		tfWarehouseID.clear();
		tfWarehouse.clear();
		tfAmount.setText("0.00"); 
		cbAmountUnit.getSelectionModel().selectFirst();
		tfEk.setText("0.00");
		cbPriceUnit.getSelectionModel().selectFirst();
		tfDate.setValue(LocalDate.now());
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
