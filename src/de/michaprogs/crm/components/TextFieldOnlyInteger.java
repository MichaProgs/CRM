package de.michaprogs.crm.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TextFieldOnlyInteger extends TextField{

	private final String REGEX = "[0-9]*";
	
	public TextFieldOnlyInteger(){
		
//		setText("0");
		
		textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(! newValue.matches(REGEX)){
					setText(oldValue);
				}
				
			}
		});
		
	}
	
}
