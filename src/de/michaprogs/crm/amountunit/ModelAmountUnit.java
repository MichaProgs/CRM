package de.michaprogs.crm.amountunit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelAmountUnit {

	private int amountUnitID;
	private String amountUnit;
	
	private ObservableList<ModelAmountUnit> obsListAmountUnits = FXCollections.observableArrayList();
	private ObservableList<String> obsListAmountUnitsComboBox = FXCollections.observableArrayList();
	
	public ModelAmountUnit(){}
	
	public ModelAmountUnit(	int amountUnitID,
							String amountUnit){
		
		this.amountUnitID = amountUnitID;
		this.amountUnit = amountUnit;
		
	}
	
	/**
	 * Constructor for Database (Insert AmountUnit) <br>
	 * Constructor for Database (Delete AmountUnit) <br>
	 * @param amountUnit
	 */
	public ModelAmountUnit(String amountUnit){
		this.amountUnit = amountUnit;
	}

	/*
	 * GETTER & SETTER
	 */
	public int getAmountUnitID() {
		return amountUnitID;
	}

	public void setAmountUnitID(int amountUnitID) {
		this.amountUnitID = amountUnitID;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public ObservableList<ModelAmountUnit> getObsListAmountUnits() {
		return obsListAmountUnits;
	}

	public void setObsListAmountUnits(ObservableList<ModelAmountUnit> obsListAmountUnits) {
		this.obsListAmountUnits = obsListAmountUnits;
	}

	public ObservableList<String> getObsListAmountUnitsComboBox() {
		return obsListAmountUnitsComboBox;
	}

	public void setObsListAmountUnitsComboBox(ObservableList<String> obsListAmountUnitsComboBox) {
		this.obsListAmountUnitsComboBox = obsListAmountUnitsComboBox;
	}
	
	
	
}
