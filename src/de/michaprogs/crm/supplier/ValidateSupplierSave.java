package de.michaprogs.crm.supplier;

public class ValidateSupplierSave {

	private boolean isValid;
	
	public ValidateSupplierSave(int _supplierID,
								String _name1){
		
		if( 	_supplierID != 0 &&
			! 	_name1.isEmpty() ){
			isValid = true;
		}else if(_supplierID == 0){
			System.out.println("Bitte g�ltige Lieferanten-Nr. w�hlen!");
			isValid = false;
		}else if(_name1.isEmpty()){
			System.out.println("Bitte g�ltigen Name1 w�hlen!");
			isValid = false;
		}else{
			System.err.println("***" + getClass().getName() + ": Unbekannter Fehler");
			isValid = false;
		}		
		
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
