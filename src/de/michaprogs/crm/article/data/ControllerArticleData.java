package de.michaprogs.crm.article.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.amountunit.ModelAmountUnit;
import de.michaprogs.crm.amountunit.SelectAmountUnit;
import de.michaprogs.crm.article.DeleteArticle;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.article.UpdateArticle;
import de.michaprogs.crm.article.ValidateArticleSave;
import de.michaprogs.crm.article.add.LoadArticleAdd;
import de.michaprogs.crm.article.search.LoadArticleSearch;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import de.michaprogs.crm.article.supplier.add.LoadArticleSupplierAdd;
import de.michaprogs.crm.article.supplier.edit.LoadArticleSupplierEdit;
import de.michaprogs.crm.articlecategory.ModelArticleCategory;
import de.michaprogs.crm.articlecategory.SelectArticleCategory;
import de.michaprogs.crm.barrelsize.data.LoadBarrelsizeData;
import de.michaprogs.crm.bolting.data.LoadBoltingData;
import de.michaprogs.crm.components.ComboWarehouse;
import de.michaprogs.crm.components.ImageFileChooser;
import de.michaprogs.crm.components.TextFieldCurrency;
import de.michaprogs.crm.components.TextFieldDesity;
import de.michaprogs.crm.components.TextFieldDouble;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.stock.ModelStock;
import de.michaprogs.crm.stock.SelectStock;
import de.michaprogs.crm.supplier.data.LoadSupplierData;
import de.michaprogs.crm.warehouse.ModelWarehouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;

public class ControllerArticleData{
	
	@FXML private Label lblSubHeadline;
	
	@FXML private TextFieldOnlyInteger tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	@FXML private ComboBox<String> cbCategory;
	@FXML private TextField tfEanID;
	
	@FXML private TextField tfBarrelsize;
	@FXML private TextField tfBolting;
	@FXML private TextFieldOnlyInteger tfLength;
	@FXML private TextFieldOnlyInteger tfWidth;
	@FXML private TextFieldOnlyInteger tfHeight;
	@FXML private TextFieldDouble tfWeight;
	@FXML private TextFieldDesity tfDesity;
	
	@FXML private TextFieldCurrency tfEk;
	@FXML private TextFieldCurrency tfVk;
	@FXML private ComboBox<String> cbPriceUnit;
	@FXML private ComboBox<String> cbAmountUnit;
	@FXML private ComboBox<String> cbTax;
	
	@FXML private ImageView ivImage;
		  private String imageFilepath = "";
	@FXML private TextArea taLongtext;
	
	@FXML private TextField tfWarehouseID;
	@FXML private ComboWarehouse cbWarehouse;
	@FXML private Label lblStock;
	
	@FXML private Label lblLastChange;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	  	  private Button btnEditSave = new Button("Speichern"); //Initialized in Java-Code
	  	  private Button btnEditAbort = new Button("Abbrechen"); //Initialized in Java-Code
	@FXML private Button btnDelete;
	
	@FXML private Button btnImageAdd;
	@FXML private Button btnImageDelete;
	
	@FXML private Button btnArticleSupplierAdd;
	@FXML private Button btnArticleSupplierEdit;
	@FXML private Button btnArticleSupplierDelete;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	//Tables & Columns
	@FXML private TableView<ModelArticleSupplier> tvArticleSupplier;
	@FXML private TableColumn<ModelArticleSupplier, Integer> tcSupplierID;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierName1;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierArticleID; //SupplierArticleID could be with chars
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierDescription1;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierDescription2;
	@FXML private TableColumn<ModelArticleSupplier, BigDecimal> tcSupplierEk;
	@FXML private TableColumn<ModelArticleSupplier, Integer> tcSupplierPriceUnit;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierAmountUnit;
	
	@FXML private TableView<ModelStock> tvStock;
	@FXML private TableColumn<ModelStock, Integer> tcStockSupplierID;
	@FXML private TableColumn<ModelStock, String> tcStockSupplierName1;
	@FXML private TableColumn<ModelStock, String> tcStockAmount;
	@FXML private TableColumn<ModelStock, String> tcStockAmountUnit;
	@FXML private TableColumn<ModelStock, String> tcStockEk;
	@FXML private TableColumn<ModelStock, String> tcStockPriceUnit;
	@FXML private TableColumn<ModelStock, String> tcStockDate;
	
	//Panels & Nodes
	@FXML private HBox hboxBtnTopbar;
	private Main main;
	
	public ControllerArticleData(){}
	
	@FXML private void initialize(){

		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		/* ComboBoxes */
		cbAmountUnit.setItems(new SelectAmountUnit(new ModelAmountUnit()).getModelAmountUnit().getObsListAmountUnitsComboBox());
		cbCategory.setItems(new SelectArticleCategory(new ModelArticleCategory()).getModelArticleCategory().getObsListArticleCategoriesComboBox());
		new InitCombos().initComboPriceUnit(cbPriceUnit);
		new InitCombos().initComboTax(cbTax);
		
		/* Buttons */
		initBtnSearch();
		initBtnNew();
		
		initBtnEdit();
		initBtnEditAbort();
		initBtnEditSave();
//		
//		initBtnRefresh();
		initBtnDelete();
//		initBtnPrint();
//		
		initBtnBarrelsize();
		initBtnBolting();
		
//		//Image
		initBtnImageAdd();
		initBtnImageDelete();
//		
		/* COMBOS */
		initComboWarehouses();
		
		/* Tables */
		initTableArticleSupplier();
		initTableStock();
		
		//Tab Supplier
		initBtnArticleSupplierAdd();
		initBtnArticleSupplierEdit();
		initBtnArticleSupplierDelete();	
//				
//		//TextFields
//		initTfVkAndEk();
//		
//		/* ContextMenu */
//		initMiDeleteArticleSupplier();
//		initMiEditArticleSupplier();
//		initMiAddArticleSupplier();
//		initContextMenuArticleSupplier();
		
		//Disable fields from beginning
		disableAllFields();
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
				
				LoadArticleSearch search = new LoadArticleSearch(true);
				int selectedArticleID = search.getController().getSelectedArticleID();
				if(selectedArticleID != 0){
					selectArticle(selectedArticleID);
				}
				
			}
		});
		
	}
		
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadArticleAdd articleAdd = new LoadArticleAdd(true);
				int createdArticleID = articleAdd.getController().getCreatedArticleID();
				if(createdArticleID != 0){
					selectArticle(createdArticleID);
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
				
				enableAllFields();				
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
				
				/* UPDATE ARTICLE */			
				if(new ValidateArticleSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
											tfDescription1.getText()).isValid()){
					
					new UpdateArticle(
						new ModelArticle(
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
							tfDescription1.getText(), 
							tfDescription2.getText(), 
							String.valueOf(cbCategory.getSelectionModel().getSelectedItem()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfEanID.getText()),						
							tfBarrelsize.getText(),
							tfBolting.getText(),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfLength.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfWidth.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfHeight.getText()),
							new Validate().new ValidateDoubleTwoDigits().validateDouble(tfWeight.getText()),
							new Validate().new ValidateDoubleFourDigits().validateDouble(tfDesity.getText()),						
							new Validate().new ValidateCurrency().validateCurrency(tfEk.getText()), 
							new Validate().new ValidateCurrency().validateCurrency(tfVk.getText()), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbPriceUnit.getSelectionModel().getSelectedItem()), 
							cbAmountUnit.getSelectionModel().getSelectedItem(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbTax.getSelectionModel().getSelectedItem()),
							taLongtext.getText(),	
							imageFilepath, //IMAGEFILEPATH						
							0, //STOCK MIN
							0, //STOCK MAX
							0, //STOCK ALERT						
							String.valueOf(LocalDate.now())),
						tvArticleSupplier.getItems()
					);
					
					hboxBtnTopbar.getChildren().remove(btnEditAbort);
					hboxBtnTopbar.getChildren().remove(btnEditSave);
					
					disableAllFields();
					setButtonState();
					
					//Reload ArticleData
					selectArticle(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()));
					
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
				
				hboxBtnTopbar.getChildren().remove(btnEditAbort);
				hboxBtnTopbar.getChildren().remove(btnEditSave);
				
				disableAllFields();
				setButtonState();
				selectArticle(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()));
				
//				removeTableKeyEntf();
//				removeTableClick();
				
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setGraphic(new GraphicButton("delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DeleteAlert delete = new DeleteAlert();
				
				if(delete.getDelete()){
					new DeleteArticle(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()));
					resetAllFields();
					setButtonState();
				}
				
			}
		});
		
	}

	private void initBtnImageAdd(){
		
		btnImageAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ImageFileChooser ifc = new ImageFileChooser();
				if(ifc.getImageFile() != null){
					
					imageFilepath = ifc.getImageFile().getPath();
					imageFilepath = "file:///" + imageFilepath;
					
					ivImage.setImage(new Image(imageFilepath));
					btnImageDelete.setVisible(true);
					
				}
			}
		});
		
	}
	
	private void initBtnImageDelete(){
		
		btnImageDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
					
				DeleteAlert delete = new DeleteAlert();
				if(delete.getDelete()){
					ivImage.setImage(null);
					imageFilepath = "";
					btnImageDelete.setVisible(false);
				}
					
			}
		});
		
	}
	

	/******************************
	 * BUTTONS TITLED PANE SUPPLIER
	 ******************************/
	private void initBtnArticleSupplierAdd(){
		
		btnArticleSupplierAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addArticleSupplier();
			}
		});		
	}

	private void initBtnArticleSupplierDelete(){
		
		btnArticleSupplierDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {				
				deleteArticleSupplier();				
			}
		});		
	}
	
	private void initBtnArticleSupplierEdit(){
		
		btnArticleSupplierEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				editArticleSupplier();
			}
		});
		
	}
	
	private void initBtnBarrelsize(){
		
		btnBarrelsize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBarrelsizeData barrelsize = new LoadBarrelsizeData(true);
				tfBarrelsize.setText(barrelsize.getController().getSelectedBarrelsize());
				
			}
		});
		
	}
	
	private void initBtnBolting(){
		
		btnBolting.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBoltingData bolting = new LoadBoltingData(true);
				if(! bolting.getController().getSelectedBolting().equals("")){
					tfBolting.setText(bolting.getController().getSelectedBolting());
				}
				
			}
		});
		
	}

	
	/*
	 * COMBOBOXES
	 */
	private void initComboWarehouses(){
		
		cbWarehouse.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(newValue != oldValue){
					selectStock(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), cbWarehouse.getSelectionModel().getSelectedItem());
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableArticleSupplier(){
		
		this.tcSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
		this.tcSupplierName1.setCellValueFactory(new PropertyValueFactory<>("supplierName1"));
		this.tcSupplierArticleID.setCellValueFactory(new PropertyValueFactory<>("supplierArticleID"));
		this.tcSupplierDescription1.setCellValueFactory(new PropertyValueFactory<>("supplierDescription1"));
		this.tcSupplierDescription2.setCellValueFactory(new PropertyValueFactory<>("supplierDescription2"));
		this.tcSupplierEk.setCellValueFactory(new PropertyValueFactory<>("supplierEk"));
		this.tcSupplierPriceUnit.setCellValueFactory(new PropertyValueFactory<>("supplierPriceUnit"));
		this.tcSupplierAmountUnit.setCellValueFactory(new PropertyValueFactory<>("supplierAmountUnit"));
		
		tvArticleSupplier.setContextMenu(new ContextMenuArticleSupplier());
		
		tvArticleSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(tvArticleSupplier.getSelectionModel().getSelectedItems().size() == 1){
					
					if(	event.getClickCount() == 2 &&
						isEditable()){					
						editArticleSupplier();
					}
				
				}else{
					System.out.println("Bitte 1 Zeile markieren!");
				}
				
			}
		});
		
		tvArticleSupplier.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(	event.getCode().equals(KeyCode.DELETE) &&
					isEditable()){
					deleteArticleSupplier();
				}else if(	event.getCode().equals(KeyCode.ENTER) &&
							isEditable()){
					editArticleSupplier();
				}
				
			}
		});
		
	}
	
	private void initTableStock(){
		
		this.tcStockSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
		this.tcStockSupplierName1.setCellValueFactory(new PropertyValueFactory<>("name1"));
		this.tcStockAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		this.tcStockAmount.setStyle("-fx-alignment: CENTER_RIGHT;");
		this.tcStockAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		this.tcStockEk.setCellValueFactory(new PropertyValueFactory<>("ek"));
		this.tcStockEk.setStyle("-fx-alignment: CENTER_RIGHT;");
		this.tcStockPriceUnit.setCellValueFactory(new PropertyValueFactory<>("priceUnit"));
		this.tcStockDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectArticle(int _articleID){
		 
		ModelArticle article = new SelectArticle(new ModelArticle(_articleID)).getModelArticle(); 
		
		/* ARTICLE DATA */
		tfArticleID.setText(String.valueOf(article.getArticleID()));
		tfDescription1.setText(article.getDescription1());
		tfDescription2.setText(article.getDescription2());
		cbCategory.getSelectionModel().select(article.getCategory());
		tfEanID.setText(String.valueOf(article.getEanID()));
		
		tfBarrelsize.setText(article.getBarrelsize());
		tfBolting.setText(article.getBolting());
		tfLength.setText(String.valueOf(article.getLength()));
		tfWidth.setText(String.valueOf(article.getWidth()));
		tfHeight.setText(String.valueOf(article.getHeight()));
		tfWeight.setText(String.valueOf(article.getWeight()));
		tfDesity.setText(String.valueOf(article.getDesity()));
		
		tfEk.setText(String.valueOf(article.getEk()));
		tfVk.setText(String.valueOf(article.getVk()));
		cbPriceUnit.getSelectionModel().select(String.valueOf(article.getPriceUnit()));
		cbAmountUnit.getSelectionModel().select(article.getAmountUnit());
		cbTax.getSelectionModel().select(article.getTax());
		
		taLongtext.setText(article.getLongtext());
		
		/* TITLE */
		lblSubHeadline.setText(" - " + article.getArticleID() + " " + article.getDescription1());
		lblLastChange.setText(article.getLastChange());
		main.getStage().setTitle(main.getProgramName() + " - Artikelstamm " + article.getArticleID() + " " + article.getDescription1());
		
		imageFilepath = article.getImageFilepath();		
		if(	! article.getImageFilepath().equals("")){
			ivImage.setImage(new Image(article.getImageFilepath()));
		}else{
			ivImage.setImage(null);
		}
		
		/* ARTICLE SUPPLIER */
		tvArticleSupplier.setItems(article.getObsListArticleSupplier());
	
		/* STOCK */
		selectStock(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), cbWarehouse.getSelectionModel().getSelectedItem());
		
		setButtonState();
		
	}
	
	private void selectStock(int _articleID, String _warehouse){
		
		ModelWarehouse warehouse = new ModelWarehouse();
		warehouse.selectWarehouseID(_warehouse);

		int _warehouseID = warehouse.getWarehouseID();
		
		tvStock.setItems(new SelectStock(_articleID, _warehouseID).getObsListStock());
		
		//Calculate Total Stock and Average Price
		double totalStock = 0.00;
		BigDecimal averagePrice = new BigDecimal("0.00");
		
		for(int i = 0; i < tvStock.getItems().size(); i++){
			
			totalStock = totalStock + tvStock.getItems().get(i).getAmount();
			averagePrice = averagePrice.add(tvStock.getItems().get(i).getEk());
			
		}
		
		if(tvStock.getItems().size() != 0){
			averagePrice = averagePrice.divide(new BigDecimal(tvStock.getItems().size()));
			averagePrice = averagePrice.setScale(2, RoundingMode.CEILING);
		}
		
		lblStock.setText("Es sind " + totalStock + " " + cbAmountUnit.getSelectionModel().getSelectedItem() + " am Lager (Durchschnittspreis: " + averagePrice + " EUR / " +  cbPriceUnit.getSelectionModel().getSelectedItem() + ")");
		
	}
			
	/*
	 * UI METHODS
	 */
	private void resetAllFields(){
		
		lblSubHeadline.setText("");
		
		tfArticleID.clear();
		tfDescription1.clear();
		tfDescription2.clear();
		cbCategory.getSelectionModel().clearSelection();
		tfEanID.clear();
		
		tfBarrelsize.clear();
		tfBolting.clear();
		tfLength.clear();
		tfWidth.clear();
		tfHeight.clear();
		tfWeight.clear();
		tfDesity.clear();
		
		tfEk.clear();
		tfVk.clear();
		cbPriceUnit.getSelectionModel().select(0);
		cbAmountUnit.getSelectionModel().select(0);
		cbTax.getSelectionModel().select(1);
				
		//Tab Longtext
		taLongtext.clear();
		
		ivImage.setImage(null);
		
		tvArticleSupplier.getItems().clear();
		tvStock.getItems().clear();
		cbWarehouse.getSelectionModel().selectFirst();
		lblStock.setText("");
		
		lblLastChange.setText("Letzte Änderung: "); //Same as in the FXML-File
		
	}
	
	private void enableAllFields(){
		
		/* First Block (Hauptstammdaten) */
		tfArticleID.setDisable(true); //tfArticleID should never be enabled
		tfDescription1.setDisable(false);
		tfDescription2.setDisable(false);
		cbCategory.setDisable(false);
		tfEanID.setDisable(false);
			
		/* Second Block (Größen- und Gewichtsangaben */
		//tfBarrelsize should never be enabled
		btnBarrelsize.setDisable(false);
		//tfBolting should never be enabled
		btnBolting.setDisable(false);
		tfLength.setDisable(false);
		tfWidth.setDisable(false);
		tfHeight.setDisable(false);
		tfWeight.setDisable(false);
		tfDesity.setDisable(false);
		
		/* Thrid Block (Preisinformationen) */
		tfEk.setDisable(false);
		tfVk.setDisable(false);
		cbAmountUnit.setDisable(false);
		cbPriceUnit.setDisable(false);
		cbTax.setDisable(false);
		
		/* First Tab (Langtext) */
		taLongtext.setDisable(false);
		
	}
	
	private void disableAllFields(){
		
		/* First Block (Hauptstammdaten */
		tfArticleID.setDisable(true);
		tfDescription1.setDisable(true);
		tfDescription2.setDisable(true);
		cbCategory.setDisable(true);
		tfEanID.setDisable(true);
			
		/* Second Block (Größen- und Gewichtsangaben*/
		tfBarrelsize.setDisable(true);
		btnBarrelsize.setDisable(true);
		tfBolting.setDisable(true);
		btnBolting.setDisable(true);
		tfLength.setDisable(true);
		tfWidth.setDisable(true);
		tfHeight.setDisable(true);
		tfWeight.setDisable(true);
		tfDesity.setDisable(true);
		
		/* Third Block (Preisinformationen) */
		tfEk.setDisable(true);
		tfVk.setDisable(true);
		cbAmountUnit.setDisable(true);
		cbPriceUnit.setDisable(true);
		cbTax.setDisable(true);		
		
		/* First Tab (Langtext) */
		taLongtext.setDisable(true);				
		
	}

	private boolean isEditable(){
		
		if(	hboxBtnTopbar.getChildren().contains(btnEditSave) &&
			hboxBtnTopbar.getChildren().contains(btnEditAbort)){
			return true;
		}else{
			return false;
		}
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void addArticleSupplier(){
		
		LoadArticleSupplierAdd articleSupplierAdd = new LoadArticleSupplierAdd(
			true, 
			tfDescription1.getText(), 
			tfDescription2.getText(),
			tfBarrelsize.getText(),
			tfBolting.getText(),
			new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbPriceUnit.getSelectionModel().getSelectedItem()),
			cbAmountUnit.getSelectionModel().getSelectedItem(),
			tvArticleSupplier.getItems()
		);

		tvArticleSupplier.setItems(articleSupplierAdd.getController().getObsListArticleSupplier());
		if(tvArticleSupplier.getItems().size() > 0){
			tvArticleSupplier.getSelectionModel().selectFirst();
			tvArticleSupplier.requestFocus();
		}
		
	}
	
	private void editArticleSupplier(){
		
		if(tvArticleSupplier.getSelectionModel().getSelectedItems().size() == 1){
			
			int index = tvArticleSupplier.getSelectionModel().getSelectedIndex();
			
			new LoadArticleSupplierEdit(
				true, 
				tvArticleSupplier.getItems().get(index).getSupplierID(),
				tvArticleSupplier.getItems().get(index).getSupplierArticleID(),
				tvArticleSupplier.getItems().get(index).getSupplierDescription1(),
				tvArticleSupplier.getItems().get(index).getSupplierDescription2(), 
				tfBarrelsize.getText(), 
				tfBolting.getText(), 
				tvArticleSupplier.getItems().get(index).getSupplierEk(),
				tvArticleSupplier.getItems().get(index).getSupplierPriceUnit(), 
				tvArticleSupplier.getItems().get(index).getSupplierAmountUnit(), 
				tvArticleSupplier.getItems(),
				index
			);
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	private void deleteArticleSupplier(){
		
		if(tvArticleSupplier.getSelectionModel().getSelectedItems().size() == 1){
			
			DeleteAlert delete = new DeleteAlert();
			if(delete.getDelete()){
				tvArticleSupplier.getItems().remove(tvArticleSupplier.getSelectionModel().getSelectedIndex());
				if(tvArticleSupplier.getItems().size() > 0){
					tvArticleSupplier.getSelectionModel().selectFirst();
					tvArticleSupplier.requestFocus();
				}
			}
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	private void setButtonState(){
		
		if(tfArticleID.getText().equals("")){
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			
			btnArticleSupplierAdd.setDisable(true);
			btnArticleSupplierEdit.setDisable(true);
			btnArticleSupplierDelete.setDisable(true);
			
		}else{
			
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			cbWarehouse.setDisable(false);
			
			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
				
				btnDelete.setDisable(true);
				btnNew.setDisable(true);
				btnSearch.setDisable(true);
				btnEdit.setDisable(true);
				
				cbWarehouse.setDisable(true);
//				
				btnArticleSupplierAdd.setDisable(false);
				btnArticleSupplierEdit.setDisable(false);
				btnArticleSupplierDelete.setDisable(false);
				
				btnImageAdd.setDisable(false);
				if(ivImage.getImage() != null){
					btnImageDelete.setVisible(true);
				}
				
			}else{
				
				btnSearch.setDisable(false);
				btnNew.setDisable(false);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				
				cbWarehouse.setDisable(false);
				
				btnArticleSupplierAdd.setDisable(true);
				btnArticleSupplierEdit.setDisable(true);
				btnArticleSupplierDelete.setDisable(true);
				
				btnImageAdd.setDisable(true);
				if(ivImage.getImage() != null){
					btnImageDelete.setVisible(false);
				}
				
			}
		}
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	/*
	 * CONTEXT-MENU
	 */
	private class ContextMenuArticleSupplier extends ContextMenu{
		
		private MenuItem miAdd = new MenuItem("Hinzufügen..");
		private MenuItem miEdit = new MenuItem("Bearbeiten..");
		private MenuItem miDelete = new MenuItem("Löschen");
		
		private MenuItem miGoTo = new MenuItem("Gehe zu..");
		
		public ContextMenuArticleSupplier(){
			
			//initialize
			initMiAdd();
			initMiEdit();
			initMiDelete();
			
			initMiGoTo();
			
			this.getItems().addAll(miAdd, miEdit, miDelete, new SeparatorMenuItem(), miGoTo);
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(hboxBtnTopbar.getChildren().contains(btnEditSave)){
						
						miAdd.setDisable(false);
						miEdit.setDisable(false);
						miDelete.setDisable(false);
						miGoTo.setDisable(true);
						
					}else{
						
						miAdd.setDisable(true);
						miEdit.setDisable(true);
						miDelete.setDisable(true);
						miGoTo.setDisable(false);
						
					}
					
				}
			});
			
		}
		
		private void initMiAdd(){
			
			miAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					addArticleSupplier();
				}
			});
			
		}
		
		private void initMiEdit(){
			
			miEdit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					editArticleSupplier();
				}
			});
			
		}

		private void initMiDelete(){
	
			miDelete.setOnAction(new EventHandler<ActionEvent>() {
		
				@Override
				public void handle(ActionEvent event) {
					deleteArticleSupplier();
				}
			});
	
		}
		
		private void initMiGoTo(){
			
			miGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					if(tvArticleSupplier.getSelectionModel().getSelectedItems().size() == 1){
						main.getContentPane().setCenter(new LoadSupplierData(false, tvArticleSupplier.getItems().get(tvArticleSupplier.getSelectionModel().getSelectedIndex()).getSupplierID(), main).getContent());
					}else{
						System.out.println("Bitte 1 Zeile markieren!");
					}
					
				}
			});
			
		}
		
	}
	
}