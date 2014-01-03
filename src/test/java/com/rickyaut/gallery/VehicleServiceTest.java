package com.rickyaut.gallery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class VehicleServiceTest {

	@Test
	public void testJson() {
		CarBrand brand = CarBrand.Toyota;
		URL url = getClass().getClassLoader().getResource(
				"json/car/" + brand.getDataFileName());
		try {
			List<Vehicle> vehicleArray = new ObjectMapper().readValue(url,
					Maker.class).getVehicles();
			System.out.println(String.format("found %d vehicles for %s",
					vehicleArray.size(), brand.name()));
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testYoutube() throws IOException {
		//
		/*
		 * The YouTube object is used to make all API requests. The last
		 * argument is required, but because we don't need anything initialized
		 * when the HttpRequest is initialized, we override the interface and
		 * provide a no-op function.
		 */
		YouTube youtube = new YouTube.Builder(new NetHttpTransport(),
				new JacksonFactory(), new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				}).setApplicationName("youtube-cmdline-search-sample").build();

		YouTube.Search.List search = youtube.search().list("id,snippet");
		/*
		 * It is important to set your developer key from the Google Developer
		 * Console for non-authenticated requests (found under the API Access
		 * tab at this link: code.google.com/apis/). This is good practice and
		 * increases your quota.
		 */
		search.setKey("AIzaSyBlMiJvaDr-xrpcVH-xOTKP0QaakXJsujI");
		search.setQ("toyota cruze");

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
		search.setMaxResults(10L);
		SearchListResponse searchResponse = search.execute();

		List<SearchResult> searchResultList = searchResponse.getItems();

		if (searchResultList != null) {
			System.out.println(searchResultList);
		} else {
			System.out.println("There were no results for your query.");
		}
	}

	@Test
	public void testNews() throws IOException, ParseException, IllegalArgumentException, FeedException {
		
		URL url = new URL("https://news.google.com/news/feeds?output=rss&hl=en&ned=us&q="
				+ URLEncoder.encode(String.format("%s %s", "Holden", "Caprice"),
						"UTF-8"));
        HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
        // Reading the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpcon));
        List<SyndEntry> entries = feed.getEntries();
        junit.framework.Assert.assertFalse(entries.isEmpty());
	}

}
