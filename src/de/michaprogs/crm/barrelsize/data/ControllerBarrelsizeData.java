package de.michaprogs.crm.barrelsize.data;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.barrelsize.ModelBarrelsize;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerBarrelsizeData {

	//Tables & Columns
	@FXML private TableView<ModelBarrelsize> tvBarrelsize;
	@FXML private TableColumn<ModelBarrelsize, Integer> tcBarrelsizeID;
	@FXML private TableColumn<ModelBarrelsize, String> tcBarrelsize;
		
	@FXML private Label lblSubHeadline;
	
	//Buttons
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private String selectedBarrelsize = "";
	private Stage stage;
	
	public ControllerBarrelsizeData(){
		
	}
	
	@FXML private void initialize(){	
		
		/* BUTTONS */
		initBtnAbort();
		initBtnSelect();
		
		/* TABLES */
		initTableBarrelsize();
		
		//Load all barrelsize from Database and show
		refreshTablebarrelsize();
		
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
				
				if(tvBarrelsize.getSelectionModel().getSelectedItems().size() == 1){
					
					selectedBarrelsize = tvBarrelsize.getItems().get(tvBarrelsize.getSelectionModel().getSelectedIndex()).getBarrelsize();
					
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
	
	private void initTableBarrelsize(){
		
		tcBarrelsizeID.setCellValueFactory(new PropertyValueFactory<>("barrelsizeID"));
		tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));	
		
		tvBarrelsize.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelBarrelsize>() {

			@Override
			public void changed(ObservableValue<? extends ModelBarrelsize> observable, ModelBarrelsize oldValue,
					ModelBarrelsize newValue) {
				lblSubHeadline.setText(tcBarrelsize.getCellData(tvBarrelsize.getSelectionModel().getSelectedIndex()));				
			}
			
		});
		
	}
	
	private void refreshTablebarrelsize(){		
		ModelBarrelsize barrelsize = new ModelBarrelsize();
		barrelsize.selectBarrelsizes();
		tvBarrelsize.setItems(barrelsize.getObsListBarrelsizes());	
	}
	
	public String getSelectedBarrelsize(){
		return selectedBarrelsize;
	}
	
}
