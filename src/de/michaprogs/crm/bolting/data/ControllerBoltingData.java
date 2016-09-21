package de.michaprogs.crm.bolting.data;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.barrelsize.ModelBarrelsize;
import de.michaprogs.crm.bolting.ModelBolting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBoltingData {

	@FXML private TextField tfFilter;
	
	//Tables & Columns
	      private ObservableList<ModelBolting> obsListBolting = FXCollections.observableArrayList();
	@FXML private TableView<ModelBolting> tvBolting;
	@FXML private TableColumn<ModelBolting, String> tcBoltingID;
	@FXML private TableColumn<ModelBolting, String> tcBolting;
	
	@FXML private Label lblSubHeadline;
	
	//Buttons
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private String selectedBolting = "";
	private Stage stage;
	
	public ControllerBoltingData(){}
	
	@FXML private void initialize(){	
		
		/* TABLES */
		initTableBolting();
		
		/* BUTTONS */
		initBtnSelect();
		initBtnAbort();
		
		/* TEXTFIELDS */
		initTfFilter();
		
		//Load all Bolting from Database and show
		refreshTableBolting();
		
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSelect(){
		
		btnSelect.setGraphic(new GraphicButton("select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				selectBolting();
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
				}									
			}
		});	
	}
	
	/*
	 * TABLES
	 */
	private void initTableBolting(){
		
		tcBoltingID.setCellValueFactory(new PropertyValueFactory<>("boltingID"));
		tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));	
		
		tvBolting.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode().equals(KeyCode.ENTER)){
					selectBolting();
				}
				
			}
		});
		
		tvBolting.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					selectBolting();
				}
				
			}
		});
		
		tvBolting.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelBolting>() {
			
			@Override
			public void changed(ObservableValue<? extends ModelBolting> observable, ModelBolting oldValue,
					ModelBolting newValue) {
				lblSubHeadline.setText(tcBolting.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));	
				
			}
			
		});
		
	}
	
	/*
	 * TEXTFIELDS
	 */
	private void initTfFilter(){
		
		tfFilter.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				FilteredList<ModelBolting> filteredList = new FilteredList<>(obsListBolting);
				filteredList.setPredicate(barrelsize ->{
					
					if(tfFilter.getText().isEmpty() || tfFilter.getText() == null){
						return true;
					}else if(barrelsize.getBolting().toLowerCase().contains(tfFilter.getText().toLowerCase())){
						return true;
					}
					
					return false;
					
				});
				
				tvBolting.setItems(filteredList);
				
			}
		});
		
	}
	
	/*
	 * DATABASE
	 */
	private void selectBolting(){
		
		if(tvBolting.getSelectionModel().getSelectedItems().size() == 1){
			
			selectedBolting = tvBolting.getItems().get(tvBolting.getSelectionModel().getSelectedIndex()).getBolting();
			
			if(stage != null){
				stage.close();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void refreshTableBolting(){		
		
		ModelBolting bolting = new ModelBolting();
		bolting.selectBoltings();
		obsListBolting = bolting.getObsListBoltings();
		tvBolting.setItems(bolting.getObsListBoltings());	
		
		if(tvBolting.getItems().size() > 0){
			tvBolting.getSelectionModel().selectFirst();
			tvBolting.requestFocus();
		}
		
	}
	
	public String getSelectedBolting(){
		return selectedBolting;
	}
	
}
