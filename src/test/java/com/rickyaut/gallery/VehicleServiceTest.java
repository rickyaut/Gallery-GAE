package com.rickyaut.gallery;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class VehicleServiceTest {

	@Test
	public void test() {
		Brand brand = Brand.Toyota;
		URL url = getClass().getClassLoader().getResource("json/"+brand.getDataFileName());
		try {
			Vehicle[] vehicleArray = new ObjectMapper().readValue(url, Vehicle[].class);
			System.out.println(String.format("found %d vehicles for %s", vehicleArray.length, brand.name()));
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
