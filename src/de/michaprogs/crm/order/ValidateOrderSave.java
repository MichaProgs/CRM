package de.michaprogs.crm.order;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;

public class ValidateOrderSave {

	private boolean isValid;
	
	public ValidateOrderSave(	int orderID,
								int supplierID,
								String orderDate,
								String requestDate,
								ObservableList<ModelArticle> obsListArticle){
		
		if(orderID != 0 && supplierID != 0 && obsListArticle.size() > 0 && ! orderDate.equals("") && ! requestDate.equals("")){
			isValid = true;
		}else if(orderID == 0){
			System.out.println("Bitte g�ltige Bestell-Nr. verwenden!");
			isValid = false;
		}else if(supplierID == 0){
			System.out.println("Bitte g�ltige Lieferanten-Nr. w�hlen!");
			isValid = false;
		}else if(orderDate.equals("")){
			System.out.println("Bitte g�ltiges Bestell-Datum w�hlen!");
			isValid = false;
		}else if(requestDate.equals("")){
			System.out.println("Bitte g�lties Anfrage-Datum w�hlen!");
			isValid = false;
		}else if(obsListArticle.size() == 0){
			System.out.println("Bitte mindestens 1 Artikel angeben!");
			isValid = false;
		}else{
			System.out.println(getClass().getName() + ": Unbekannter Fehler");
			isValid = false;
		}
		
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
