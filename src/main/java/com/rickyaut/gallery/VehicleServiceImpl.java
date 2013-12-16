package com.rickyaut.gallery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.appengine.api.memcache.MemcacheService;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class VehicleServiceImpl implements VehicleService {
	private static final Logger logger = Logger.getLogger(VehicleServiceImpl.class.getName());
	@Autowired
	private ObjectMapper jsonObjectMapper;
	
	@Autowired
	private MemcacheService syncCache;

	@Override
	public List<Vehicle> findCarsByBrand(CarBrand brand) {
		String dataFileName = "json/car/"+brand.getDataFileName();
		List<Vehicle> vehicles = (List<Vehicle>)syncCache.get(dataFileName);
		if(vehicles == null){
			URL url = getClass().getClassLoader().getResource(dataFileName);
			try {
				vehicles= jsonObjectMapper.readValue(url, Maker.class).getVehicles();
				syncCache.put(dataFileName, vehicles);
			} catch (JsonProcessingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle getCar(CarBrand brand, String vehicleStandardName) {
		List<Vehicle> vehicles = findCarsByBrand(brand);
		for(Vehicle vehicle: vehicles){
			String standardName = vehicle.getName().toLowerCase().replaceAll(" ", "-");
			if(StringUtils.equals(standardName, vehicleStandardName)){
				return vehicle;
			}
		}
		return null;
	}
	
	@Override
	public List<Video> findCarYoutubeVideos(CarBrand brand,
			String carStandardName) {
		Vehicle car = getCar(brand, carStandardName);
		List<Video> results;
		try {
			results = searchYoutube(String.format("%s %s", brand.name(), car.getName()));
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			results = new ArrayList<Video>();
		}
		return results;
	}
	
	private List<Video> searchYoutube(String term) throws IOException {
		YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				}).setApplicationName("gallery").build();

		YouTube.Search.List search = youtube.search().list("id,snippet");
		/*
		 * It is important to set your developer key from the Google Developer
		 * Console for non-authenticated requests (found under the API Access
		 * tab at this link: code.google.com/apis/). This is good practice and
		 * increases your quota.
		 */
		search.setKey("AIzaSyBlMiJvaDr-xrpcVH-xOTKP0QaakXJsujI");
		search.setQ(term);

		/*
		 * We are only searching for videos (not playlists or channels). If we
		 * were searching for more, we would add them as a string like this:
		 * "video,playlist,channel".
		 */
		search.setType("video");
		/*
		 * This method reduces the info returned to only the fields we need. It
		 * makes things more efficient, because we are transmitting less data.
		 */
		search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
		search.setMaxResults(20L);
		SearchListResponse searchResponse = search.execute();

		List<Video> videos = new ArrayList<Video>();
		List<SearchResult> searchResultList = searchResponse.getItems();
		for(SearchResult result: searchResultList){
			Video video = new Video(result.getId().getVideoId(), result.getSnippet().getTitle(), result.getSnippet().getThumbnails().getDefault().getUrl());
			videos.add(video);
		}
		return videos;
	}

	@Override
	public List<Story> findCarStories(CarBrand brand, String carStandardName) {
		List<Story> stories = new ArrayList<Story>();
		try{
			URL url = new URL("https://news.google.com/news/feeds?output=rss&hl=en&q=" + URLEncoder.encode(String.format("%s %s",
									brand.name(), carStandardName), "UTF-8"));
	        HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
	        SyndFeedInput input = new SyndFeedInput();
	        SyndFeed feed = input.build(new XmlReader(httpcon));
	 
	        for (SyndEntry entry: (List<SyndEntry>)feed.getEntries()) {
	        	stories.add( new Story(entry.getTitle(), entry.getLink(), entry.getPublishedDate(), entry.getDescription().getValue()));
	        }
		}catch(Exception e){
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		return stories;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
			throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	@Override
	public List<Vehicle> findTrucksByBrand(TruckBrand brand) {
		String dataFileName = "json/truck/"+brand.getDataFileName();
		List<Vehicle> vehicles = (List<Vehicle>)syncCache.get(dataFileName);
		if(vehicles == null){
			URL url = getClass().getClassLoader().getResource(dataFileName);
			try {
				Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
				vehicles = Arrays.asList(vehicleArray);
				syncCache.put(dataFileName, vehicles);
			} catch (JsonProcessingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle getTruck(TruckBrand brand, String truckStandardName) {
		List<Vehicle> vehicles = findTrucksByBrand(brand);
		for(Vehicle vehicle: vehicles){
			String standardName = vehicle.getName().toLowerCase().replaceAll(" ", "-");
			if(StringUtils.equals(standardName, truckStandardName)){
				return vehicle;
			}
		}
		return null;
	}

	@Override
	public List<Vehicle> findBoatsByBrand(BoatBrand brand) {
		String dataFileName = "json/boat/"+brand.getDataFileName();
		List<Vehicle> vehicles = (List<Vehicle>)syncCache.get(dataFileName);
		if(vehicles == null){
			URL url = getClass().getClassLoader().getResource(dataFileName);
			try {
				Vehicle[] vehicleArray = jsonObjectMapper.readValue(url, Vehicle[].class);
				vehicles = Arrays.asList(vehicleArray);
				syncCache.put(dataFileName, vehicles);
			} catch (JsonProcessingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle getBoat(BoatBrand brand, String boatStandardName) {
		List<Vehicle> vehicles = findBoatsByBrand(brand);
		for(Vehicle vehicle: vehicles){
			String standardName = vehicle.getName().toLowerCase().replaceAll(" ", "-");
			if(StringUtils.equals(standardName, boatStandardName)){
				return vehicle;
			}
		}
		return null;
	}

}
