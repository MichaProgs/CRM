package de.michaprogs.crm.properties;

import de.michaprogs.crm.article.barrelsize.add.LoadBarrelsizeAdd;
import de.michaprogs.crm.article.bolting.add.LoadBoltingAdd;
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
	
	private Stage stage;
	
	public ControllerProperties(){}
	
	@FXML private void initialize(){
	
		/* BUTTONS */
		initBtnBolting();
		initBtnBarrelsize();
		
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
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
