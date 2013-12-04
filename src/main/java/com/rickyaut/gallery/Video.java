package com.rickyaut.gallery;

import java.io.Serializable;

public class Video implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7999974676604685716L;
	private String youtubeID;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYoutubeID() {
		return youtubeID;
	}

	public void setYoutubeID(String youtubeID) {
		this.youtubeID = youtubeID;
	}
}
