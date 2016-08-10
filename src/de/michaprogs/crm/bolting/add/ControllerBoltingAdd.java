package de.michaprogs.crm.bolting.add;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.bolting.ModelBolting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBoltingAdd {

	//Tables & Columns
	@FXML private TableView<ModelBolting> tvBolting;
	@FXML private TableColumn<ModelBolting, Integer> tcBoltingID;
	@FXML private TableColumn<ModelBolting, String> tcBolting;
	
	@FXML private Label lblSubHeadline;
	
	//TextFields
	@FXML private TextField tfBolting;
	
	//Buttons
	@FXML private Button btnAdd;
	@FXML private Button btnAbort;
	
	private Stage stage;
	
	public ControllerBoltingAdd(){
		
	}
	
	@FXML private void initialize(){
		
		this.tcBoltingID.setCellValueFactory(new PropertyValueFactory<>("boltingID"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));		
		
		initTableBolting();
		
		//Init Buttons
		initBtnAbort();
		initBtnAdd();
		
		//Load all Bolting from Database and show
		refreshTableBolting();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnAbort(){
		
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null){
					stage.close();
				}else{
					tfBolting.clear();
				}
									
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
	
	/*
	 * TABLES
	 */
	private void initTableBolting(){
		
		tvBolting.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getButton().equals(MouseButton.SECONDARY) &&
					event.getClickCount() == 2){
					tvBolting.setContextMenu(new ContextMenuTable());				
				}
				
				if(tvBolting.getSelectionModel().getSelectedItems().size() == 1){
					lblSubHeadline.setText(tcBolting.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void refreshTableBolting(){		
		ModelBolting bolting = new ModelBolting();
		bolting.selectBoltings();
		tvBolting.setItems(bolting.getObsListBoltings());	
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private class ContextMenuTable extends ContextMenu{
		
		private MenuItem itemDelete;
		
		public ContextMenuTable(){
			
			initItemDelete();
			
			this.getItems().add(itemDelete);
			
		}
		
		private void initItemDelete(){
			
			itemDelete = new MenuItem("Löschen");
			itemDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					DeleteAlert delete = new DeleteAlert();
					if(delete.getDelete()){
						ModelBolting bolting = new ModelBolting();
						bolting.deleteBolting(	tcBoltingID.getCellData(tvBolting.getSelectionModel().getSelectedIndex()), 
												tcBolting.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));
					
						refreshTableBolting();
					
					}
					
				}
			});
			
		}
		
	}
	
}
