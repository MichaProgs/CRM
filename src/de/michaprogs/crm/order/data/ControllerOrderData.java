package de.michaprogs.crm.order.data;

import java.time.LocalDate;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.order.ModelOrder;
import de.michaprogs.crm.order.SelectOrder;
import de.michaprogs.crm.order.add.LoadOrderAdd;
import de.michaprogs.crm.order.search.LoadOrderSearch;
import de.michaprogs.crm.supplier.ModelSupplier;
import de.michaprogs.crm.supplier.SelectSupplier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	@FXML private Button btnSearch;
	@FXML private Button btnNew; 
	@FXML private Button btnEdit; //TODO INIT
	@FXML private Button btnDelete; //TODO INIT
	@FXML private Button btnDocument; //TODO INIT
	
	
	private Stage stage;
	private Main main;
	
	public ControllerOrderData(){}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		
		/* TABLES */
		initTableArticle();
		
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
					selectOrder(orderSearch.getController().getSelectedOrderID());
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadOrderAdd orderAdd = new LoadOrderAdd(true, main);
				if(orderAdd.getController().getCreatedOrderID() != 0){
					selectOrder(orderAdd.getController().getCreatedOrderID());
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
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
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectOrder(int orderID){
		
		ModelOrder order = new SelectOrder(new ModelOrder(orderID)).getModelOrder();		
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
		tvArticle.setItems(order.getObsListArticle());
		
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
