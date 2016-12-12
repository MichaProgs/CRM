package de.michaprogs.crm.documents.order.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.documents.order.ModelOrder;
import de.michaprogs.crm.documents.order.SearchOrder;
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

public class ControllerOrderSearch {

	@FXML private Label lblSubHeadline;
	
	//Table & Columns
	@FXML private TableView<ModelOrder> tvOrderSearch;
	@FXML private TableColumn<ModelOrder, Integer> tcOrderID;
	@FXML private TableColumn<ModelOrder, String> tcOrderDate;
	@FXML private TableColumn<ModelOrder, Integer> tcSupplierID;
	@FXML private TableColumn<ModelOrder, String> tcRequest;
	@FXML private TableColumn<ModelOrder, String> tcRequestDate;
		
	//Textfields
	@FXML private TextFieldOnlyInteger tfOrderID;
	@FXML private DatePicker tfOrderDate;
	@FXML private TextFieldOnlyInteger tfSupplierID;
	@FXML private TextField tfRequest;
	@FXML private DatePicker tfRequestDate;
	@FXML private TextFieldOnlyInteger tfClerkID;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
		
	private Stage stage;
	private int selectedOrderID = 0;
	private int selectedOrderSupplierID = 0;
	
	public ControllerOrderSearch(){}
	
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
		
		/* COLUMNS */
		tcOrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		tcOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		tcSupplierID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
		tcRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
		tcRequestDate.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
		
		tvOrderSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 && 
					tvOrderSearch.getItems().size() > 0 &&
					tcOrderID.getCellData(tvOrderSearch.getSelectionModel().getSelectedIndex()) != null){
					
					select();
					
				}
			}
		});
		
		tvOrderSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
		
		tfOrderID.setOnAction(ae);
//		tfOrderDate.setOnAction(ae);
		tfSupplierID.setOnAction(ae);
		tfRequest.setOnAction(ae);
//		tfRequestDate.setOnAction(ae);
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
		ModelOrder order = 	new SearchOrder(
								new ModelOrder(
									new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfOrderID.getText()),
									String.valueOf(tfOrderDate.getValue()),
									tfRequest.getText(),
									String.valueOf(tfRequestDate.getValue()),
									new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfSupplierID.getText()),
									new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfClerkID.getText())
								)).getModelOrder();
		
		tvOrderSearch.setItems(order.getObsListOrderSearch());
		if(tvOrderSearch.getItems().size() > 0){
			tvOrderSearch.getSelectionModel().selectFirst();
			tvOrderSearch.requestFocus();
		}
		
		if(tvOrderSearch.getItems().size() == 1){
			lblSubHeadline.setText("(" + String.valueOf(tvOrderSearch.getItems().size()) + " Suchergebnis)" );
		}else{
			lblSubHeadline.setText("(" + String.valueOf(tvOrderSearch.getItems().size()) + " Suchergebnisse)" );
		}
		
	}
	
	private void select(){
		
		if(tvOrderSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			
			selectedOrderID = tvOrderSearch.getItems().get(tvOrderSearch.getSelectionModel().getSelectedIndex()).getOrderID();
			selectedOrderSupplierID = tvOrderSearch.getItems().get(tvOrderSearch.getSelectionModel().getSelectedIndex()).getSupplierID();
			
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
		
		tfOrderID.clear();
		tfOrderDate.setValue(null);
		tfSupplierID.clear();
		tfRequest.clear();
		tfRequestDate.setValue(null);
		
		tvOrderSearch.getItems().clear();
		lblSubHeadline.setText("");
				
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getSelectedOrderID(){
		return selectedOrderID;
	}
	
	public int getSelectedOrderSupplierID(){
		return selectedOrderSupplierID;
	}
	
}
