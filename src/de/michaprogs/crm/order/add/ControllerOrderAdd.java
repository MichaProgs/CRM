package de.michaprogs.crm.order.add;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.clerk.data.LoadClerkData;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.order.InsertOrder;
import de.michaprogs.crm.order.ModelOrder;
import de.michaprogs.crm.order.ValidateOrderSave;
import de.michaprogs.crm.position.add.LoadAddPosition;
import de.michaprogs.crm.position.edit.LoadEditPosition;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;
import de.michaprogs.crm.supplier.search.LoadSupplierSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerOrderAdd {

	/* OFFER DATA */
	@FXML private TextFieldOnlyInteger tfOrderID;
	@FXML private DatePicker tfOrderDate;
	@FXML private TextField tfRequest;
	@FXML private DatePicker tfRequestDate;
	
	@FXML private TextArea taNotes;
	
	/* CLERK */
	@FXML private TextField tfClerkID;
	@FXML private TextField tfClerk;
	@FXML private TextField tfClerkPhone;
	@FXML private TextField tfClerkFax;
	@FXML private TextField tfClerkEmail;
	
	/* SUPPLIER DATA */
	@FXML private TextFieldOnlyInteger tfSupplierID;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextField tfZip;
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
	@FXML private TextFieldOnlyInteger tfSkonto;
	@FXML private TextFieldOnlyInteger tfPaymentNetto;
	
	/* ARTICLE */
	@FXML private TableView<ModelArticle> tvArticle;
	@FXML private TableColumn<ModelArticle, Integer> tcArticleID;
	@FXML private TableColumn<ModelArticle, String> tcDescription1;
	@FXML private TableColumn<ModelArticle, String> tcDescription2;
	@FXML private TableColumn<ModelArticle, String> tcBarrelsize;
	@FXML private TableColumn<ModelArticle, String> tcBolting;
	@FXML private TableColumn<ModelArticle, String> tcAmount;
	@FXML private TableColumn<ModelArticle, String> tcAmountUnit;
	@FXML private TableColumn<ModelArticle, String> tcVk;
	@FXML private TableColumn<ModelArticle, String> tcPriceUnit;
	@FXML private TableColumn<ModelArticle, String> tcTotal;
	@FXML private TableColumn<ModelArticle, String> tcTax;
	
	/* BUTTONS */
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	@FXML private Button btnSupplierSearch;
	@FXML private Button btnClerkSearch;
	
	@FXML private Button btnArticleAdd;
	@FXML private Button btnArticleEdit;
	@FXML private Button btnArticleDelete;
	
	private Stage stage;
	private Main main;
	private int createdOrderID = 0;
	
	public ControllerOrderAdd(){}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnSave();
		initBtnAbort();
		
		initBtnSupplierSearch();
		initBtnClerkSearch();
		
		initBtnArticleAdd();
		initBtnArticleEdit();
		initBtnArticleDelete();
		
		/* TABLES */
		initTableArticle();
		
	}
	
	/* BUTTONS */
	private void initBtnSupplierSearch(){
		
		btnSupplierSearch.setGraphic(new ImageView(new Image("file:resources/search_15_blue.png", 15, 15, true, true)));
		btnSupplierSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				LoadSupplierSearch supplierSearch = new LoadSupplierSearch(true);
				if(supplierSearch.getController().getSelectedSupplierID() != 0){
					
					ModelSupplier ms = new SelectSupplier(new ModelSupplier(supplierSearch.getController().getSelectedSupplierID())).getModelSupplier();
					tfSupplierID.setText(String.valueOf(ms.getSupplierID()));
					tfName1.setText(ms.getName1());
					tfName2.setText(ms.getName2());
					tfStreet.setText(ms.getStreet());
					cbLand.getSelectionModel().select(ms.getLand());
					tfZip.setText(String.valueOf(ms.getZip()));
					tfLocation.setText(ms.getLocation());
					
					tfPhone.setText(ms.getPhone());
					tfFax.setText(ms.getFax());
					tfEmail.setText(ms.getEmail());
					tfWeb.setText(ms.getWeb());
					tfContact.setText(ms.getContact());
					tfUstID.setText(ms.getUstID());
					
					cbPayment.getSelectionModel().select(ms.getPayment());
					tfIBAN.setText(ms.getIBAN());
					tfBIC.setText(ms.getBIC());
					tfBank.setText(ms.getBank());
					tfPaymentSkonto.setText(String.valueOf(ms.getPaymentSkonto()));
					tfSkonto.setText(String.valueOf(ms.getSkonto()));
					tfSkonto.setText(String.valueOf(ms.getPaymentNetto()));		
					
				}
				
			}
		});
		
	}
	
	private void initBtnClerkSearch(){
		
		btnClerkSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				LoadClerkData clerk = new LoadClerkData(true);
				if(clerk.getController().getSelectedClerkID() != 0){
					
					ModelClerk mc = new SelectClerk(new ModelClerk(clerk.getController().getSelectedClerkID()), Selection.SELECT_SPECIFIC).getModelClerk();
					tfClerkID.setText(String.valueOf(mc.getClerkID()));
					tfClerk.setText(mc.getName());
					tfClerkPhone.setText(mc.getPhone());
					tfClerkFax.setText(mc.getFax());
					tfClerkEmail.setText(mc.getEmail());
					
				}
				
			}
		});
		
	}
	
	private void initBtnArticleAdd(){
		
		btnArticleAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				LoadAddPosition posAdd = new LoadAddPosition(true, tvArticle.getItems());
				tvArticle.setItems(posAdd.getController().getObsListArticle());
				
			}
		});
		
	}
	
	private void initBtnArticleEdit(){
		
		btnArticleEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				editArticle();
			}
		});
		
	}
	
	private void initBtnArticleDelete(){
		
		btnArticleDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteArticle();
			}
		});
		
	}
	
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(new ValidateOrderSave(	new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText()), 
											new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
											String.valueOf(tfOrderDate.getValue()), 
											String.valueOf(tfRequestDate.getValue()), 
											tvArticle.getItems()).isValid()){
					
					new InsertOrder(
						new ModelOrder(
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText()), 
							String.valueOf(tfOrderDate.getValue()), 
							tfRequest.getText(), 
							String.valueOf(tfRequestDate.getValue()), 
							taNotes.getText(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfClerkID.getText()), 
							tvArticle.getItems()
						)
					);
					
					createdOrderID = new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText());
					
					if(stage != null)
						stage.close();
					
				}
				
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(new AbortAlert().getAbort()){
					if(stage != null)
						stage.close();
					//TODO reset fields
				}
				
			}
		});
		
	}
	
	/* TABLES */
	private void initTableArticle(){
		
		tcArticleID.setCellValueFactory(new PropertyValueFactory<>("articleID"));
		tcDescription1.setCellValueFactory(new PropertyValueFactory<>("description1"));
		tcDescription2.setCellValueFactory(new PropertyValueFactory<>("description2"));
		tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));
		tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		tcAmount.getStyleClass().add("tc-align-right");
		tcAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		tcVk.setCellValueFactory(new PropertyValueFactory<>("vk"));
		tcVk.getStyleClass().add("tc-align-right");
		tcPriceUnit.setCellValueFactory(new PropertyValueFactory<>("priceUnit"));
		tcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		tcTotal.getStyleClass().add("tc-align-right");
		tcTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		
		tvArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					editArticle();
				}
				
			}
		});
		
		tvArticle.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode().equals(KeyCode.ENTER)){
					editArticle();
				}else if(event.getCode().equals(KeyCode.DELETE)){
					deleteArticle();
				}
				
			}
		});
		
		tvArticle.setContextMenu(new ContextMenuTableArticle());
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void editArticle(){
		
		if(tvArticle.getSelectionModel().getSelectedItems().size() == 1){
			LoadEditPosition posEdit = new LoadEditPosition(true, tvArticle.getItems(), tvArticle.getSelectionModel().getSelectedIndex());
			tvArticle.setItems(posEdit.getController().getObsListArticle());
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void deleteArticle(){
		
		
		if(tvArticle.getSelectionModel().getSelectedItems().size() == 1){
			if(new DeleteAlert().getDelete()){
				tvArticle.getItems().remove(tvArticle.getSelectionModel().getSelectedIndex());
			}
		}else{
			System.out.println("Bitte 1 Zeile markieren");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setMain(Main main){
		this.main = main;
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getCreatedOrderID(){
		return new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText());
	}

	private class ContextMenuTableArticle extends ContextMenu{
		
		private MenuItem miEdit = new MenuItem("Bearbeiten..");
		private MenuItem miDelete = new MenuItem("Löschen");
		
		public ContextMenuTableArticle(){
			
			/* ITEMS */
			initMiEdit();
			initMiDelete();
			
			this.getItems().addAll(miEdit, miDelete);
			
		}
		
		private void initMiEdit(){
			
			miEdit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					editArticle();
				}
			});
			
		}
		
		private void initMiDelete(){
			
			miDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deleteArticle();
				}
			});
			
		}
		
	}
	
}
