package com.rickyaut.gallery;

import java.io.Serializable;
import java.util.Date;

public class Story implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7160380840420115252L;
	
	private String title;
	private String link;
	private Date date;
	private String description;
	public Story() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Story(String title, String link, Date date, String description) {
		this();
		this.title = title;
		this.link = link;
		this.date = date;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
