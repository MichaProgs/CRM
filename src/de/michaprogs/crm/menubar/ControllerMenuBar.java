package de.michaprogs.crm.menubar;

import javafx.scene.layout.BorderPane;

public class ControllerMenuBar {

	private BorderPane content;
	
	public ControllerMenuBar(){
		
	}
	
	/**
	 *  set by main-class 
	 *	@param content - the contentpane (borderpane) where the panels are changed 
	 */	
	public void setContent(BorderPane content){
		this.content = content;
	}
	
}
