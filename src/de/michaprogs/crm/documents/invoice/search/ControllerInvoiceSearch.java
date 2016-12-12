package de.michaprogs.crm.documents.invoice.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.documents.invoice.ModelInvoice;
import de.michaprogs.crm.documents.invoice.SearchInvoice;
import de.michaprogs.crm.documents.offer.ModelOffer;
import de.michaprogs.crm.documents.offer.SearchOffer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerInvoiceSearch {

	@FXML private Label lblSubHeadline;
	
	//Table & Columns
	@FXML private TableView<ModelInvoice> tvInvoiceSearch;
	@FXML private TableColumn<ModelInvoice, Integer> tcInvoiceID;
	@FXML private TableColumn<ModelInvoice, String> tcInvoiceDate;
	@FXML private TableColumn<ModelInvoice, Integer> tcCustomerID;
	@FXML private TableColumn<ModelInvoice, String> tcDeliverybillID;
	@FXML private TableColumn<ModelInvoice, String> tcDeliveryDate;
		
	//Textfields
	@FXML private TextFieldOnlyInteger tfInvoiceID;
	@FXML private DatePicker tfInvoiceDate;
	@FXML private TextFieldOnlyInteger tfCustomerID;
	@FXML private TextField tfDeliverybillID;
	@FXML private DatePicker tfDeliveryDate;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	private Stage stage;
	private int selectedInvoiceID = 0;
	private int selectedInvoiceCustomerID = 0;
	
	public ControllerInvoiceSearch(){}
	
	@FXML private void initialize(){
		
		initTable();
		initTextFields();
		
		//Buttons
		initBtnSearch();
		initBtnReset();
		initBtnAbort();
		initBtnSelect();
		
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
				resetAllFields();
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
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						resetAllFields();
					}
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTable(){
		
		//Table & Columns
		this.tcInvoiceID.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
		this.tcInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
		this.tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		this.tcDeliverybillID.setCellValueFactory(new PropertyValueFactory<>("deliverybillID"));
		this.tcDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
		
		tvInvoiceSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 && 
					tvInvoiceSearch.getItems().size() > 0 &&
					tcInvoiceID.getCellData(tvInvoiceSearch.getSelectionModel().getSelectedIndex()) != null){
					
					select();
					
				}
			}
		});
		
		tvInvoiceSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
		
		EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				search();
			}
		}; 
		
		tfInvoiceID.setOnAction(ae);
//		tfOfferDate.setOnAction(ae);
		tfCustomerID.setOnAction(ae);
		tfDeliverybillID.setOnAction(ae);
//		tfRequestDate.setOnAction(ae);
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
		ModelInvoice invoice = new SearchInvoice(new ModelInvoice(
			new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfInvoiceID.getText()),
			String.valueOf(tfInvoiceDate.getValue()), 
			new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfCustomerID.getText()),
			new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfDeliverybillID.getText()),
			String.valueOf(tfDeliveryDate.getValue())
		)).getModelInvoice();
		
		tvInvoiceSearch.setItems(invoice.getObsListInvoiceSearch());
		if(tvInvoiceSearch.getItems().size() > 0){
			tvInvoiceSearch.getSelectionModel().selectFirst();
			tvInvoiceSearch.requestFocus();
		}
		
		if(tvInvoiceSearch.getItems().size() == 1){
			lblSubHeadline.setText("(" + String.valueOf(tvInvoiceSearch.getItems().size()) + " Suchergebnis)" );
		}else{
			lblSubHeadline.setText("(" + String.valueOf(tvInvoiceSearch.getItems().size()) + " Suchergebnisse)" );
		}
		
	}
	
	private void select(){
		
		if(tvInvoiceSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			
			selectedInvoiceID = tvInvoiceSearch.getItems().get(tvInvoiceSearch.getSelectionModel().getSelectedIndex()).getInvoiceID();
			selectedInvoiceCustomerID = tvInvoiceSearch.getItems().get(tvInvoiceSearch.getSelectionModel().getSelectedIndex()).getCustomerID();
			
			if(stage != null){
				stage.close();
			}else{
				resetAllFields();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetAllFields(){
		
		this.tfInvoiceID.clear();
		this.tfInvoiceDate.setValue(null);
		this.tfCustomerID.clear();
		this.tfDeliverybillID.clear();
		this.tfDeliveryDate.setValue(null);
		
		this.tvInvoiceSearch.getItems().clear();
		this.lblSubHeadline.setText("");
				
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/**
	 * @return Returns the InvoiceID which was selected in the table
	 */
	public int getSelectedInvoiceID(){
		return selectedInvoiceID;
	}
	
	/**
	 * @return Returns the customerID to the selected InvoiceID
	 */
	public int getSelectedInvoiceCustomerID(){
		return selectedInvoiceCustomerID;
	}
	
}
