package com.rickyaut.gallery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class VehicleServiceImpl implements VehicleService {
	private final static Log logger = LogFactory.getLog(VehicleServiceImpl.class);
	@Autowired
	private JsonFactory jsonFactory;
	@Autowired
	private ObjectMapper jsonObjectMapper;

	public JsonFactory getJsonFactory() {
		return jsonFactory;
	}

	public void setJsonFactory(JsonFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	public ObjectMapper getJsonObjectMapper() {
		return jsonObjectMapper;
	}

	public void setJsonObjectMapper(ObjectMapper jsonObjectMapper) {
		this.jsonObjectMapper = jsonObjectMapper;
	}

	@Override
	public List<Vehicle> findCarsByBrand(CarBrand brand) {
		URL url = getClass().getClassLoader().getResource("json/car/"+brand.getDataFileName());
		try {
			Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
			logger.debug(String.format("found %d cars for %s", vehicleArray.length, brand.name()));
			return Arrays.asList(vehicleArray);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<Vehicle>();
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
		URL url = getClass().getClassLoader().getResource("json/truck/"+brand.getDataFileName());
		try {
			Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
			logger.debug(String.format("found %d trucks for %s", vehicleArray.length, brand.name()));
			return Arrays.asList(vehicleArray);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<Vehicle>();
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
		URL url = getClass().getClassLoader().getResource("json/boat/"+brand.getDataFileName());
		try {
			Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
			logger.debug(String.format("found %d boats for %s", vehicleArray.length, brand.name()));
			return Arrays.asList(vehicleArray);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<Vehicle>();
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
