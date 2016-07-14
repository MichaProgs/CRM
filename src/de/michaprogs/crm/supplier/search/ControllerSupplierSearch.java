package de.michaprogs.crm.supplier.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.supplier.ModelSupplier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerSupplierSearch {

	@FXML private TextFieldOnlyInteger tfSupplierID;
	@FXML private TextField tfName1;
	@FXML private TextField tfName2;
	@FXML private TextField tfStreet;
	@FXML private ComboBox<String> cbLand;
	@FXML private TextFieldOnlyInteger tfZip;
	@FXML private TextField tfLocation;
	@FXML private TextField tfPhone;
	
	//Tables & Columns
	@FXML private TableView<ModelSupplier> tvSupplierSearch;
	@FXML private TableColumn<ModelSupplier, Integer> tcSupplierID;
	@FXML private TableColumn<ModelSupplier, String> tcName1;
	@FXML private TableColumn<ModelSupplier, String> tcName2;
	@FXML private TableColumn<ModelSupplier, String> tcStreet;
	@FXML private TableColumn<ModelSupplier, String> tcLand;
	@FXML private TableColumn<ModelSupplier, Integer> tcZip;
	@FXML private TableColumn<ModelSupplier, String> tcLocation;
	@FXML private TableColumn<ModelSupplier, String> tcPhone;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	//Panels & Nodes
	
	private Stage stage;
	private int selectedSupplierID = 0;
	
	public ControllerSupplierSearch(){
		
	}
	
	@FXML private void initialize(){
		
		tfSupplierID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		tfZip.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		initTable();
		initTextFields();
		
		//ComboBoxes
		new InitCombos().initComboLand(cbLand);
		
		//Buttons
		initBtnSearch();
		initBtnReset();
		initBtnSelect();
		initBtnAbort();
		
	}
	
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("file:resources/search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				search();				
			}
		});
		
	}
	
	private void initBtnSelect(){
		
		btnSelect.setGraphic(new GraphicButton("file:resources/select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				select();
			}
		});
		
	}
	
	private void initBtnReset(){
		
		btnReset.setGraphic(new GraphicButton("file:resources/clear_32.png").getGraphicButton());
		btnReset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("file:resources/cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						reset();
					}
				}
				
			}
		});
		
	}
	
	private void initTable(){
		
		this.tcSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
		this.tcName1.setCellValueFactory(new PropertyValueFactory<>("name1"));
		this.tcName2.setCellValueFactory(new PropertyValueFactory<>("name2"));
		this.tcStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
		this.tcLand.setCellValueFactory(new PropertyValueFactory<>("land"));
		this.tcZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
		this.tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
		this.tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		tvSupplierSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				//Double Left Click
				if(	event.getButton().equals(MouseButton.PRIMARY) &&
					event.getClickCount() == 2){
					select();
				}
				
			}
		});
		
		tvSupplierSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					select();
				}
				
			}
		});
		
	}
	
	private void initTextFields(){
		
		EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					search();
				}
				
			}
		};
		
		tfSupplierID.setOnKeyPressed(ke);
		tfName1.setOnKeyPressed(ke);
		tfName2.setOnKeyPressed(ke);
		tfStreet.setOnKeyPressed(ke);
		tfZip.setOnKeyPressed(ke);
		tfLocation.setOnKeyPressed(ke);
		tfPhone.setOnKeyPressed(ke);
		
	}
	
	private void search(){
		
		ModelSupplier supplier = new ModelSupplier();
		supplier.searchSupplier(
			tfSupplierID.getText(), 
			tfName1.getText(), 
			tfName2.getText(), 
			tfStreet.getText(), 
			cbLand.getSelectionModel().getSelectedItem(), 
			tfZip.getText(), 
			tfLocation.getText(),
			tfPhone.getText()
		);
		
		tvSupplierSearch.setItems(supplier.getObsListSearch());
		if(tvSupplierSearch.getItems().size() > 0){
			tvSupplierSearch.getSelectionModel().selectFirst();
			tvSupplierSearch.requestFocus();
		}
		
	}
	
	private void select(){
		
		if(tvSupplierSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			selectedSupplierID = tvSupplierSearch.getItems().get(tvSupplierSearch.getSelectionModel().getSelectedIndex()).getSupplierID();
			
			if(stage != null){
				stage.close();
			}else{
				reset();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	private void reset(){
		
		this.tfSupplierID.clear();
		this.tfName1.clear();
		this.tfName2.clear();
		this.tfStreet.clear();
		this.cbLand.getSelectionModel().selectFirst();
		this.tfZip.clear();
		this.tfLocation.clear();
		this.tfPhone.clear();
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getSelectedSupplierID(){
		return selectedSupplierID;
	}
	
	
	
}

