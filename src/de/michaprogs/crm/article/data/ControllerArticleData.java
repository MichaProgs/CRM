package de.michaprogs.crm.article.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.add.LoadArticleAdd;
import de.michaprogs.crm.article.barrelsize.add.LoadBarrelsize;
import de.michaprogs.crm.article.bolting.add.LoadBolting;
import de.michaprogs.crm.article.search.LoadArticleSearch;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import de.michaprogs.crm.article.supplier.add.LoadArticleSupplierAdd;
import de.michaprogs.crm.article.supplier.edit.LoadArticleSupplierEdit;
import de.michaprogs.crm.components.ImageFileChooser;
import de.michaprogs.crm.components.TextFieldDesity;
import de.michaprogs.crm.components.TextFieldDouble;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
	
	@FXML private TextFieldDouble tfEk;
	@FXML private TextFieldDouble tfVk;
	@FXML private ComboBox<String> cbPriceUnit;
	@FXML private ComboBox<String> cbAmountUnit;
	@FXML private ComboBox<String> cbTax;
	
	@FXML private ImageView ivImage;
		  private String imageFilepath;
	@FXML private TextArea taLongtext;
	
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
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierArticleID;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierDescription1;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierDescription2;
	@FXML private TableColumn<ModelArticleSupplier, BigDecimal> tcSupplierEk;
	@FXML private TableColumn<ModelArticleSupplier, Integer> tcSupplierPriceUnit;
	@FXML private TableColumn<ModelArticleSupplier, String> tcSupplierAmountUnit;
	
	//Panels & Nodes
	@FXML private HBox hboxBtnTopbar;
	
	public ControllerArticleData(){}
	
	@FXML private void initialize(){

		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		/* ComboBoxes */
		new InitCombos().initComboAmountUnit(cbAmountUnit);
		new InitCombos().initComboCategory(cbCategory);
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
//		//Size
		initBtnBarrelsize();
		initBtnBolting();
//		
//		//Image
		initBtnImageAdd();
		initBtnImageDelete();
//		
		/* Tables */
		initTableArticleSupplier();
		
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
	
		btnSearch.setGraphic(new GraphicButton("file:resources/search_32.png").getGraphicButton());
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
		
		btnNew.setGraphic(new GraphicButton("file:resources/new_32.png").getGraphicButton());
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
	
		btnEdit.setGraphic(new GraphicButton("file:resources/edit_32.png").getGraphicButton());
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
		btnEditSave.setGraphic(new GraphicButton("file:resources/save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				/* UPDATE ARTICLE */
				ModelArticle article = new ModelArticle();				
				if(article.validate(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
									tfDescription1.getText())){
					
					article.updateArticle(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
						tfDescription1.getText(), 
						tfDescription2.getText(), 
						cbCategory.getSelectionModel().getSelectedItem(),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfEanID.getText()),
						
						tfBarrelsize.getText(),
						tfBolting.getText(),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfLength.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfWidth.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfHeight.getText()),
						new Validate().new ValidateDoubleTwoDigits().validateDouble(tfWeight.getText()),
						new Validate().new ValidateDoubleTwoDigits().validateDouble(tfDesity.getText()),
						
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
						
						String.valueOf(LocalDate.now())
					);
					
					/* UPDATE ARTICLE SUPPLIER */
					ModelArticleSupplier articleSupplier = new ModelArticleSupplier();
					articleSupplier.deleteArticleSupplier(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()));
					
					for(int i = 0; i < tvArticleSupplier.getItems().size(); i++){
						
						articleSupplier.insertArticleSupplier(
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
							tvArticleSupplier.getItems().get(i).getSupplierID(), 
							tvArticleSupplier.getItems().get(i).getSupplierArticleID(), 
							tvArticleSupplier.getItems().get(i).getSupplierDescription1(), 
							tvArticleSupplier.getItems().get(i).getSupplierDescription2(), 
							tvArticleSupplier.getItems().get(i).getSupplierEk(), 
							tvArticleSupplier.getItems().get(i).getSupplierPriceUnit(), 
							tvArticleSupplier.getItems().get(i).getSupplierAmountUnit()
						);
						
					}
					
					hboxBtnTopbar.getChildren().remove(btnEditAbort);
					hboxBtnTopbar.getChildren().remove(btnEditSave);
					
					disableAllFields();
					setButtonState();
//					setStockColor();
//					
//					removeTableKeyEntf();
//					removeTableClick();
//					
//					//Reload ArticleData
					selectArticle(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()));
//					
				}
			}
			
		});		
	}
	
	private void initBtnEditAbort(){
		
		btnEditAbort.getStyleClass().add("btnTopbar");
		btnEditAbort.setGraphic(new GraphicButton("file:resources/cancel_32.png").getGraphicButton());
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
		
		btnDelete.setGraphic(new GraphicButton("file:resources/delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DeleteAlert delete = new DeleteAlert();
				
				if(delete.getDelete()){
					new ModelArticle().deleteArticle(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()));
					resetAllFields();
					setButtonState();
//					cbStock.setDisable(true);
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
					btnImageDelete.setVisible(false);
				}
					
			}
		});
		
	}
	
//	private void initBtnRefresh(){
//		
//		btnRefresh.setGraphic(new GraphicButton("file:img/Refresh_32.png").getGraphicButton());
//		btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				if(! tfArticleID.equals("")){
//					selectArticle(tfArticleID.getText());
//				}				
//			}
//		});
//		
//	}
//	
//	private void initBtnPrint(){
//		
//		btnPrint.setGraphic(setGraphicButton("file:img/Print_32.png"));
//		btnPrint.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				
//				ChoiceDialog<String> cd = new ChoiceDialog<>();
//				cd.setTitle("Auswahl");
//				cd.setHeaderText("Welche Aktion möchten Sie ausführen?");
//				cd.getItems().add("Drucken");
//				cd.getItems().add("Datei erstellen");
//				cd.setSelectedItem("Drucken");
//				cd.showAndWait();
//				
//				if(cd.getResult().equals("Datei erstellen")){
//					
//					try{
//					
//						File f = new File("files/" + tfArticleID.getText() + " " + tfDescription1.getText() + ".txt");
//						BufferedWriter out = new BufferedWriter(new FileWriter(f));
//						out.write("Artikel-Nr.: " + tfArticleID.getText());
//						out.newLine();
//						out.write("Kurztext1: " + tfDescription1.getText());
//						out.newLine();
//						out.write("Kurztext2: " + tfDescription2.getText());
//						out.newLine();
//						out.write("Kategorie: " + cbCategory.getSelectionModel().getSelectedItem());
//						out.newLine();
//						out.write("EAN-Nr.: " + tfEanID.getText());
//						out.newLine();
//						out.newLine();
//						out.write("Gebindegröße: " + tfBarrelsize.getText());
//						out.newLine();
//						out.write("Verschraubung: " + tfBolting.getText());
//						out.newLine();
//						out.write("Länge x Breite x Höhe:: " + tfLength.getText() + " x " + tfWidth.getText() + " x " + tfHeight.getText());
//						out.newLine();
//						out.write("Gewicht: " + tfWeight.getText());
//						out.newLine();
//						out.write("Dichte: " + tfDesity.getText());
//						out.newLine();
//						out.newLine();
//						out.write("EK: " + tfEk.getText());
//						out.newLine();
//						out.write("VK: " + tfVk.getText());
//						out.newLine();
//						out.write("Preiseinheit: " + cbPriceUnit.getSelectionModel().getSelectedItem() + " " + cbAmountUnit.getSelectionModel().getSelectedItem());
//						out.newLine();
//						out.write("Ust: " + cbTax.getSelectionModel().getSelectedItem());
//						out.close();
//						
//						System.out.println("Datei mit Artikeldaten " + tfArticleID.getText() + " " + tfDescription1.getText() + " wurde erstellt!");
//						
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//					
//				}
//				
//			}
//		});
//		
//	}
//	
//	private void initBtnEditImage(){
//		
//		btnEditImg.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				
//				ImageFileChooser ifc = new ImageFileChooser();		
//				
//				if(ifc.getImage() != null){
//					ivProductImage.setImage(ifc.getImage());	
//					fileProductImage = ifc.getFile();
//					if(fileProductImage != null){
//						btnDeleteImg.setVisible(true);
//					}
//				}
//				
//			}
//		});
//		
//	}
//	
//	private void initBtnDeleteImage(){
//		
//		btnDeleteImg.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				ivProductImage.setImage(null);
//				fileProductImage = null;
//				btnDeleteImg.setVisible(false);
//			}
//		});
//		
//	}

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

//	private void initCbStock(){
//		
//		ModelStock stock = new ModelStock();
//		stock.selectStockID(cbStock.getSelectionModel().getSelectedItem());
//		tfStockID.setText(String.valueOf(stock.getStockID()));
//		
//		cbStock.valueProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				if(! newValue.equals(oldValue)){
//					stock.selectStockID(cbStock.getSelectionModel().getSelectedItem());
//					tfStockID.setText(String.valueOf(stock.getStockID()));
//					
//					ModelArticleStock stock = new ModelArticleStock();
//					stock.selectArticleStock(tfArticleID.getText(), new Validate().validateOnlyInteger(tfStockID.getText()));
//					tfInventory.setText(String.valueOf(stock.getInventory()));
//					setStockColor();
//				}
//			}
//		});
//		
//	}
	
	private void initBtnBarrelsize(){
		
		btnBarrelsize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBarrelsize barrelsize = new LoadBarrelsize(true);
				tfBarrelsize.setText(barrelsize.getController().getSelectedBarrelsize());
				
			}
		});
		
	}
	
	private void initBtnBolting(){
		
		btnBolting.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBolting bolting = new LoadBolting(true);
				tfBolting.setText(bolting.getController().getSelectedBolting());
				
			}
		});
		
	}
//		
//	private void initComboUnitChange(){
//		
//		cbAmountUnit.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				setStockUnit();
//			}
//		});
//		
//	}
//	
	
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
		
		tvArticleSupplier.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				//When the data is editable
				if(	hboxBtnTopbar.getChildren().contains(btnEditSave) &&
					hboxBtnTopbar.getChildren().contains(btnEditAbort)){
					
					tvArticleSupplier.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							
							if(event.getCode().equals(KeyCode.DELETE)){
								deleteArticleSupplier();
							}else if(event.getCode().equals(KeyCode.ENTER)){
								editArticleSupplier();
							}
							
						}
					});
					
					tvArticleSupplier.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							
							if(	event.getButton().equals(MouseButton.SECONDARY) &&
								event.getClickCount() == 1){
								tvArticleSupplier.setContextMenu(new ContextMenuArticleSupplier());
							}else if(	event.getButton().equals(MouseButton.PRIMARY) &&
										event.getClickCount() == 2){
								editArticleSupplier();
							}
							
						}
					});
					
				}else{
					tvArticleSupplier.setOnKeyPressed(null);
					tvArticleSupplier.setOnMouseClicked(null);
					tvArticleSupplier.setContextMenu(null);
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectArticle(int articleID){
		
		/*
		 * Main Article Data
		 */
		ModelArticle article = new ModelArticle();
		article.selectArticle(articleID);
		
		if(! article.getDescription1().isEmpty()){
			
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
			
			lblSubHeadline.setText(" - " + article.getArticleID() + " " + article.getDescription1());
			lblLastChange.setText(article.getLastChange()); 
			
			if(! article.getImageFilepath().equals("")){
				ivImage.setImage(new Image(article.getImageFilepath()));
			}else{
				ivImage.setImage(null);
			}
			
	//		if(! article.getImageFilePath().equals("")){
	//			fileProductImage  = new File(article.getImageFilePath());
	//			ivProductImage.setImage(new Image(fileProductImage.getPath()));
	//		}else{
	//			ivProductImage.setImage(null);
	//		}
	//		
	//		lblLastChange.setText(new Validate().validateDateGER(article.getLastChange()));
	//		
			/*
			 * ARTICLE SUPPLIER
			 */
			ModelArticleSupplier articleSupplier = new ModelArticleSupplier();
			articleSupplier.selectArticleSupplier(articleID);
			tvArticleSupplier.setItems(articleSupplier.getObsListArticleSupplier());
			
	//		/*
	//		 * Stock
	//		 */
	//		ModelArticleStock articleStock = new ModelArticleStock();
	//		articleStock.selectArticleStock(articleID, new Validate().validateOnlyInteger(tfStockID.getText()));
	//		tfInventory.setText(String.valueOf(articleStock.getInventory()));
	//		tvStock.setItems(articleStock.getObsListStock());
	//		tfStockMin.setText(String.valueOf(article.getStockMinUnit()));
	//		tfStockMax.setText(String.valueOf(article.getStockMaxUnit()));
	//		tfStockAlert.setText(String.valueOf(article.getStockAlertUnit()));
	//		
			setButtonState();
	//		
	//		setStockUnit();
	//		setHeadline();
	//		setStockColor();
	//		cbStock.setDisable(false);
		}else{
			System.out.println("Keinen Artikel gefunden");
			//TODO RESET FIELDS
		}
		
	}
		
	/*
	 * UI METHODS
	 */
	private void resetAllFields(){
		
		lblSubHeadline.setText("");
		
		tfArticleID.clear();
		tfDescription1.clear();
		tfDescription2.clear();
		cbCategory.getSelectionModel().select(0);
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
		
//		ivProductImage.setImage(null);
		
		//Tab Longtext
		taLongtext.clear();
		
		lblLastChange.setText("Letzte Änderung: "); //Same as in the FXML-File
		
		//Tab Stock
//		tvStock.getItems().clear();
//		tfStockAlert.clear();
//		tfStockMax.clear();
//		tfStockMin.clear();
//		tfUnitAlertStock.clear();
//		tfUnitMaxStock.clear();
//		tfUnitMinStock.clear();
//		tfUnitStock.clear();
		
		//Tab Supplier
//		tvSupplierArticle.getItems().clear();
		
//		lblHeadline.setText("Artikelstamm");
//		lblLastChange.setText("");
		
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
		
		/* Second Tab (Lieferanten) */
//		btnArticleSupplierAdd.setDisable(false);
//		if(tvSupplierArticle.getItems().size() > 0){
//			btnArticleSupplierEdit.setDisable(false);
//			btnArticleSupplierDelete.setDisable(false);
//		}else{
//			btnArticleSupplierEdit.setDisable(true);
//			btnArticleSupplierDelete.setDisable(true);
//		}
		
		/* Third Tab (Lager) */
//		tfStockMin.setDisable(false);
//		tfStockMax.setDisable(false);
//		tfStockAlert.setDisable(false);
		
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
				
		/* Second Tab (Lieferanten) */
//		btnArticleSupplierAdd.setDisable(true);
//		btnArticleSupplierEdit.setDisable(true);
//		btnArticleSupplierDelete.setDisable(true);
		
		/* Third Tab (Lager) */
//		tfStockMin.setDisable(true);
//		tfStockMax.setDisable(true);
//		tfStockAlert.setDisable(true);
		
		System.out.println("Alle Felder gesperrt");
		
	}
//			
//	/* Getter & Setter */
//	private void setStockUnit(){
//		
//		tfUnitStock.setText(cbAmountUnit.getSelectionModel().getSelectedItem());
//		tfUnitMinStock.setText(cbAmountUnit.getSelectionModel().getSelectedItem());
//		tfUnitAlertStock.setText(cbAmountUnit.getSelectionModel().getSelectedItem());
//		tfUnitMaxStock.setText(cbAmountUnit.getSelectionModel().getSelectedItem());
//		
//	}
//	
	
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
//			btnPrint.setDisable(true);
//			btnRefresh.setDisable(true);
			
			btnArticleSupplierAdd.setDisable(true);
			btnArticleSupplierEdit.setDisable(true);
			btnArticleSupplierDelete.setDisable(true);
			
		}else{
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
//			btnPrint.setDisable(false);
//			btnRefresh.setDisable(false);
			
			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
				
				btnDelete.setDisable(true);
				btnNew.setDisable(true);
				btnSearch.setDisable(true);
				btnEdit.setDisable(true);
//				btnPrint.setDisable(true);
//				btnRefresh.setDisable(true);
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
	
	/*
	 * CONTEXT-MENU
	 */
	private class ContextMenuArticleSupplier extends ContextMenu{
		
		private MenuItem miAdd = new MenuItem("Hinzufügen..");
		private MenuItem miEdit = new MenuItem("Bearbeiten..");
		private MenuItem miDelete = new MenuItem("Löschen");
		
		public ContextMenuArticleSupplier(){
			
			//initialize
			initMiAdd();
			initMiEdit();
			initMiDelete();
			
			this.getItems().addAll(miAdd, miEdit, miDelete);			
			
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
		
	}
	
}