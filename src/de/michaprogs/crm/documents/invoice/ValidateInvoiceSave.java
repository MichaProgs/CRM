package de.michaprogs.crm.documents.invoice;

import de.michaprogs.crm.SaveErrorAlert;
import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;

public class ValidateInvoiceSave {

	private boolean isValid;
	private String message = "";
	
	public ValidateInvoiceSave(	int invoiceID,
								int customerID,
								int clerkID,
								String deliveryDate,
								ObservableList<ModelArticle> obsListArticle){
		
		if(invoiceID != 0 && customerID != 0 && clerkID != 0 && obsListArticle.size() > 0 && ! deliveryDate.equals("")){
			isValid = true;
		}else if(invoiceID == 0){
			message = "Bitte gültige Rechnungs-Nr. verwenden!";
			new SaveErrorAlert(message);
			System.out.println(message);
			isValid = false;
		}else if(customerID == 0){
			message = "Bitte gültige Kunden-Nr. wählen!";
			new SaveErrorAlert(message);
			System.out.println(message);
			isValid = false;
		}else if(clerkID == 0){
			message = "Bitte gültigen Sachbearbeiter wählen!";
			new SaveErrorAlert(message);
			System.out.println(message);
			isValid = false;
		}else if(deliveryDate.equals("")){
			message = "Bitte gültiges Lieferdatum wählen!";
			new SaveErrorAlert(message);
			System.out.println(message);
			isValid = false;
		}else if(obsListArticle.size() == 0){
			message = "Bitte mindestens 1 Artikel angeben!";
			new SaveErrorAlert(message);
			System.out.println(message);
			isValid = false;
		}else{
			message = getClass().getName() +  " Unbekannter Fehler!";
			new SaveErrorAlert(message);
			System.out.println(message);
			isValid = false;
		}
		
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
