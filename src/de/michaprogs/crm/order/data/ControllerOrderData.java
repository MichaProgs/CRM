package de.michaprogs.crm.order.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.clerk.data.LoadClerkData;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.order.DeleteOrder;
import de.michaprogs.crm.order.ModelOrder;
import de.michaprogs.crm.order.SelectOrder;
import de.michaprogs.crm.order.SelectOrder.OrderSelection;
import de.michaprogs.crm.order.UpdateOrder;
import de.michaprogs.crm.order.ValidateOrderSave;
import de.michaprogs.crm.order.add.LoadOrderAdd;
import de.michaprogs.crm.order.search.LoadOrderSearch;
import de.michaprogs.crm.position.data.ControllerPositionData;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;
import de.michaprogs.crm.supplier.search.LoadSupplierSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControllerOrderData {

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
	
	/* ARTICLE - NESTED CONTROLLER */
	@FXML private ControllerPositionData positionDataController; //fx:id + 'Controller'
	
	/* BUTTONS */
	@FXML private Button btnSearch;
	@FXML private Button btnNew; 
	@FXML private Button btnEdit; 
		  private Button btnEditSave = new Button("Speichern"); 
		  private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	@FXML private Button btnDocument; //TODO INIT
	
	@FXML private Button btnClerkSearch; //TODO INIT
	@FXML private Button btnSupplierSearch; //TODO INIT
	
	@FXML private HBox hboxBtnTopbar;
	
	private Stage stage;
	private Main main;
	private int oldSupplierID; //if the supplierID is changed while editing the order and the edit_abort button is clicked you need the old supplierID to reload the order!
	
	public ControllerOrderData(){}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		initBtnEdit();		
		initBtnEditAbort();
		initBtnEditSave();
		initBtnDelete();
		
		initBtnClerkSearch();
		initBtnSupplierSearch();
		
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

				LoadOrderSearch orderSearch = new LoadOrderSearch(true);
				if(orderSearch.getController().getSelectedOrderID() != 0){
					selectOrder(orderSearch.getController().getSelectedOrderID(), orderSearch.getController().getSelectedOrderSupplierID());
					setButtonState();
					positionDataController.calculateTotal();
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadOrderAdd orderAdd = new LoadOrderAdd(true, 0, main);
				if(orderAdd.getController().getCreatedOrderID() != 0){
					selectOrder(orderAdd.getController().getCreatedOrderID(), orderAdd.getController().getCreatedOrderSupplierID());
					setButtonState();
					positionDataController.calculateTotal();
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
				
				enableFields();				
				setButtonState();
				positionDataController.calculateTotal();
				
			}
		});
		
	}
	
	private void initBtnEditSave(){
		
		btnEditSave.getStyleClass().add("btnTopbar");
		btnEditSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new ValidateOrderSave(	
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText()), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()),
						String.valueOf(tfOrderDate.getValue()),
						String.valueOf(tfRequestDate.getValue()),
						positionDataController.getTableArticle().getItems()).isValid()){

					new UpdateOrder(new ModelOrder(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText()), 
						String.valueOf(tfOrderDate.getValue()), 
						tfRequest.getText(), 
						String.valueOf(tfRequestDate.getValue()), 
						taNotes.getText(), 
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfClerkID.getText()),
						positionDataController.getTableArticle().getItems(),
						new BigDecimal(positionDataController.getLblTotal().getText()),
						positionDataController.getTableArticle().getItems().size()
					));
					
					if(stage != null){
						stage.close();
					}else{						
						hboxBtnTopbar.getChildren().remove(btnEditSave);
						hboxBtnTopbar.getChildren().remove(btnEditAbort);
						
						disableFields();
						setButtonState();
					}

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
						
						hboxBtnTopbar.getChildren().remove(btnEditSave);
						hboxBtnTopbar.getChildren().remove(btnEditAbort);
						
						disableFields();
						setButtonState();
						selectOrder(Integer.valueOf(tfOrderID.getText()),
									Integer.valueOf(oldSupplierID));
						
					}					
				}
				
			}
		});
		
	}
		
	private void initBtnDelete(){
		
		btnDelete.setGraphic(new GraphicButton("delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(new DeleteAlert().getDelete()){
					new DeleteOrder(
						new ModelOrder( 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText())
						)
					);
					
					resetFields();
					setButtonState();
					
				}
				
			}
		});
		
	}
	
	private void initBtnSupplierSearch(){
		
		btnSupplierSearch.setGraphic(new ImageView(new Image("file:resources/search_15_blue.png", 15, 15, true, true)));
		btnSupplierSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				oldSupplierID = Integer.valueOf(tfSupplierID.getText());
				
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
	
	/*
	 * DATABASE METHODS
	 */
	public void selectOrder(int orderID, int supplierID){
		
		ModelOrder order = new SelectOrder(new ModelOrder(orderID, supplierID), OrderSelection.SPECIFIC_ORDER).getModelOrder();		
		ModelSupplier supplier = new SelectSupplier(new ModelSupplier(order.getSupplierID())).getModelSupplier();
		ModelClerk clerk = new SelectClerk(new ModelClerk(order.getClerkID()), Selection.SELECT_SPECIFIC).getModelClerk();
		
		/* OFFER DATA */
		tfOrderID.setText(String.valueOf(order.getOrderID()));
		tfOrderDate.setValue(LocalDate.parse(order.getOrderDate()));
		tfRequest.setText(order.getRequest());
		tfRequestDate.setValue(LocalDate.parse(order.getRequestDate()));		
		taNotes.setText(order.getNotes());
		
		/* CLERK */
		tfClerkID.setText(String.valueOf(clerk.getClerkID()));
		tfClerk.setText(clerk.getName());
		tfClerkPhone.setText(clerk.getPhone());
		tfClerkFax.setText(clerk.getFax());
		tfClerkEmail.setText(clerk.getEmail());
		
		/* SUPPLIER */
		tfSupplierID.setText(String.valueOf(supplier.getSupplierID()));
		tfName1.setText(supplier.getName1());
		tfName2.setText(supplier.getName2());
		tfStreet.setText(supplier.getStreet());
		cbLand.getSelectionModel().select(supplier.getLand());
		tfZip.setText(String.valueOf(supplier.getZip()));
		tfLocation.setText(supplier.getLocation());
		
		tfPhone.setText(supplier.getPhone());
		tfFax.setText(supplier.getFax());
		tfEmail.setText(supplier.getEmail());
		tfWeb.setText(supplier.getWeb());
		tfContact.setText(supplier.getContact());
		tfUstID.setText(supplier.getUstID());
		
		cbPayment.getSelectionModel().select(supplier.getPayment());
		tfIBAN.setText(supplier.getIBAN());
		tfBIC.setText(supplier.getBIC());
		tfBank.setText(supplier.getBank());
		tfPaymentSkonto.setText(String.valueOf(supplier.getPaymentSkonto()));
		tfSkonto.setText(String.valueOf(supplier.getSkonto()));
		tfPaymentNetto.setText(String.valueOf(supplier.getPaymentNetto()));
		
		/* ARTICLE */
		positionDataController.getTableArticle().setItems(order.getObsListArticle()); 
		
	}
	
	/* 
	 * UI METHODS 
	 */ 
	private void resetFields(){
		
		tfOrderID.clear();
		tfOrderDate.setValue(LocalDate.now());
		tfRequest.clear();
		tfRequestDate.setValue(LocalDate.now());
		taNotes.clear();
		
		tfClerkID.clear();
		tfClerk.clear();
		tfClerkPhone.clear();
		tfClerkFax.clear();
		tfClerkEmail.clear();
		
		tfSupplierID.clear();
		tfName1.clear();
		tfName2.clear();
		tfStreet.clear();
		cbLand.getSelectionModel().select("");
		tfZip.clear();
		tfLocation.clear();
		
		tfPhone.clear();
		tfFax.clear();
		tfEmail.clear();
		tfWeb.clear();
		tfContact.clear();
		tfUstID.clear();
		
		cbPayment.getSelectionModel().select("");
		tfIBAN.clear();
		tfBIC.clear();
		tfBank.clear();
		tfPaymentSkonto.clear();
		tfSkonto.clear();
		tfPaymentNetto.clear();

		positionDataController.getTableArticle().setItems(null);
		
	}
	
	private void enableFields(){
		
		tfOrderDate.setDisable(false);
		tfRequest.setDisable(false);
		tfRequestDate.setDisable(false);
		taNotes.setDisable(false);
		
		btnClerkSearch.setDisable(false);
		btnSupplierSearch.setDisable(false);
				
	}
	
	private void disableFields(){
		
		tfOrderDate.setDisable(true);
		tfRequest.setDisable(true);
		tfRequestDate.setDisable(true);
		taNotes.setDisable(true);
		
		btnClerkSearch.setDisable(true);
		btnSupplierSearch.setDisable(true);
		
	}

	private void setButtonState(){
		
		if(tfOrderID.getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			btnDocument.setDisable(true);
			
			positionDataController.getBtnArticleAdd().setDisable(true);
			positionDataController.getBtnArticleEdit().setDisable(true);
			positionDataController.getBtnArticleDelete().setDisable(true);
			
		}else{
			
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			btnDocument.setDisable(false);
		
			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
				
				btnDelete.setDisable(true);
				btnNew.setDisable(true);
				btnSearch.setDisable(true);
				btnEdit.setDisable(true);
				btnDocument.setDisable(true);
				
				positionDataController.getBtnArticleAdd().setDisable(false);
				if(positionDataController.getTableArticle().getItems().size() > 0){
					positionDataController.getBtnArticleDelete().setDisable(false);
					positionDataController.getBtnArticleEdit().setDisable(false);
				}else{
					positionDataController.getBtnArticleDelete().setDisable(true);
					positionDataController.getBtnArticleEdit().setDisable(true);
				}
				
			}else{
				
				btnSearch.setDisable(false);
				btnNew.setDisable(false);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				btnDocument.setDisable(false);
				
				positionDataController.getBtnArticleAdd().setDisable(true);
				positionDataController.getBtnArticleEdit().setDisable(true);
				positionDataController.getBtnArticleDelete().setDisable(true);
				
			}
			
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
	
}
