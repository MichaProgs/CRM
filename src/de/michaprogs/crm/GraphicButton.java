package de.michaprogs.crm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicButton {

	private ImageView iv = null;
	
	public GraphicButton(String filepath){
		
		if(! filepath.isEmpty()){
			iv = new ImageView(new Image(filepath, 32, 32, true, true));
		}
		
	}
	
	public ImageView getGraphicButton(){
		return iv;
	}
	
}
