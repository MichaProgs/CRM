package de.michaprogs.crm.menubar;


import de.michaprogs.crm.GraphicMenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ControllerMenuBar {

	@FXML private MenuItem miArticle;
	
	private BorderPane content;
	
	public ControllerMenuBar(){
		
	}
	
	@FXML private void initialize(){
		
		//MenuItems
		initMiArticle();
		
	}
	
	/*
	 * MENUITEMS
	 */
	private void initMiArticle(){
		
		miArticle.setGraphic(new GraphicMenuItem("file:resources/article_32.png").getGraphicMenuItem());
		miArticle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//TODO
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
