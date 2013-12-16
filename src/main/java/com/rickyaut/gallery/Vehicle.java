package com.rickyaut.gallery;

import java.io.Serializable;
import java.util.List;

public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8574618973769860977L;
	private String name;
	private String url;
	private String thumbnailUrl;
	private List<Image> images;
	private List<Image> exteriorImages;
	private List<Image> interiorImages;
	private List<Video> videos;
	private List<Story> stories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Image> getExteriorImages() {
		return exteriorImages;
	}

	public void setExteriorImages(List<Image> exteriorImages) {
		this.exteriorImages = exteriorImages;
	}

	public List<Image> getInteriorImages() {
		return interiorImages;
	}

	public void setInteriorImages(List<Image> interiorImages) {
		this.interiorImages = interiorImages;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

}
