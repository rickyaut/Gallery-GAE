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
	
	@RequestMapping(value="/brand/{brandShortName}/vehicles", method=RequestMethod.GET)
	public ModelAndView listVehicles(@PathVariable String brandShortName){
		Brand selectedBrand = Brand.findByShortName(brandShortName);
		List<Vehicle> vehicles = vehicleService.findVehiclesByBrand(selectedBrand);
		Brand[] brands = Brand.values();
		ModelAndView modelAndView = new ModelAndView("vehicles");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("vehicles", vehicles);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/vehicle/{vehicleStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showImages(@PathVariable String brandShortName, @PathVariable String vehicleStandardName){
		Brand selectedBrand = Brand.findByShortName(brandShortName);
		Vehicle selectedVehicle = vehicleService.getVehicle(selectedBrand, vehicleStandardName);
		ModelAndView modelAndView = new ModelAndView("images");
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("selectedVehicle", selectedVehicle);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandName}/vehicle/{vehicleName}/videos", method=RequestMethod.GET)
	public ModelAndView showVideos(@PathVariable String brandName, @PathVariable String vehicleName){
		System.out.println(brandName);
		return new ModelAndView("videos");
	}
}
