package de.michaprogs.crm.supplier;

import de.michaprogs.crm.contact.ModelContact;
import javafx.collections.ObservableList;

public class UpdateSupplierContacts {

	public UpdateSupplierContacts(int supplierID, ObservableList<ModelContact> obsListContacts){
		
		new DeleteSupplierContacts(supplierID);
		new InsertSupplierContacts(supplierID, obsListContacts);
		
	}
	
}
