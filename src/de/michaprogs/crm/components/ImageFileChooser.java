package de.michaprogs.crm.components;

import java.io.File;

import javafx.stage.FileChooser;

public class ImageFileChooser{
	
	private File file;
	
	public ImageFileChooser(){
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Bilddateien", "*.jpg", "*.png", "*.bmp");
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(extFilter);
		
		file = fc.showOpenDialog(null);
		
	}
	
	public File getImageFile(){
		return file;
	}
	
}