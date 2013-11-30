package com.rickyaut.gallery;

import java.util.List;

public interface VehicleService {
	List<Vehicle> findVehiclesByBrand(Brand brand);
	Vehicle getVehicle(Brand brand, String vehicleStandardName);
}
