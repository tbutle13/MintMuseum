package com.example.mintmuseum;

public class ArtWork {
	private String name;
	private String artist;
	private String id;
	private String description;
	private String type;
	
	public ArtWork(){}
	public ArtWork (String id, String name, String description, String artist, String type) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.description = description;
		this.type = type;
	}
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getArtist() { return artist; }
	
	public void setArtist(String artist) { this.artist = artist; }
	
	public String getID() { return id; }
	
	public void setID(String id)  { this.id = id; }
	
	public String getDescription() { return this.description; }
	
	public void setDescriptiion(String description) { this.description = description; }
	
	public String getType(){ return this.type; }
	
	public void setType(String type) { this.type = type; }
}
