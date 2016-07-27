package de.michaprogs.crm.article.bolting.add;

import de.michaprogs.crm.article.bolting.ModelBolting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBoltingAdd {

	//Tables & Columns
	@FXML private TableView<ModelBolting> tvBolting;
	@FXML private TableColumn<ModelBolting, String> tcBoltingID;
	@FXML private TableColumn<ModelBolting, String> tcBolting;
	
	//TextFields
	@FXML private TextField tfBolting;
	
	//Buttons
	@FXML private Button btnAdd;
	@FXML private Button btnAbort;
	
	private String selectedBolting = "";
	private Stage stage;
	
	public ControllerBoltingAdd(){
		
	}
	
	@FXML private void initialize(){
		
		this.tcBoltingID.setCellValueFactory(new PropertyValueFactory<>("boltingID"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));		
		
		initTableDoubleClick();
		
		//Init Buttons
		initBtnAbort();
		initBtnAdd();
		
		//Load all Bolting from Database and show
		refreshTableBolting();
		
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void initBtnAbort(){
		
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null)
					stage.close();				
			}
		});	
	}
	
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				new ModelBolting().insertBolting(tfBolting.getText());
				refreshTableBolting();
				tfBolting.clear();
				
			}
		});
		
	}
	
	private void initTableDoubleClick(){
		
		tvBolting.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 2){
					selectedBolting = tcBolting.getCellData(tvBolting.getSelectionModel().getSelectedIndex());
					if(stage != null)
						stage.close();
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
