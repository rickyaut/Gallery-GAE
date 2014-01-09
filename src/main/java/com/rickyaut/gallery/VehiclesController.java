package com.rickyaut.gallery;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VehiclesController {
	@Autowired
	VehicleService vehicleService;
	private void setMetaDataIntoMV(ModelAndView mv, String title, String description, String image){
		mv.addObject("meta_title", "AllMotorsGallery - "+ title);
		mv.addObject("meta_description", description);
		if(StringUtils.isNotBlank(image)){
			mv.addObject("ogImage", image);
		}
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView showHomePage(){
		ModelAndView modelAndView = new ModelAndView("index");
		setMetaDataIntoMV(modelAndView, "Home", 
				"Find car images/videos and useful stories in AllMotorsGallery", null);
		return modelAndView;
	}
	
	@RequestMapping(value="/brand/{brandShortName}/cars", method=RequestMethod.GET)
	public ModelAndView listCars(@PathVariable String brandShortName){
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
		CarBrand[] brands = CarBrand.values();
		ModelAndView modelAndView = new ModelAndView("cars");
		StringBuffer descriptionBuf = new StringBuffer(String.format("List of all %s cars, including ", selectedBrand.name()));
		for(Vehicle car: cars){
			descriptionBuf.append(car.getName()+", ");
		}
		setMetaDataIntoMV(modelAndView, selectedBrand.name(), 
				descriptionBuf.toString(), null);
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("cars", cars);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/car/{carStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showCarImages(HttpServletRequest request, HttpServletResponse response, @PathVariable String brandShortName, @PathVariable String carStandardName) throws IOException{
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		if(selectedCar == null){
			response.sendRedirect(String.format("/brand/%s/cars", brandShortName));
			return null;
		}else{
			ModelAndView modelAndView = new ModelAndView("car.images");
			setMetaDataIntoMV(modelAndView, String.format("%s %s Images", selectedBrand.name(), selectedCar.getName()), 
					String.format("interior images/photos and exterior images/photos gallery of %s %s car", selectedBrand.name(), selectedCar.getName()), null);
			modelAndView.addObject("selectedBrand", selectedBrand);
			modelAndView.addObject("cars", cars);
			modelAndView.addObject("selectedCar", selectedCar);
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/brand/{brandShortName}/car/{carStandardName}/video", method=RequestMethod.GET)
	public ModelAndView playCarVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable String brandShortName, @PathVariable String carStandardName, @RequestParam("youtubeID") String youtubeID) throws IOException{
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		if(selectedCar == null){
			response.sendRedirect(String.format("/brand/%s/cars", brandShortName));
			return null;
		}else{
			if(selectedCar.getVideos()==null||selectedCar.getVideos().isEmpty()){
				List<Video> videos = vehicleService.findCarYoutubeVideos(selectedBrand, carStandardName);
				selectedCar.setVideos(videos);
			}
			Video selectedVideo = null;
			for(Video video: selectedCar.getVideos()){
				if(StringUtils.equals(video.getYoutubeID(), youtubeID)){
					selectedVideo = video;
				}
			}
			List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
			ModelAndView modelAndView = new ModelAndView("car.videos");
			setMetaDataIntoMV(modelAndView, String.format("%s %s Videos", selectedBrand.name(), selectedCar.getName()),  
					String.format("Play popular youtube videos about %s %s car", selectedBrand.name(), selectedCar.getName()), selectedVideo==null?"":selectedVideo.getThumbnailURL());
			modelAndView.addObject("selectedBrand", selectedBrand);
			modelAndView.addObject("cars", cars);
			modelAndView.addObject("selectedCar", selectedCar);
			return modelAndView;
		}

	}

	@RequestMapping(value="/brand/{brandShortName}/car/{carStandardName}/videos", method=RequestMethod.GET)
	public String showCarVideos(HttpServletRequest request, HttpServletResponse response, @PathVariable String brandShortName, @PathVariable String carStandardName){
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Video> videos = vehicleService.findCarYoutubeVideos(selectedBrand, carStandardName);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		selectedCar.setVideos(videos);
		return "redirect:/brand/"+brandShortName+"/car/"+carStandardName+"/video?youtubeID="+videos.get(0).getYoutubeID();
	}

	@RequestMapping(value="/brand/{brandShortName}/car/{carStandardName}/stories", method=RequestMethod.GET)
	public ModelAndView showCarStories(HttpServletRequest request, HttpServletResponse response, @PathVariable String brandShortName, @PathVariable String carStandardName) throws IOException{
		CarBrand selectedBrand = CarBrand.findByShortName(brandShortName);
		List<Vehicle> cars = vehicleService.findCarsByBrand(selectedBrand);
		Vehicle selectedCar = vehicleService.getCar(selectedBrand, carStandardName);
		if(selectedCar == null){
			response.sendRedirect(String.format("/brand/%s/cars", brandShortName));
			return null;
		}else{
			List<Story> stories = vehicleService.findCarStories(selectedBrand, carStandardName);
			selectedCar.setStories(stories);
			ModelAndView modelAndView = new ModelAndView("car.stories");
			setMetaDataIntoMV(modelAndView, String.format("%s %s stories", selectedBrand.name(), selectedCar.getName()),  
					String.format("Read hot news and useful stories about %s %s", selectedBrand.name(), selectedCar.getName()), null);
			modelAndView.addObject("selectedBrand", selectedBrand);
			modelAndView.addObject("cars", cars);
			modelAndView.addObject("selectedCar", selectedCar);
			return modelAndView;
		}
	}

	@RequestMapping(value="/brand/{brandShortName}/trucks", method=RequestMethod.GET)
	public ModelAndView listTrucks(@PathVariable String brandShortName){
		TruckBrand selectedBrand = TruckBrand.findByShortName(brandShortName);
		List<Vehicle> trucks = vehicleService.findTrucksByBrand(selectedBrand);
		TruckBrand[] brands = TruckBrand.values();
		ModelAndView modelAndView = new ModelAndView("trucks");
		StringBuffer descriptionBuf = new StringBuffer(String.format("List of all %s trucks, including ", selectedBrand.name()));
		for(Vehicle truck: trucks){
			descriptionBuf.append(truck.getName()+", ");
		}
		setMetaDataIntoMV(modelAndView, selectedBrand.name(), descriptionBuf.toString(), null);
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("trucks", trucks);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/truck/{truckStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showTruckImages(HttpServletRequest request, HttpServletResponse response, @PathVariable String brandShortName, @PathVariable String truckStandardName) throws IOException{
		TruckBrand selectedBrand = TruckBrand.findByShortName(brandShortName);
		List<Vehicle> trucks = vehicleService.findTrucksByBrand(selectedBrand);
		Vehicle selectedTruck = vehicleService.getTruck(selectedBrand, truckStandardName);
		if(selectedTruck == null){
			response.sendRedirect(String.format("/brand/%s/trucks", brandShortName));
			return null;
		}else{
			ModelAndView modelAndView = new ModelAndView("truck.images");
			setMetaDataIntoMV(modelAndView, String.format("%s %s Images", selectedBrand.name(), selectedTruck.getName()), 
					String.format("Display image collections of %s %s truck", selectedBrand.name(), selectedTruck.getName()), null);
			modelAndView.addObject("selectedBrand", selectedBrand);
			modelAndView.addObject("trucks", trucks);
			modelAndView.addObject("selectedTruck", selectedTruck);
			return modelAndView;
		}
	}

	@RequestMapping(value="/brand/{brandShortName}/boats", method=RequestMethod.GET)
	public ModelAndView listBoatss(@PathVariable String brandShortName){
		BoatBrand selectedBrand = BoatBrand.findByShortName(brandShortName);
		List<Vehicle> boats = vehicleService.findBoatsByBrand(selectedBrand);
		BoatBrand[] brands = BoatBrand.values();
		ModelAndView modelAndView = new ModelAndView("boats");
		StringBuffer descriptionBuf = new StringBuffer(String.format("List of all %s boats, including ", selectedBrand.name()));
		for(Vehicle truck: boats){
			descriptionBuf.append(truck.getName()+", ");
		}
		setMetaDataIntoMV(modelAndView, selectedBrand.name(), descriptionBuf.toString(), null);
		modelAndView.addObject("selectedBrand", selectedBrand);
		modelAndView.addObject("boats", boats);
		modelAndView.addObject("brands", brands);
		return modelAndView;
	}

	@RequestMapping(value="/brand/{brandShortName}/boat/{boatStandardName}/images", method=RequestMethod.GET)
	public ModelAndView showBoatImages(HttpServletRequest request, HttpServletResponse response, @PathVariable String brandShortName, @PathVariable String boatStandardName) throws IOException{
		BoatBrand selectedBrand = BoatBrand.findByShortName(brandShortName);
		List<Vehicle> boats = vehicleService.findBoatsByBrand(selectedBrand);
		Vehicle selectedBoat = vehicleService.getBoat(selectedBrand, boatStandardName);
		if(selectedBoat == null){
			response.sendRedirect(String.format("/brand/%s/boats", brandShortName));
			return null;
		}else{
			ModelAndView modelAndView = new ModelAndView("boat.images");
			setMetaDataIntoMV(modelAndView, String.format("%s %s Images", selectedBrand.name(), selectedBoat.getName()), 
					String.format("Display image collections of %s %s boat", selectedBrand.name(), selectedBoat.getName()), null);
			modelAndView.addObject("selectedBrand", selectedBrand);
			modelAndView.addObject("boats", boats);
			modelAndView.addObject("selectedBoat", selectedBoat);
			return modelAndView;
		}
	}

}
