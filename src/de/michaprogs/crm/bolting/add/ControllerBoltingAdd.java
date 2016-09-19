package de.michaprogs.crm.bolting.add;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.bolting.DeleteBolting;
import de.michaprogs.crm.bolting.ModelBolting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	@FXML private Button btnDelete;
	
	private Stage stage;
	
	public ControllerBoltingAdd(){
		
	}
	
	@FXML private void initialize(){
		
		initTableBolting();
		
		//Init Buttons
		initBtnDelete();
		initBtnAdd();
		
		//Load all Bolting from Database and show
		refreshTable();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnDelete(){
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
													
			}
		});	
	}
	
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				new ModelBolting().insertBolting(tfBolting.getText());
				refreshTable();
				tfBolting.clear();
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableBolting(){
		
		tcBoltingID.setCellValueFactory(new PropertyValueFactory<>("boltingID"));
		tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));	
		
		tvBolting.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelBolting>() {
			
			@Override
			public void changed(ObservableValue<? extends ModelBolting> observable, ModelBolting oldValue,
					ModelBolting newValue) {
				lblSubHeadline.setText(tcBolting.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));	
				
			}
			
		});
		
		tvBolting.setContextMenu(new ContextMenuTable());
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void refreshTable(){		
		ModelBolting bolting = new ModelBolting();
		bolting.selectBoltings();
		tvBolting.setItems(bolting.getObsListBoltings());	
	}
	
	private void deleteBolting(){
		
		if(tvBolting.getSelectionModel().getSelectedItems().size() == 1){
			if(new DeleteAlert().getDelete()){
				new DeleteBolting(tcBoltingID.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));
				refreshTable();
			}
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private class ContextMenuTable extends ContextMenu{
		
		private MenuItem itemDelete = new MenuItem("Löschen");;
		
		public ContextMenuTable(){
			
			initItemDelete();
			
			this.getItems().add(itemDelete);
			
		}
		
		private void initItemDelete(){
			
			itemDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deleteBolting();
				}
			});
			
		}
		
	}
	
}
