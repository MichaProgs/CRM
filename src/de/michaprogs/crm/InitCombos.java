package de.michaprogs.crm;

import javafx.scene.control.ComboBox;

public class InitCombos {

	public InitCombos(){}
	
	public void initComboPriceUnit(ComboBox<String> cbPriceUnit){
		
		cbPriceUnit.getItems().addAll(
			"1",
			"10",
			"100",
			"1000"
		);
		
		cbPriceUnit.getSelectionModel().select(0);
		
	}
	
	public void initComboAmountUnit(ComboBox<String> cbAmountUnit){
		
		cbAmountUnit.getItems().addAll(
			"STK",
			"LTR",
			"METER",
			"KG",
			"TO"
		);
		
		cbAmountUnit.getSelectionModel().select(0);
		
	}
	
	public void initComboTax(ComboBox<String> cbTax){
		
		cbTax.getItems().addAll(
			"7",
			"19"
		);
		
		cbTax.getSelectionModel().select(1);
		
	}
	
	public void initComboCategory(ComboBox<String> cbCategory){
		
		cbCategory.getItems().addAll(
			"",
			"Gas",
			"Gas-Zubehör",
			"Heizöl",
			"Diesel",
			"Schmierstoffe"
		);
		
		cbCategory.getSelectionModel().select(0);
		
	}
	
	public void initComboOfferEnd(ComboBox<String> cbOfferEnd){
		
		cbOfferEnd.getItems().addAll(
			"Standart",
			"Flüssiggas",
			"Behälter",
			"Behälter-Tausch",
			"Heizmobil"
		);
		
		cbOfferEnd.getSelectionModel().select(0);
		
	}
	
	public void initComboLand(ComboBox<String> cbLand){
		
		cbLand.getItems().addAll(
			"",
			"DE",
			"AT",
			"CH",
			"ITA",
			"CZ"
		);
		
	}
	
	public void initComboPayment(ComboBox<String> cbPayment){
		
		cbPayment.getItems().addAll(
			"",
			"Überweisung",
			"SEPA-Lastschrift",
			"Barzahlung"
		);
		
	}
	
	public void initComboWarehouse(ComboBox<String> cbWarehouse){
		
		cbWarehouse.getItems().addAll(
			"",
			"Hauptlager",
			"Zweitlager",
			"Drittlager"
		);
		
	}
	
}
