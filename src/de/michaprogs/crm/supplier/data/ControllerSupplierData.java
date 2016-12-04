package de.michaprogs.crm.supplier.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.Validate.ValidateOnlyInteger;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.contact.data.ControllerContactData;
import de.michaprogs.crm.offer.add.LoadOfferAdd;
import de.michaprogs.crm.offer.data.LoadOfferData;
import de.michaprogs.crm.order.ModelOrder;
import de.michaprogs.crm.order.SelectOrder;
import de.michaprogs.crm.order.SelectOrder.OrderSelection;
import de.michaprogs.crm.order.add.LoadOrderAdd;
import de.michaprogs.crm.order.data.LoadOrderData;
import de.michaprogs.crm.supplier.DeleteSupplier;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;
import de.michaprogs.crm.supplier.UpdateSupplier;
import de.michaprogs.crm.supplier.ValidateSupplierSave;
import de.michaprogs.crm.supplier.add.LoadSupplierAdd;
import de.michaprogs.crm.supplier.search.LoadSupplierSearch;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerSupplierData {

	@FXML private Label lblSubHeadline;
	
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
	
	/* ORDER */
	@FXML private TableView<ModelOrder> tvOrder;
	@FXML private TableColumn<ModelOrder, Integer> tcOrderID;
	@FXML private TableColumn<ModelOrder, String> tcOrderRequest;
	@FXML private TableColumn<ModelOrder, String> tcOrderDate;
	@FXML private TableColumn<ModelOrder, String> tcClerk;
	@FXML private TableColumn<ModelOrder, Integer> tcAmountOfPositions;
	@FXML private TableColumn<ModelOrder, BigDecimal> tcTotal;
	
	/* CONTACTS - NESTED CONTROLLER! */
	@FXML private ControllerContactData contactDataController; //fx:id + 'Controller'
		
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern"); //Initialized in Java-Code
	      private Button btnEditAbort = new Button("Abbrechen"); //Initialized in Java-Code
	@FXML private Button btnDelete;
	
	//Panels & Nodes
	@FXML private HBox hboxBtnTopbar;
	
	private Stage stage;
	private Main main;
	
	public ControllerSupplierData(){}
	
	@FXML private void initialize(){
		
		tfSupplierID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		//ComboBoxes
		new InitCombos().initComboLand(cbLand);
		new InitCombos().initComboPayment(cbPayment);
		
		//Buttons
		initBtnSearch();
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
		
		//TABLES
		initTableOrder();
		
		//disable all fields from beginning
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
				
				LoadSupplierSearch supplierSearch = new LoadSupplierSearch(true);
				int selectedSupplierID = supplierSearch.getController().getSelectedSupplierID();
				if(selectedSupplierID != 0){
					selectSupplier(selectedSupplierID);
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
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
				
				if(new ValidateSupplierSave(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), 
											tfName1.getText()).isValid()){
					
					new UpdateSupplier(new ModelSupplier(
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
						taNotes.getText()),
						contactDataController.getObsListContact()
					);
					
					hboxBtnTopbar.getChildren().remove(btnEditSave);
					hboxBtnTopbar.getChildren().remove(btnEditAbort);
					
					disableAllFields();
					setButtonState();
					
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
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						selectSupplier(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()));
					}
					
					hboxBtnTopbar.getChildren().remove(btnEditSave);
					hboxBtnTopbar.getChildren().remove(btnEditAbort);
					
					disableAllFields();
					setButtonState();
					
				}
				
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
					
					new DeleteSupplier(new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()));
					resetAllFields();
					setButtonState();
					
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableOrder(){
		
		tcOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
		tcOrderRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
		tcOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		tcClerk.setCellValueFactory(new PropertyValueFactory<>("clerk"));
		tcAmountOfPositions.setCellValueFactory(new PropertyValueFactory<>("amountOfPositions"));
		tcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		tvOrder.setContextMenu(new ContextMenuTableOrder());
		
		tvOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOrder(); 
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectSupplier(int _supplierID){
		
		ModelSupplier supplier = new SelectSupplier(new ModelSupplier(_supplierID)).getModelSupplier();
			
		/* MAIN DATA */
		this.tfSupplierID.setText(String.valueOf(supplier.getSupplierID()));
		this.tfName1.setText(supplier.getName1());
		this.tfName2.setText(supplier.getName2());
		this.tfStreet.setText(supplier.getStreet());
		this.cbLand.getSelectionModel().select(supplier.getLand());
		this.tfZip.setText(String.valueOf(supplier.getZip()));
		this.tfLocation.setText(supplier.getLocation());
		
		/* CONTACT DATA */
		this.tfPhone.setText(supplier.getPhone());
		this.tfFax.setText(supplier.getFax());
		this.tfEmail.setText(supplier.getEmail());
		this.tfWeb.setText(supplier.getWeb());
		this.tfContact.setText(supplier.getContact());
		this.tfUstID.setText(supplier.getUstID());
		
		/* PAYMENT DATA */
		this.cbPayment.getSelectionModel().select(supplier.getPayment());
		this.tfIBAN.setText(supplier.getIBAN());
		this.tfBIC.setText(supplier.getBIC());
		this.tfBank.setText(supplier.getBank());
		this.tfPaymentSkonto.setText(String.valueOf(supplier.getPaymentSkonto()));
		this.tfPaymentNetto.setText(String.valueOf(supplier.getPaymentNetto()));
		this.tfSkonto.setText(String.valueOf(supplier.getSkonto()));
		
		/* NOTES */
		this.taNotes.setText(supplier.getNotes());
		
		/* TITLE */
		this.lblSubHeadline.setText("- " + supplier.getSupplierID() + " " + supplier.getName1() + ", " + supplier.getZip() + " " + supplier.getLocation());
		main.getStage().setTitle(main.getProgramName() + " - Artikelstamm " + supplier.getSupplierID() + " " + supplier.getName1() + ", " + supplier.getZip() + " " + supplier.getLocation());
		
		/* CONTACTS */
		this.contactDataController.setTableData(supplier.getObsListContacts());
		
		/* ORDER */
		selectOrder(supplier.getSupplierID());
		
		setButtonState();
		
	}
	
	private void selectOrder(int _supplierID){
		
		if(_supplierID != 0){
			tvOrder.setItems(new SelectOrder(new ModelOrder(_supplierID), OrderSelection.ALL_ORDER_TO_SUPPLIER).getModelOrder().getObsListSupplierOrder());
			
		}else{
			System.out.println("Bitte gültige Kundennummer wählen!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
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
		this.taNotes.setDisable(true);
		
	}
	
	private void enableAllFields(){
		
//		this.tfSupplierID.setDisable(false); should never be enabled!
		this.tfName1.setDisable(false);
		this.tfName2.setDisable(false);
		this.tfStreet.setDisable(false);
		this.cbLand.setDisable(false);
		this.tfZip.setDisable(false);
		this.tfLocation.setDisable(false);
		this.tfPhone.setDisable(false);
		this.tfFax.setDisable(false);
		this.tfEmail.setDisable(false);
		this.tfWeb.setDisable(false);
		this.tfContact.setDisable(false);
		this.tfUstID.setDisable(false);
		this.cbPayment.setDisable(false);
		this.tfIBAN.setDisable(false);
		this.tfBIC.setDisable(false);
		this.tfBank.setDisable(false);
		this.tfPaymentSkonto.setDisable(false);
		this.tfPaymentNetto.setDisable(false);
		this.tfSkonto.setDisable(false);
		this.taNotes.setDisable(false);
		
	}
	
	private void resetAllFields(){
		
		this.tfSupplierID.clear();
		this.tfName1.clear();
		this.tfName2.clear();
		this.tfStreet.clear();
		this.cbLand.getSelectionModel().selectFirst();
		this.tfZip.clear();
		this.tfLocation.clear();
		this.tfPhone.clear();
		this.tfFax.clear();
		this.tfEmail.clear();
		this.tfWeb.clear();
		this.tfContact.clear();
		this.tfUstID.clear();
		this.cbPayment.getSelectionModel().selectFirst();
		this.tfIBAN.clear();
		this.tfBIC.clear();
		this.tfBank.clear();
		this.tfPaymentSkonto.clear();
		this.tfPaymentNetto.clear();
		this.tfSkonto.clear();
		this.taNotes.clear();
		
	}
	
	private void setButtonState(){
		
		//Supplier is selected
		if(! tfName1.getText().isEmpty()){
			
			btnSearch.setDisable(false);
			btnNew.setDisable(false);
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
		
			//Supplier edit is active
			if(	hboxBtnTopbar.getChildren().contains(btnEditSave) &&
				hboxBtnTopbar.getChildren().contains(btnEditAbort)){
				
				btnSearch.setDisable(true);
				btnNew.setDisable(true);
				btnEdit.setDisable(true);
				btnDelete.setDisable(true);
				
				/* CONTACTS */
				contactDataController.getButtonContactAdd().setDisable(false);
				if(contactDataController.getObsListContact().size() > 0){
					contactDataController.getButtonContactEdit().setDisable(false);
					contactDataController.getButtonContactDelete().setDisable(false);
				}
				
			}else{
				
				/* CONTACTS */
				contactDataController.getButtonContactAdd().setDisable(true);
				contactDataController.getButtonContactEdit().setDisable(true);
				contactDataController.getButtonContactDelete().setDisable(true);
				
			}
			
		}else{
			
			btnSearch.setDisable(false);
			btnNew.setDisable(false);
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			
			/* CONTACTS */
			contactDataController.getButtonContactAdd().setDisable(true);
			contactDataController.getButtonContactEdit().setDisable(true);
			contactDataController.getButtonContactDelete().setDisable(true);
			
		}
		
	}
	
	private void goToOrder(){
		
		if(tvOrder.getSelectionModel().getSelectedItems().size() == 1){
			main.getContentPane().setCenter(new LoadOrderData(	false, 
																tvOrder.getItems().get(tvOrder.getSelectionModel().getSelectedIndex()).getOrderID(), 
																Integer.valueOf(tfSupplierID.getText()),
																main
											).getContent());				
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableOrder extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzufügen..");
		
		public ContextMenuTableOrder(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(tfSupplierID.getText().equals("")){
						itemNew.setDisable(true);
						itemGoTo.setDisable(true);
					}else{
						
						if(	hboxBtnTopbar.getChildren().contains(btnEditAbort) &&
							hboxBtnTopbar.getChildren().contains(btnEditSave)){
							itemNew.setDisable(true);
							itemGoTo.setDisable(true);
						}else{
						
							itemNew.setDisable(false);
							if(tvOrder.getItems().size() > 0){
								itemGoTo.setDisable(false);
							}else{
								itemGoTo.setDisable(true);
							}
						}
					}
					
				}
			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToOrder();
				}
			});
			
		}
		
		private void initItemNew(){
			
			itemNew.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					LoadOrderAdd orderAdd = new LoadOrderAdd(true, new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()), main);
					if(orderAdd.getController().getCreatedOrderID() != 0){
						selectOrder(Integer.valueOf(tfSupplierID.getText()));
					}
					
				}
			});
			
		}
		
	}
	
}

