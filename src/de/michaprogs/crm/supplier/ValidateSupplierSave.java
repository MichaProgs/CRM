package de.michaprogs.crm.supplier;

public class ValidateSupplierSave {

	private boolean isValid;
	
	public ValidateSupplierSave(int _supplierID,
								String _name1){
		
		if( 	_supplierID != 0 &&
			! 	_name1.isEmpty() ){
			isValid = true;
		}else if(_supplierID == 0){
			System.out.println("Bitte gültige Lieferanten-Nr. wählen!");
			isValid = false;
		}else if(_name1.isEmpty()){
			System.out.println("Bitte gültigen Name1 wählen!");
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
