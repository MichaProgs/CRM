package de.michaprogs.crm.article.barrelsize.add;

import de.michaprogs.crm.article.barrelsize.ModelBarrelsize;
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

public class ControllerBarrelsizeAdd {

	//Tables & Columns
	@FXML private TableView<ModelBarrelsize> tvBarrelsize;
	@FXML private TableColumn<ModelBarrelsize, String> tcBarrelsizeID;
	@FXML private TableColumn<ModelBarrelsize, String> tcBarrelsize;
	
	//TextFields
	@FXML private TextField tfBarrelsize;
	
	//Buttons
	@FXML private Button btnAdd;
	@FXML private Button btnAbort;
	
	private String selectedBarrelsize = "";
	private Stage stage;
	
	public ControllerBarrelsizeAdd(){
		
	}
	
	@FXML private void initialize(){
		
		this.tcBarrelsizeID.setCellValueFactory(new PropertyValueFactory<>("barrelsizeID"));
		this.tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));		
		
		initTableDoubleClick();
		
		//Init Buttons
		initBtnAbort();
		initBtnAdd();
		
		//Load all barrelsize from Database and show
		refreshTablebarrelsize();
		
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
				
				new ModelBarrelsize().insertbarrelsize(tfBarrelsize.getText());
				refreshTablebarrelsize();
				tfBarrelsize.clear();
				
			}
		});
		
	}
	
	private void initTableDoubleClick(){
		
		tvBarrelsize.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 2){
					
					selectedBarrelsize = tcBarrelsize.getCellData(tvBarrelsize.getSelectionModel().getSelectedIndex());
					
					if(stage != null)
						stage.close();
					
				}
				
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
