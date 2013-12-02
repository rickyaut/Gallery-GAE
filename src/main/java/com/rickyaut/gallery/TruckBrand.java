package com.rickyaut.gallery;


public enum TruckBrand{
	Volvo("volvo", "", "volvo-gallery.json");

	private String shortName;
	private String iconFileName;
	private String dataFileName;
	
	public static TruckBrand findByShortName(String shortName){
		for(TruckBrand brand: TruckBrand.values()){
			if(brand.shortName.equals(shortName)){
				return brand;
			}
		}
		return null;
	}
	
	TruckBrand(String shortName, String iconFileName, String dataFileName){
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
