package com.example.mintmuseum;

import android.provider.BaseColumns;

public class ArtWork implements BaseColumns {
	public static final String TABLE_NAME = "artwork";
	public static final String TABLE_NAME_ID = "art_id";
	public static final String TABLE_NAME_TITLE = "art_title";
	public static final String TABLE_NAME_ARTIST = "art_artist";
	public static final String TABLE_NAME_DESCRIPTION = "art_description";
	
	private ArtWork() {}
}
