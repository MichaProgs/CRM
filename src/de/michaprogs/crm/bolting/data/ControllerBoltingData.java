package de.michaprogs.crm.bolting.data;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.bolting.ModelBolting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBoltingData {

	//Tables & Columns
	@FXML private TableView<ModelBolting> tvBolting;
	@FXML private TableColumn<ModelBolting, String> tcBoltingID;
	@FXML private TableColumn<ModelBolting, String> tcBolting;
	
	@FXML private Label lblSubHeadline;
	
	//Buttons
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private String selectedBolting = "";
	private Stage stage;
	
	public ControllerBoltingData(){
		
	}
	
	@FXML private void initialize(){
		
		this.tcBoltingID.setCellValueFactory(new PropertyValueFactory<>("boltingID"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));		
		
		initTableDoubleClick();
		
		//Init Buttons
		initBtnSelect();
		initBtnAbort();
		
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
		
		btnSelect.setGraphic(new GraphicButton("file:resources/select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(tvBolting.getSelectionModel().getSelectedItems().size() == 1){
					
					selectedBolting = tvBolting.getItems().get(tvBolting.getSelectionModel().getSelectedIndex()).getBolting();
					
					if(stage != null){
						stage.close();
					}
					
				}else{
					System.out.println("Bitte 1 Zeile markieren!");
				}
				
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("file:resources/cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null){
					stage.close();
				}									
			}
		});	
	}
	
	private void initTableDoubleClick(){
		
		tvBolting.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 &&
					event.getButton().equals(MouseButton.PRIMARY)){
					
					selectedBolting = tvBolting.getItems().get(tvBolting.getSelectionModel().getSelectedIndex()).getBolting();
					
					if(stage != null){
						stage.close();
					}
					
				}
				
				if(tvBolting.getSelectionModel().getSelectedItems().size() == 1){
					lblSubHeadline.setText(tvBolting.getItems().get(tvBolting.getSelectionModel().getSelectedIndex()).getBolting());
				}
				
			}
		});
		
	}
	
	private void refreshTableBolting(){		
		ModelBolting bolting = new ModelBolting();
		bolting.selectBoltings();
		tvBolting.setItems(bolting.getObsListBoltings());	
	}
	
	public String getSelectedBolting(){
		return selectedBolting;
	}
	
}
