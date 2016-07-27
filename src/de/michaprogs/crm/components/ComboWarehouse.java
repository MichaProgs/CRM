package de.michaprogs.crm.components;

import de.michaprogs.crm.warehouse.ModelWarehouse;
import javafx.scene.control.ComboBox;

public class ComboWarehouse extends ComboBox<String>{

	public ComboWarehouse(){
		
		ModelWarehouse warehouse = new ModelWarehouse();
		warehouse.selectWarehouses();
		
		this.setItems(warehouse.getObsListWarehousesCombo());
		this.getSelectionModel().selectFirst();
		
	}
	
}
