package com.rickyaut.gallery;


public enum CarBrand{
	Audi("audi", "", "audi-gallery.json"), 
	Benz("benz", "", "benz-gallery.json"), 
	BMW("bmw", "", "bmw-gallery.json"), 
	Ford("ford", "", "ford-gallery.json"), 
	GM_BUICK("buick", "", "gm-buick-gallery.json"), 
	GM_Cadillac("cadillac", "", "gm-cadillac-gallery.json"), 
	GM_Chevrolet("chevrolet", "", "gm-chevrolet-gallery.json"), 
	GM_GMC("gmc", "", "gm-gmc-gallery.json"), 
	Holden("holden", "", "holden-gallery.json"), 
	Honda("honda", "", "honda-gallery.json"), 
	Jaguar("jaguar", "", "jaguar-gallery.json"), 
	Lamborghini("lamborghini", "", "lamborghini-gallery.json"), 
	Lexus("lexus", "", "lexus-gallery.json"), 
	Maserati("maserati", "", "maserati-gallery.json"), 
	Mazda("mazda", "", "mazda-gallery.json"), 
	Porsche("porsche", "", "porsche-gallery.json"), 
	Toyota("toyota", "", "toyota-gallery.json"), 
	Volvo("volvo", "", "volvo-gallery.json");

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
