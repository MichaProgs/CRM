package de.michaprogs.crm.article.add;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.barrelsize.add.LoadBarrelsize;
import de.michaprogs.crm.article.bolting.add.LoadBolting;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import de.michaprogs.crm.article.supplier.add.LoadArticleSupplierAdd;
import de.michaprogs.crm.components.TextFieldDesity;
import de.michaprogs.crm.components.TextFieldDouble;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	@FXML private TextFieldDouble tfEk;
	@FXML private TextFieldDouble tfVk;
	@FXML private ComboBox<String> cbPriceUnit;
	@FXML private ComboBox<String> cbAmountUnit;
	@FXML private ComboBox<String> cbTax;
	
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
	
	//Buttons
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	@FXML private Button btnArticleSupplierAdd;
	@FXML private Button btnArticleSupplierEdit;
	@FXML private Button btnArticleSupplierDelete;
	
	@FXML private TextArea taLongtext;
	
	private Stage stage;
	private int createdArticleID = 0;
	
	public ControllerArticleAdd(){}
	
	@FXML private void initialize(){
		
		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		/* ComboBoxes */
		new InitCombos().initComboAmountUnit(cbAmountUnit);
		new InitCombos().initComboCategory(cbCategory);
		new InitCombos().initComboPriceUnit(cbPriceUnit);
		new InitCombos().initComboTax(cbTax);
		
		/* Buttons */
		initBtnSave();
		
		initBtnBarrelsize();
		initBtnBolting();
		
		initBtnArticleSupplierAdd();
		
		//Table
		initTableArticleSupplier();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ModelArticle article = new ModelArticle();
				if(article.validate(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()), 
										tfDescription1.getText())){
					
					article.insertArticle(
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
						"", //IMAGEFILEPATH 
						0, //STOCK MIN 
						0, //STOCK MAX 
						0, //STOCK ALERT 
						String.valueOf(LocalDate.now()) //LAST CHANGE
					);
					
					createdArticleID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText());
					if(stage != null)
						stage.close();
					
				}
				
			}
		});
		
	}
	
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
	
	private void initBtnArticleSupplierAdd(){
		
		btnArticleSupplierAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
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
		this.tcSupplierPriceUnit.setCellValueFactory(new PropertyValueFactory<>("priceUnit"));
		this.tcSupplierAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public int getCreatedArticleID(){
		return createdArticleID;
	}
	
	/* Setted by Loader-Class */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
