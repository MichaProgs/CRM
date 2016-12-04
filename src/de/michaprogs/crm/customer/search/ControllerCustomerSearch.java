package de.michaprogs.crm.customer.search;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SearchCustomer;
import de.michaprogs.crm.customer.category.ModelCustomerCategory;
import de.michaprogs.crm.customer.category.SelectCustomerCategory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerCustomerSearch {

	@FXML private Label lblSubHeadline;
	
	@FXML private TextField tfCustomerID;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextField tfZip;
	@FXML private TextField tfLocation;
	@FXML private TextField tfPhone;
	@FXML private TextField tfMobile;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private ComboBox<String> cbCategory;
	
	@FXML private TableView<ModelCustomer> tvCustomerSearch;
	@FXML private TableColumn<ModelCustomer, Integer> tcCustomerID;
	@FXML private TableColumn<ModelCustomer, String> tcName1;
	@FXML private TableColumn<ModelCustomer, String> tcName2;
	@FXML private TableColumn<ModelCustomer, String> tcStreet;
	@FXML private TableColumn<ModelCustomer, String> tcLand;
	@FXML private TableColumn<ModelCustomer, String> tcZip;
	@FXML private TableColumn<ModelCustomer, String> tcLocation;
	@FXML private TableColumn<ModelCustomer, String> tcPhone;
 	
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private int selectedCustomerID = 0;
	
	public ControllerCustomerSearch(){}
	
	@FXML private void initialize(){
		
		new InitCombos().initComboLand(cbLand);
		
		cbCategory.setItems(new SelectCustomerCategory(new ModelCustomerCategory()).getModelCustomerCategory().getObsListCustomerCategoriesComboBox());
		
		tfCustomerID.setText("");
		tfZip.setText("");
		
		initBtnSearch();
		initBtnReset();
		initBtnSelect();
		initBtnAbort();
		
		initTextFields();
		
		initTableCustomerSearch();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				search();
			}
		});
		
	}
	
	private void initBtnReset(){
		
		btnReset.setGraphic(new GraphicButton("clear_32.png").getGraphicButton());
		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				reset();
			}
		});
		
	}
	
	private void initBtnSelect(){
		
		btnSelect.setGraphic(new GraphicButton("select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				select();
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null){
					stage.close();
				}else{
					reset();
				}
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableCustomerSearch(){
		
		this.tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		this.tcName1.setCellValueFactory(new PropertyValueFactory<>("name1"));
		this.tcName2.setCellValueFactory(new PropertyValueFactory<>("name2"));
		this.tcStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
		this.tcLand.setCellValueFactory(new PropertyValueFactory<>("land"));
		this.tcZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
		this.tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
		this.tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		tvCustomerSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				//Double Left Click
				if(	event.getButton().equals(MouseButton.PRIMARY) &&
					event.getClickCount() == 2){
					select();
				}
				
			}
		});
		
		tvCustomerSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					select();
				}
				
			}
		});
		
	}
	
	/*
	 * TEXTFIELDS
	 */
	private void initTextFields(){
		
		EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					search();
				}
				
			}
		};
		
		tfCustomerID.setOnKeyPressed(ke);
		tfName1.setOnKeyPressed(ke);
		tfName2.setOnKeyPressed(ke);
		tfStreet.setOnKeyPressed(ke);
		tfZip.setOnKeyPressed(ke);
		tfLocation.setOnKeyPressed(ke);
		tfPhone.setOnKeyPressed(ke);
		tfMobile.setOnKeyPressed(ke);
		tfFax.setOnKeyPressed(ke);
		tfEmail.setOnKeyPressed(ke);
		
	}
	
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
		SearchCustomer searchCustomer = new SearchCustomer(
			tfCustomerID.getText(), 
			tfName1.getText(), 
			tfName2.getText(), 
			tfStreet.getText(), 
			cbLand.getSelectionModel().getSelectedItem(), 
			tfZip.getText(), 
			tfLocation.getText(), 
			tfPhone.getText(), 
			tfMobile.getText(), 
			tfFax.getText(), 
			tfEmail.getText(),
			cbCategory.getSelectionModel().getSelectedItem()
		);
		
		tvCustomerSearch.setItems(searchCustomer.getObsListSearch());
		if(tvCustomerSearch.getItems().size() > 0){
			tvCustomerSearch.getSelectionModel().selectFirst();
			tvCustomerSearch.requestFocus();
		}
		
		if(tvCustomerSearch.getItems().size() == 1){
			lblSubHeadline.setText("(" + String.valueOf(tvCustomerSearch.getItems().size()) + " Suchergebnis)" );
		}else{
			lblSubHeadline.setText("(" + String.valueOf(tvCustomerSearch.getItems().size()) + " Suchergebnisse)" );
		}
		
	}
	
	private void select(){
		
		if(tvCustomerSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			selectedCustomerID = tvCustomerSearch.getItems().get(tvCustomerSearch.getSelectionModel().getSelectedIndex()).getCustomerID();
			
			if(stage != null){
				stage.close();
			}else{
				reset();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void reset(){
		
		tfCustomerID.clear();
		tfName1.clear();
		tfName2.clear();
		tfStreet.clear();
		cbLand.getSelectionModel().select("");
		tfZip.clear();
		tfLocation.clear();
		tfPhone.clear();
		tfMobile.clear();
		tfFax.clear();
		tfEmail.clear();
		cbCategory.getSelectionModel().select("");
		
		tvCustomerSearch.getItems().clear();
		
		lblSubHeadline.setText("");
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getSelectedCustomerID(){
		return selectedCustomerID;
	}
	
}
