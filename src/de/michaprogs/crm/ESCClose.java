package de.michaprogs.crm;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ESCClose {

	/**
	 * This class can be called if a window or dialog should be closable by pressing esc
	 * @param stage - the stage that should be closed
	 * @param scene - the scene which the eventhandler is set on
	 */
	public ESCClose(Stage stage, Scene scene){
		
		final EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode() == KeyCode.ESCAPE){
					stage.close();
				}
				
			}
		};
		
		scene.setOnKeyPressed(ke);
		
	}
	
}
