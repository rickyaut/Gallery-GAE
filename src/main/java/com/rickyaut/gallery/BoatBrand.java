package com.rickyaut.gallery;


public enum BoatBrand{
	Mustang("mustang", "", "mustang-gallery.json"); 

	private String shortName;
	private String iconFileName;
	private String dataFileName;
	
	public static BoatBrand findByShortName(String shortName){
		for(BoatBrand brand: BoatBrand.values()){
			if(brand.shortName.equals(shortName)){
				return brand;
			}
		}
		return null;
	}
	
	BoatBrand(String shortName, String iconFileName, String dataFileName){
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
