package com.example.mintmuseum;

import java.util.List;

public class Artist {
	private String name;
	private String bio;
	private List<ArtWork> artworks;
	private String id;
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getBio() { return bio; }
	
	public void setBio(String bio) { this.bio = bio; }
	
	public List<ArtWork> getArtWork() { return artworks; }
	
	public void setArtWork(List<ArtWork> art) { this.artworks = art; }
	
	
	public String getID() { return id; }
	
	public void setID(String id)  { this.id = id; }

}
