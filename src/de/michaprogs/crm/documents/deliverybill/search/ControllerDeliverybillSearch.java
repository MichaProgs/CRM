package de.michaprogs.crm.documents.deliverybill.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.documents.deliverybill.SearchDeliverybill;
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

public class ControllerDeliverybillSearch {

	@FXML private Label lblSubHeadline;
	
	//Table & Columns
	@FXML private TableView<ModelDeliverybill> tvDeliverybillSearch;
	@FXML private TableColumn<ModelDeliverybill, Integer> tcDeliverybillID;
	@FXML private TableColumn<ModelDeliverybill, String> tcDeliverybillDate;
	@FXML private TableColumn<ModelDeliverybill, Integer> tcCustomerID;
	@FXML private TableColumn<ModelDeliverybill, String> tcRequest;
	@FXML private TableColumn<ModelDeliverybill, String> tcRequestDate;
		
	//Textfields
	@FXML private TextFieldOnlyInteger tfDeliverybillID;
	@FXML private DatePicker tfDeliverybillDate;
	@FXML private TextFieldOnlyInteger tfCustomerID;
	@FXML private TextField tfRequest;
	@FXML private DatePicker tfRequestDate;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	private Stage stage;
	private int selectedDeliverybillID = 0;
	private int selectedDeliverybillCustomerID = 0;
	
	public ControllerDeliverybillSearch(){}
	
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
		this.tcDeliverybillID.setCellValueFactory(new PropertyValueFactory<>("deliverybillID"));
		this.tcDeliverybillDate.setCellValueFactory(new PropertyValueFactory<>("deliverybillDate"));
		this.tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		this.tcRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
		this.tcRequestDate.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
		
		tvDeliverybillSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 && 
					tvDeliverybillSearch.getItems().size() > 0 &&
					tcDeliverybillID.getCellData(tvDeliverybillSearch.getSelectionModel().getSelectedIndex()) != null){
					
					select();
					
				}
			}
		});
		
		tvDeliverybillSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
		
		tfDeliverybillID.setOnAction(ae);
//		tfDeliverybillDate.setOnAction(ae);
		tfCustomerID.setOnAction(ae);
		tfRequest.setOnAction(ae);
//		tfRequestDate.setOnAction(ae);
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
		SearchDeliverybill search = new SearchDeliverybill(
			tfDeliverybillID.getText(),
			String.valueOf(tfDeliverybillDate.getValue()), 
			tfCustomerID.getText(),
			tfRequest.getText(),
			String.valueOf(tfRequestDate.getValue())
		);
		
		tvDeliverybillSearch.setItems(search.getObsListSearch());
		if(tvDeliverybillSearch.getItems().size() > 0){
			tvDeliverybillSearch.getSelectionModel().selectFirst();
			tvDeliverybillSearch.requestFocus();
		}
		
		if(tvDeliverybillSearch.getItems().size() == 1){
			lblSubHeadline.setText("(" + String.valueOf(tvDeliverybillSearch.getItems().size()) + " Suchergebnis)" );
		}else{
			lblSubHeadline.setText("(" + String.valueOf(tvDeliverybillSearch.getItems().size()) + " Suchergebnisse)" );
		}
		
	}
	
	private void select(){
		
		if(tvDeliverybillSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			
			selectedDeliverybillID = tvDeliverybillSearch.getItems().get(tvDeliverybillSearch.getSelectionModel().getSelectedIndex()).getDeliverybillID();
			selectedDeliverybillCustomerID = tvDeliverybillSearch.getItems().get(tvDeliverybillSearch.getSelectionModel().getSelectedIndex()).getCustomerID();
			
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
		
		this.tfDeliverybillID.clear();
		this.tfDeliverybillDate.setValue(null);
		this.tfCustomerID.clear();
		this.tfRequest.clear();
		this.tfRequestDate.setValue(null);
		
		this.tvDeliverybillSearch.getItems().clear();
		this.lblSubHeadline.setText("");
				
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/**
	 * @return Returns the ArticleID which was selected in the table
	 */
	public int getSelectedDeliverybillID(){
		return selectedDeliverybillID;
	}
	
	public int getSelectedDeliverybillCustomerID(){
		return selectedDeliverybillCustomerID;
	}
	
}
