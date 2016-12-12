package de.michaprogs.crm.documents.deliverybill;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;

public class ValidateDeliverybillSave {

	private boolean isValid;
	
	public ValidateDeliverybillSave(	int _deliverybillID,
										int _customerID,
										String _deliverybillDate,
										String _requestDate,
										ObservableList<ModelArticle> _obsListArticle){
		
		if(_deliverybillID != 0 && _customerID != 0 && _obsListArticle.size() > 0 && ! _deliverybillDate.equals("") && ! _requestDate.equals("")){
			isValid = true;
		}else if(_deliverybillID == 0){
			System.out.println("Bitte gültige Lieferschein-Nr. verwenden!");
			isValid = false;
		}else if(_customerID == 0){
			System.out.println("Bitte gültige Kunden-Nr. wählen!");
			isValid = false;
		}else if(_deliverybillDate.equals("")){
			System.out.println("Bitte gültiges Lieferschein-Datum wählen!");
			isValid = false;
		}else if(_requestDate.equals("")){
			System.out.println("Bitte gülties Anfrage-Datum wählen!");
			isValid = false;
		}else if(_obsListArticle.size() == 0){
			System.out.println("Bitte mindestens 1 Artikel angeben!");
			isValid = false;
		}else{
			System.out.println("***ModelInvoice.java -> validate(): Unbekannter Fehler");
			isValid = false;
		}
		
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
