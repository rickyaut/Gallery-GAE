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
	public ModelAndView listVehicles(@PathVariable String brandShortName){
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
	public ModelAndView showImages(@PathVariable String brandShortName, @PathVariable String carStandardName){
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		ModelAndView modelAndView = new ModelAndView("images");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("selectedCar", selectedCar);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandName}/car/{vehicleName}/videos", method=RequestMethod.GET)
	public ModelAndView showVideos(@PathVariable String brandName, @PathVariable String vehicleName){
		System.out.println(brandName);
		return new ModelAndView("videos");
	}
}
