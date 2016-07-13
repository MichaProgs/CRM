package de.michaprogs.crm.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TextFieldDesity extends TextField{

	private final String REGEX = "[0-9]+([.|,]?[0-9]{0,4})?";
	
	public TextFieldDesity(){
		
		setText("0.0000");
		
		textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(! newValue.matches(REGEX)){
					setText(oldValue);
				}
				
			}
		});
		
		focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(oldValue){
					
					//If inputstring has a comma it will be replaced by a dot
					if(getText().contains(",")){
						setText(getText().replace(",", "."));
					//If inputstring hat nether comma or dot '.0000' will be added
					}else if(	! getText().contains(",") ||
								! getText().contains(".")){
						setText(getText().concat(".0000"));
					}
					
					//If inputstring like 5. '0000' will be added
					try{
						getText().charAt(getText().indexOf(".") + 1);
					}catch(StringIndexOutOfBoundsException e){
						setText(getText().concat("0000"));
					}catch(Exception e){
						System.out.println("Unbekannter Fehler!");
						e.printStackTrace();
					}	
					
					//If inputstring like 5.0 '000' will be added
					try{
						getText().charAt(getText().indexOf(".") + 2);
					}catch(StringIndexOutOfBoundsException e){
						setText(getText().concat("000"));
					}catch(Exception e){
						System.out.println("Unbekannter Fehler!");
						e.printStackTrace();
					}
					
					//If inputstring like 5.00 '00' will be added
					try{
						getText().charAt(getText().indexOf(".") + 3);
					}catch(StringIndexOutOfBoundsException e){
						setText(getText().concat("00"));
					}catch(Exception e){
						System.out.println("Unbekannter Fehler!");
						e.printStackTrace();
					}
					
					//If inputstring like 5.000 '0' will be added
					try{
						getText().charAt(getText().indexOf(".") + 4);
					}catch(StringIndexOutOfBoundsException e){
						setText(getText().concat("0"));
					}catch(Exception e){
						System.out.println("Unbekannter Fehler!");
						e.printStackTrace();
					}
					
				}
				
			}
		});
		
	}
	
}
