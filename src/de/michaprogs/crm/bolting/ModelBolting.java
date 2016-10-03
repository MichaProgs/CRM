package de.michaprogs.crm.bolting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelBolting {	
	
	private int boltingID;
	private String bolting;
	
	private ObservableList<ModelBolting> obsListBoltings = FXCollections.observableArrayList();
	
	/**
	 * Constructor for Database (Select All Boltings)
	 */
	public ModelBolting(){}
	
	/**
	 * Constructor for Database (Insert Bolting)
	 * @param bolting
	 */	
	public ModelBolting(String bolting){
		this.bolting = bolting;
	}
	
	/**
	 * Consturctor for ObservableList <br>
	 * Constructor for Database (Update Bolting)
	 * Constructor for Database (Delete Bolting)
	 * @param boltingID
	 * @param bolting
	 */
	public ModelBolting(int boltingID, String bolting){
		this.boltingID = boltingID;
		this.bolting = bolting;
	}
	
	/*
	 * GETTER & SETTER
	 */
	public int getBoltingID() {
		return boltingID;
	}

	public void setBoltingID(int boltingID) {
		this.boltingID = boltingID;
	}

	public String getBolting() {
		return bolting;
	}

	public void setBolting(String bolting) {
		this.bolting = bolting;
	}

	public ObservableList<ModelBolting> getObsListBoltings() {
		return obsListBoltings;
	}

	public void setObsListBoltings(ObservableList<ModelBolting> obsListBoltings) {
		this.obsListBoltings = obsListBoltings;
	}
	
}
