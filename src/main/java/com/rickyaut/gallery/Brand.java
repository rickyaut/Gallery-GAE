package com.rickyaut.gallery;


public enum Brand{
	Audi("audi", "", "audi-gallery.json"), 
	Benz("benz", "", "benz-gallery.json"), 
	BMW("bmw", "", "bmw-gallery.json"), 
	Ford("ford", "", "ford-gallery.json"), 
	GM("gm", "", "gm-gallery.json"), 
	Holden("holden", "", "holden-gallery.json"), 
	Honda("honda", "", "honda-gallery.json"), 
	Jaguar("jaguar", "", "jaguar-gallery.json"), 
	Lamborghini("lamborghini", "", "lamborghini-gallery.json"), 
	Lexus("lexus", "", "lexus-gallery.json"), 
	Maserati("maserati", "", "maserati-gallery.json"), 
	Mazda("mazda", "", "mazda-gallery.json"), 
	Porsche("porsche", "", "porsche-gallery.json"), 
	Toyota("toyota", "", "toyota-gallery.json");

	private String shortName;
	private String iconFileName;
	private String dataFileName;
	
	public static Brand findByShortName(String shortName){
		for(Brand brand: Brand.values()){
			if(brand.shortName.equals(shortName)){
				return brand;
			}
		}
		return null;
	}
	
	Brand(String shortName, String iconFileName, String dataFileName){
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
