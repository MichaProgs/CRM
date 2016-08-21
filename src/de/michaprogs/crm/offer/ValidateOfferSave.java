package de.michaprogs.crm.offer;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;

public class ValidateOfferSave {

	private boolean isValid;
	
	public ValidateOfferSave(	int _offerID,
								int _customerID,
								String _offerDate,
								String _requestDate,
								ObservableList<ModelArticle> _obsListArticle){
		
		if(_offerID != 0 && _customerID != 0 && _obsListArticle.size() > 0 && ! _offerDate.equals("") && ! _requestDate.equals("")){
			isValid = true;
		}else if(_offerID == 0){
			System.out.println("Bitte g�ltige Angebots-Nr. verwenden!");
			isValid = false;
		}else if(_customerID == 0){
			System.out.println("Bitte g�ltige Kunden-Nr. w�hlen!");
			isValid = false;
		}else if(_offerDate.equals("")){
			System.out.println("Bitte g�ltiges Angebots-Datum w�hlen!");
			isValid = false;
		}else if(_requestDate.equals("")){
			System.out.println("Bitte g�lties Anfrage-Datum w�hlen!");
			isValid = false;
		}else if(_obsListArticle.size() == 0){
			System.out.println("Bitte mindestens 1 Artikel angeben!");
			isValid = false;
		}else{
			System.out.println("***ModelOffer.java -> validate(): Unbekannter Fehler");
			isValid = false;
		}
		
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
