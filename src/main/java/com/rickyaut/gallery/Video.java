package com.rickyaut.gallery;

import java.io.Serializable;

public class Video implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7999974676604685716L;
	private String youtubeID;
	private String title;
	private String thumbnailURL;
	private String description;

	public Video() {
		super();
	}

	public Video(String youtubeID, String title, String description, String thumbnailURL) {
		this();
		this.youtubeID = youtubeID;
		this.title = title;
		this.thumbnailURL = thumbnailURL;
	}

	public String getYoutubeID() {
		return youtubeID;
	}

	public void setYoutubeID(String youtubeID) {
		this.youtubeID = youtubeID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailURL() {
		return thumbnailURL;
	}

	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}
}
