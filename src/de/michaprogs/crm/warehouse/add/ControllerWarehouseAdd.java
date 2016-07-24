package de.michaprogs.crm.warehouse.add;

import de.michaprogs.crm.warehouse.ModelWarehouse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerWarehouseAdd {

	@FXML private TableView<ModelWarehouse> tvWarehouse;
	@FXML private TableColumn<ModelWarehouse, Integer> tcWarehouseID;
	@FXML private TableColumn<ModelWarehouse, String> tcWarehouse;
	
	@FXML private TextField tfWarehouse;
	
	@FXML private Button btnAdd;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private int selectedWarehouseID = 0;
	
	public ControllerWarehouseAdd(){
		
	}
	
	@FXML private void initialize(){
		
		initButtonAdd();
		initTableWarehouse();
		
		selectWarehouses();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initButtonAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				new ModelWarehouse().insertWarehouse(tfWarehouse.getText());
				tfWarehouse.clear();
				selectWarehouses();
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableWarehouse(){
		
		this.tcWarehouseID.setCellValueFactory(new PropertyValueFactory<>("warehouseID"));
		this.tcWarehouse.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
		
		tvWarehouse.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getButton().equals(MouseButton.PRIMARY) &&
					event.getClickCount() == 2){
					if(tvWarehouse.getSelectionModel().getSelectedItems().size() == 1){
						selectedWarehouseID = tvWarehouse.getItems().get(tvWarehouse.getSelectionModel().getSelectedIndex()).getWarehouseID();
						if(stage != null){
							stage.close();
						}
					}else{
						System.out.println("Bitte 1 Zeile markieren!");
					}
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectWarehouses(){
		
		ModelWarehouse warehouse = new ModelWarehouse();
		warehouse.selectWarehouses();
		tvWarehouse.setItems(warehouse.getObsListWarehouses());
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getSelectedWarehouseID(){
		return selectedWarehouseID;
	}
	
}
