package de.michaprogs.crm.article.add;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.amountunit.ModelAmountUnit;
import de.michaprogs.crm.amountunit.SelectAmountUnit;
import de.michaprogs.crm.article.InsertArticle;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.ValidateArticleSave;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import de.michaprogs.crm.article.supplier.add.LoadArticleSupplierAdd;
import de.michaprogs.crm.article.supplier.edit.LoadArticleSupplierEdit;
import de.michaprogs.crm.articlecategory.ModelArticleCategory;
import de.michaprogs.crm.articlecategory.SelectArticleCategory;
import de.michaprogs.crm.barrelsize.data.LoadBarrelsizeData;
import de.michaprogs.crm.bolting.data.LoadBoltingData;
import de.michaprogs.crm.components.ImageFileChooser;
import de.michaprogs.crm.components.TextFieldCurrency;
import de.michaprogs.crm.components.TextFieldDesity;
import de.michaprogs.crm.components.TextFieldDouble;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
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
import javafx.stage.Stage;

public class ControllerArticleAdd {

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
	
	//Buttons
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	@FXML private Button btnImageAdd;
	@FXML private Button btnImageDelete;
	
	@FXML private Button btnArticleSupplierAdd;
	@FXML private Button btnArticleSupplierEdit;
	@FXML private Button btnArticleSupplierDelete;
	
	@FXML private ImageView ivImage;
	@FXML private TextArea taLongtext;
	@FXML private TextArea taNotes;
	
	private Stage stage;
	private String imageFilepath = "";
	private int createdArticleID = 0;
	
	public ControllerArticleAdd(){}
	
	@FXML private void initialize(){
		
		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		/* ComboBoxes */
		cbAmountUnit.setItems(new SelectAmountUnit(new ModelAmountUnit()).getModelAmountUnit().getObsListAmountUnitsComboBox());
		cbAmountUnit.getSelectionModel().selectFirst();
		cbCategory.setItems(new SelectArticleCategory(new ModelArticleCategory()).getModelArticleCategory().getObsListArticleCategoriesComboBox());
		new InitCombos().initComboPriceUnit(cbPriceUnit);
		new InitCombos().initComboTax(cbTax);
		
		/* Buttons */
		initBtnSave();
		initBtnAbort();
		
		initBtnBarrelsize();
		initBtnBolting();
		
		initBtnImageAdd();
		initBtnImageDelete();
		
		initBtnArticleSupplierAdd();
		initBtnArticleSupplierEdit();
		initBtnArticleSupplierDelete();
		
		//Table
		initTableArticleSupplier();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				System.out.println(cbTax.getSelectionModel().getSelectedItem());
				
				/* INSERT ARTICLE */
				if(new ValidateArticleSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
											tfDescription1.getText()).isValid()){
					
					InsertArticle insert = new InsertArticle(
						new ModelArticle(
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
							new Validate().new ValidateDoubleFourDigits().validateDouble(tfDesity.getText()),
							new Validate().new ValidateCurrency().validateCurrency(tfEk.getText()), 
							new Validate().new ValidateCurrency().validateCurrency(tfVk.getText()), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbPriceUnit.getSelectionModel().getSelectedItem()), 
							cbAmountUnit.getSelectionModel().getSelectedItem(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbTax.getSelectionModel().getSelectedItem()), 						
							taLongtext.getText(),
							taNotes.getText(),
							imageFilepath,  						
							0, //STOCK MIN TODO
							0, //STOCK MAX TODO
							0, //STOCK ALERT TODO						
							String.valueOf(LocalDate.now())), //LAST CHANGE
						tvArticleSupplier.getItems()
					);
					
					
					
					if(insert.wasSuccessful()){
						createdArticleID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText());
						if(stage != null){
							stage.close();
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
	
	private void initBtnBarrelsize(){
		
		btnBarrelsize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBarrelsizeData barrelsize = new LoadBarrelsizeData(true);
				if(! barrelsize.getController().getSelectedBarrelsize().equals("")){
					tfBarrelsize.setText(barrelsize.getController().getSelectedBarrelsize());
				}
				
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
	
	private void initBtnArticleSupplierAdd(){
		
		btnArticleSupplierAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addArticleSupplier();
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
	
	private void initBtnArticleSupplierDelete(){
		
		btnArticleSupplierDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteArticleSupplier();
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
	public int getCreatedArticleID(){
		return createdArticleID;
	}
	
	/* Set by Loader-Class */
	public void setStage(Stage stage){
		this.stage = stage;
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
