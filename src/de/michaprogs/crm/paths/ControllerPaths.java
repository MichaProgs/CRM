package de.michaprogs.crm.paths;

import java.io.File;

import de.michaprogs.crm.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ControllerPaths {
	
	@FXML private TextField tfPathOfferTemplate;
	@FXML private TextField tfPathOfferSaving;
	
	@FXML private Button btnPathOfferTemplate;
	@FXML private Button btnPathOfferSaving;		
	
	private Stage stage;
	private Main main;
	
	public ControllerPaths(){}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnOfferTemplate();
		initBtnOfferSaving();
		
		tfPathOfferSaving.setText(new PathProperties().loadProperty(PathProperties.KEY_OFFER_SAVING));
		tfPathOfferTemplate.setText(new PathProperties().loadProperty(PathProperties.KEY_OFFER_TEMPLATE));
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnOfferTemplate(){
		
		btnPathOfferTemplate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fc = new FileChooser();
				fc.getExtensionFilters().add(new ExtensionFilter("Word ab 2010", "*.docx"));
				File f = fc.showOpenDialog(null);
				if(f != null){
					String pathOfferTemplate = f.getAbsolutePath();
					tfPathOfferTemplate.setText(pathOfferTemplate);
					new PathProperties().writeProperty(PathProperties.KEY_OFFER_TEMPLATE, pathOfferTemplate);				
				}
				
			}
		});
		
	}
	
	private void initBtnOfferSaving(){
		
		btnPathOfferSaving.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				DirectoryChooser dc = new DirectoryChooser();
				File f = dc.showDialog(null);
				
				if(f != null){
					String pathOfferSaving = f.getAbsolutePath();
					tfPathOfferSaving.setText(pathOfferSaving);
					new PathProperties().writeProperty(PathProperties.KEY_OFFER_SAVING, pathOfferSaving);
				}
				
			}
		});
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
}
