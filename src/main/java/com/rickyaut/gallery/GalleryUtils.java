package com.rickyaut.gallery;

import org.apache.commons.lang.StringUtils;

public class GalleryUtils {
	public static String toStandardName(String vehicleName){
		String standardName = vehicleName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "-");
		while(standardName.endsWith("-")) standardName = StringUtils.removeEnd(standardName, "-");
		while(standardName.startsWith("-")) standardName = StringUtils.removeStart(standardName, "-");
		return standardName;
	}
}
