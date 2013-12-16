package com.rickyaut.gallery;

import java.io.Serializable;
import java.util.List;

public class Maker implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1078309844718598100L;
	String lastUpdate;
	List<Vehicle> vehicles;

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
