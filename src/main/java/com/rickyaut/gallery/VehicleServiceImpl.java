package com.rickyaut.gallery;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.memcache.MemcacheService;

public class VehicleServiceImpl implements VehicleService {
	private static final Logger logger = Logger.getLogger(VehicleServiceImpl.class.getName());
	@Autowired
	private ObjectMapper jsonObjectMapper;
	
	@Autowired
	private MemcacheService syncCache;

	@Override
	public List<Vehicle> findCarsByBrand(CarBrand brand) {
		String dataFileName = "json/car/"+brand.getDataFileName();
		List<Vehicle> vehicles = (List<Vehicle>)syncCache.get(dataFileName);
		if(vehicles == null){
			URL url = getClass().getClassLoader().getResource(dataFileName);
			try {
				Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
				vehicles = Arrays.asList(vehicleArray);
				syncCache.put(dataFileName, vehicles);
			} catch (JsonProcessingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle getCar(CarBrand brand, String vehicleStandardName) {
		List<Vehicle> vehicles = findCarsByBrand(brand);
		for(Vehicle vehicle: vehicles){
			String standardName = vehicle.getName().toLowerCase().replaceAll(" ", "-");
			if(StringUtils.equals(standardName, vehicleStandardName)){
				return vehicle;
			}
		}
		return null;
	}

	@Override
	public List<Vehicle> findTrucksByBrand(TruckBrand brand) {
		String dataFileName = "json/truck/"+brand.getDataFileName();
		List<Vehicle> vehicles = (List<Vehicle>)syncCache.get(dataFileName);
		if(vehicles == null){
			URL url = getClass().getClassLoader().getResource(dataFileName);
			try {
				Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
				vehicles = Arrays.asList(vehicleArray);
				syncCache.put(dataFileName, vehicles);
			} catch (JsonProcessingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle getTruck(TruckBrand brand, String truckStandardName) {
		List<Vehicle> vehicles = findTrucksByBrand(brand);
		for(Vehicle vehicle: vehicles){
			String standardName = vehicle.getName().toLowerCase().replaceAll(" ", "-");
			if(StringUtils.equals(standardName, truckStandardName)){
				return vehicle;
			}
		}
		return null;
	}

	@Override
	public List<Vehicle> findBoatsByBrand(BoatBrand brand) {
		String dataFileName = "json/boat/"+brand.getDataFileName();
		List<Vehicle> vehicles = (List<Vehicle>)syncCache.get(dataFileName);
		if(vehicles == null){
			URL url = getClass().getClassLoader().getResource(dataFileName);
			try {
				Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
				vehicles = Arrays.asList(vehicleArray);
				syncCache.put(dataFileName, vehicles);
			} catch (JsonProcessingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle getBoat(BoatBrand brand, String boatStandardName) {
		List<Vehicle> vehicles = findBoatsByBrand(brand);
		for(Vehicle vehicle: vehicles){
			String standardName = vehicle.getName().toLowerCase().replaceAll(" ", "-");
			if(StringUtils.equals(standardName, boatStandardName)){
				return vehicle;
			}
		}
		return null;
	}
}
