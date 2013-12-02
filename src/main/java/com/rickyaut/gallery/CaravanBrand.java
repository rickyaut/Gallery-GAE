package com.rickyaut.gallery;


public enum CaravanBrand{
	Mustang("mustang", "", "mustang-gallery.json"); 

	private String shortName;
	private String iconFileName;
	private String dataFileName;
	
	public static CaravanBrand findByShortName(String shortName){
		for(CaravanBrand brand: CaravanBrand.values()){
			if(brand.shortName.equals(shortName)){
				return brand;
			}
		}
		return null;
	}
	
	CaravanBrand(String shortName, String iconFileName, String dataFileName){
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
