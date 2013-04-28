package com.example.mintmuseum;

import java.io.Serializable;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1475013205810525502L;
	private String name;
	private ArtWork art;
	
	public Group(){}
	public Group(String name, ArtWork art) {
		this.name = name;
		this.art = art;
	}
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public ArtWork getArt() { return this.art; }
	public void setArt(ArtWork art) { this.art = art; }
}
