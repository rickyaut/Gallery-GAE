package com.rickyaut.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VehiclesController {
	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping(value="/brand/{brandShortName}/cars", method=RequestMethod.GET)
	public ModelAndView listCars(@PathVariable String brandShortName){
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
		CarBrand[] brands = CarBrand.values();
		ModelAndView modelAndView = new ModelAndView("cars");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("cars", cars);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/car/{carStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showCarImages(@PathVariable String brandShortName, @PathVariable String carStandardName){
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		ModelAndView modelAndView = new ModelAndView("car.images");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("cars", cars);
		modelAndView.addObject("selectedCar", selectedCar);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/car/{carStandardName}/videos", method=RequestMethod.GET)
	public ModelAndView showVideos(@PathVariable String brandShortName, @PathVariable String carStandardName){
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		ModelAndView modelAndView = new ModelAndView("car.videos");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("cars", cars);
		modelAndView.addObject("selectedCar", selectedCar);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/trucks", method=RequestMethod.GET)
	public ModelAndView listTrucks(@PathVariable String brandShortName){
		TruckBrand selectedBrand = TruckBrand.findByShortName(brandShortName);
		List<Vehicle> trucks = vehicleService.findTrucksByBrand(selectedBrand);
		TruckBrand[] brands = TruckBrand.values();
		ModelAndView modelAndView = new ModelAndView("trucks");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("trucks", trucks);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/truck/{truckStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showTruckImages(@PathVariable String brandShortName, @PathVariable String truckStandardName){
		TruckBrand selectedBrand = TruckBrand.findByShortName(brandShortName);
		List<Vehicle> trucks = vehicleService.findTrucksByBrand(selectedBrand);
		Vehicle selectedTruck = vehicleService.getTruck(selectedBrand, truckStandardName);
		ModelAndView modelAndView = new ModelAndView("truck.images");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("trucks", trucks);
		modelAndView.addObject("selectedTruck", selectedTruck);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/boats", method=RequestMethod.GET)
	public ModelAndView listBoatss(@PathVariable String brandShortName){
		BoatBrand selectedBrand = BoatBrand.findByShortName(brandShortName);
		List<Vehicle> boats = vehicleService.findBoatsByBrand(selectedBrand);
		BoatBrand[] brands = BoatBrand.values();
		ModelAndView modelAndView = new ModelAndView("boats");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("boats", boats);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/boat/{boatStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showBoatImages(@PathVariable String brandShortName, @PathVariable String boatStandardName){
		BoatBrand selectedBrand = BoatBrand.findByShortName(brandShortName);
		List<Vehicle> boats = vehicleService.findBoatsByBrand(selectedBrand);
		Vehicle selectedBoat = vehicleService.getBoat(selectedBrand, boatStandardName);
		ModelAndView modelAndView = new ModelAndView("boat.images");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("boats", boats);
		modelAndView.addObject("selectedBoat", selectedBoat);
		return modelAndView;
	}

}
