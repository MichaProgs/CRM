package de.michaprogs.crm.properties;

import de.michaprogs.crm.barrelsize.add.LoadBarrelsizeAdd;
import de.michaprogs.crm.bolting.add.LoadBoltingAdd;
import de.michaprogs.crm.clerk.add.LoadClerkAdd;
import de.michaprogs.crm.warehouse.add.LoadWarehouseAdd;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControllerProperties {

	@FXML private BorderPane content;
	
	@FXML private Button btnBolting;
	@FXML private Button btnBarrelsize;
	@FXML private Button btnWarehouse;
	@FXML private Button btnClerk;
	
	private Stage stage;
	
	public ControllerProperties(){}
	
	@FXML private void initialize(){
	
		content.setCenter(new LoadBoltingAdd(false).getContent());
		
		/* BUTTONS */
		initBtnBolting();
		initBtnBarrelsize();
		initBtnWarehouse();
		initBtnClerk();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnBolting(){
		
		btnBolting.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {				
				content.setCenter(new LoadBoltingAdd(false).getContent());				
			}
		});
		
	}
	
	private void initBtnBarrelsize(){
		
		btnBarrelsize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(new LoadBarrelsizeAdd(false).getContent());
			}
		});
		
	}
	
	private void initBtnWarehouse(){
		
		btnWarehouse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(new LoadWarehouseAdd(false).getContent());
			}
		});
		
	}
	
	private void initBtnClerk(){
		
		btnClerk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(new LoadClerkAdd(false).getContent());
			}
		});
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
