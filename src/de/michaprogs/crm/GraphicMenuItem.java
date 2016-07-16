package de.michaprogs.crm;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicMenuItem {

	private ImageView iv = null;
	
	public GraphicMenuItem(String filepath){
		
		if(! filepath.isEmpty()){
			iv = new ImageView(new Image(filepath, 22, 22, true, true));
		}
		
	}
	
	public ImageView getGraphicMenuItem(){
		return iv;
	}
	
}
