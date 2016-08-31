package de.michaprogs.crm.customer;

import de.michaprogs.crm.contact.ModelContact;
import javafx.collections.ObservableList;

public class UpdateCustomerContacts {

	public UpdateCustomerContacts(int customerID, ObservableList<ModelContact> obsListContacts){
		
		new DeleteCustomerContacts(customerID);
		new InsertCustomerContacts(customerID, obsListContacts);
		
	}
	
}
