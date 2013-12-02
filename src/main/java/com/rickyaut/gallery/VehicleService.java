package com.rickyaut.gallery;

import java.util.List;

public interface VehicleService {
	List<Vehicle> findCarsByBrand(CarBrand brand);
	Vehicle getCar(CarBrand brand, String vehicleStandardName);
}
