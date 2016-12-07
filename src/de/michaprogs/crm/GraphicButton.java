package de.michaprogs.crm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicButton {

	private ImageView iv = null;
	
	/**
	 * Sets a Graphic on a Button
	 * @param filename Name of the file in resource-folder + extention (*.png, *.jpg etc.)
	 */
	public GraphicButton(String filename){
		
		if(! filename.isEmpty()){
			iv = new ImageView(new Image("file:resources/" + filename, 32, 32, true, true));
		}
		
	}
	
	/*
	 * Sets a Graphic on a Button
	 * @param filename - name of the file in resource-folder + extention (*.png, *.jpg etc.)
	 */
	public static ImageView graphicButton(String filename, int width, int height){
		if(! filename.isEmpty()){
			return new ImageView(new Image("file:resources/" + filename, width, height, true, true));
		}
		return null;
	}
	
	public ImageView getGraphicButton(){
		return iv;
	}
	
}
