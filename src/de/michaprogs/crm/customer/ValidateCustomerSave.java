package de.michaprogs.crm.customer;

public class ValidateCustomerSave {

	private boolean isValid;
	
	public ValidateCustomerSave(	int _customerID,
									String _name1){
		
		if( 	_customerID != 0 &&
			! 	_name1.isEmpty() ){
			isValid = true;
		}else if(_customerID == 0){
			System.out.println("Bitte gültige Kunden-Nr. wählen!");
			isValid = false;
		}else if(_name1.isEmpty()){
			System.out.println("Bitte gültigen Name1 wählen!");
			isValid = false;
		}else{
			System.err.println("***ModelCustomer.java -> validate: Unbekannter Fehler");
			isValid = false;
		}
		
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
