package com.rickyaut.gallery;

import java.util.List;

public interface VehicleService {
	List<Vehicle> findCarsByBrand(CarBrand brand);
	Vehicle getCar(CarBrand brand, String carStandardName);
	List<Video> findCarYoutubeVideos(CarBrand brand, String carStandardName);
	List<Story> findCarStories(CarBrand brand, String carStandardName);

	List<Vehicle> findTrucksByBrand(TruckBrand brand);
	Vehicle getTruck(TruckBrand brand, String truckStandardName);

	List<Vehicle> findBoatsByBrand(BoatBrand brand);
	Vehicle getBoat(BoatBrand brand, String boatStandardName);
	
}
