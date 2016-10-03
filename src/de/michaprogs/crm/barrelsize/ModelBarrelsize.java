package de.michaprogs.crm.barrelsize;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelBarrelsize {	
	
	private int barrelsizeID;
	private String barrelsize;
	
	private ObservableList<ModelBarrelsize> obsListBarrelsizes = FXCollections.observableArrayList();
	
	public ModelBarrelsize(){}
	
	/**
	 * Constructor for Database (Insert Barrelsize)
	 * @param barrelsize
	 */
	public ModelBarrelsize(String barrelsize){
		this.barrelsize = barrelsize;
	}
	
	/**
	 * Constructor for Database (Delete Barrelsize)
	 * Constructor for ObservableList
	 * @param _barrelsizeID
	 * @param _barrelsize
	 */
	public ModelBarrelsize(int barrelsizeID, String barrelsize){
		this.barrelsizeID = barrelsizeID;
		this.barrelsize = barrelsize;
	}

	/*
	 * GETTER & SETTER
	 */
	public int getBarrelsizeID() {
		return barrelsizeID;
	}

	public void setBarrelsizeID(int barrelsizeID) {
		this.barrelsizeID = barrelsizeID;
	}

	public String getBarrelsize() {
		return barrelsize;
	}

	public void setBarrelsize(String barrelsize) {
		this.barrelsize = barrelsize;
	}

	public ObservableList<ModelBarrelsize> getObsListBarrelsizes() {
		return obsListBarrelsizes;
	}

	public void setObsListBarrelsizes(ObservableList<ModelBarrelsize> obsListBarrelsizes) {
		this.obsListBarrelsizes = obsListBarrelsizes;
	}
	
}
