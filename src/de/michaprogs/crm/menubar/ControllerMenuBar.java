package de.michaprogs.crm.menubar;


import de.michaprogs.crm.GraphicMenuItem;
import de.michaprogs.crm.properties.LoadProperties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ControllerMenuBar {

	@FXML private MenuItem itemArticle;
	@FXML private MenuItem itemProperties;
	
	private BorderPane content;
	
	public ControllerMenuBar(){}
	
	@FXML private void initialize(){
		
		//MenuItems
		initItemArticle();
		initItemProperties();
		
	}
	
	/*
	 * MENUITEMS
	 */
	private void initItemArticle(){
		
		itemArticle.setGraphic(new GraphicMenuItem("file:resources/article_32.png").getGraphicMenuItem());
		itemArticle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//TODO
			}
		});
		
	}
	
	private void initItemProperties(){
		
//		itemProperties.setGraphic(); TODO
		itemProperties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new LoadProperties(true);
			}
		});
		
	}
	
	/**
	 *  set by main-class 
	 *	@param content - the contentpane (borderpane) where the panels are changed 
	 */	
	public void setContent(BorderPane content){
		this.content = content;
	}
	
}
