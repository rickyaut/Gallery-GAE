package com.rickyaut.gallery;


public enum CarBrand{
	//Alpha Romeo
	//Aston Martin
	Audi("audi", "", "audi-gallery.json"), 
	Benz("benz", "", "benz-gallery.json"), 
	//Bentley
	BMW("bmw", "", "bmw-gallery.json"), 
	//Chrysler
	//Dodge
	//Ferrari
	//Fiat
	Ford("ford", "", "ford-gallery.json"), 
	//Jeep
	GM_BUICK("buick", "", "gm-buick-gallery.json"), 
	GM_Cadillac("cadillac", "", "gm-cadillac-gallery.json"), 
	GM_Chevrolet("chevrolet", "", "gm-chevrolet-gallery.json"), 
	GM_GMC("gmc", "", "gm-gmc-gallery.json"), 
	Holden("holden", "", "holden-gallery.json"), 
	Honda("honda", "", "honda-gallery.json"), 
	Hyundai("hyundai", "", "hyundai-gallery.json"), 
	Jaguar("jaguar", "", "jaguar-gallery.json"), 
	Lamborghini("lamborghini", "", "lamborghini-gallery.json"), 
	//Land Rover
	Lexus("lexus", "", "lexus-gallery.json"), 
	//Lincoln
	Maserati("maserati", "", "maserati-gallery.json"), 
	Mazda("mazda", "", "mazda-gallery.json"), 
	//Mclaren
	//Mini
	Mitsubishi("mitsubishi", "", "mitsubishi-gallery.json"), 
	Nissan("nissan", "", "nissan-gallery.json"), 
	Porsche("porsche", "", "porsche-gallery.json"), 
	//Rolls Royce
	Subaru("subaru", "", "subaru-gallery.json"), 
	//Suzuki
	Toyota("toyota", "", "toyota-gallery.json"), 
	Volvo("volvo", "", "volvo-gallery.json");
	//Volkswagon

	private String shortName;
	private String iconFileName;
	private String dataFileName;
	
	public static CarBrand findByShortName(String shortName){
		for(CarBrand brand: CarBrand.values()){
			if(brand.shortName.equals(shortName)){
				return brand;
			}
		}
		return null;
	}
	
	CarBrand(String shortName, String iconFileName, String dataFileName){
		this.shortName = shortName;
		this.iconFileName = iconFileName;
		this.dataFileName = dataFileName;
	}
	
	public String getShortName() {
		return shortName;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public String getCode() {
		return "vehicle.brand."+name().toLowerCase();
	}

	public String getDefaultMessage() {
		return name();
	}
	
}
