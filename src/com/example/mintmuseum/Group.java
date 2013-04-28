package com.example.mintmuseum;

public class Group {
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
