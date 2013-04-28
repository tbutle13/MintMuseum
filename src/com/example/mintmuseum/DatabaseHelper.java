package com.example.mintmuseum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "MintMuseum.db";
	private static final String TABLE_ART = "ARTWORK";
	private static final String KEY_ID = "_id";
	private static final String ART_KEY_NAME = "name";
	private static final String ART_KEY_ARTIST = "artits";
	private static final String ART_KEY_DESCRIPTION = "description";
	private static final String ART_KEY_TYPE = "type";
	
	private static final String ART_TABLE_CREATE = "CREATE VIRTUAL TABLE " + TABLE_ART + " USING FTS3 ("
			+ KEY_ID + ", " + ART_KEY_NAME + ", " + ART_KEY_ARTIST + ", " 
			+ ART_KEY_DESCRIPTION  +  ", " + ART_KEY_TYPE + ");";
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ART_TABLE_CREATE);
		
	}
	//Just for help creating mock objects
	public DatabaseHelper makeDatabaseHelper(Context context) {
		return new DatabaseHelper(context);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ART);
		onCreate(db);
		
	}
	
	/**
	 * Add artwork do database
	 * @param art artwork object to add
	 * 
	 */
	public boolean addArtwork(ArtWork art) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_ID, art.getID());
		cv.put(ART_KEY_NAME, art.getName());
		cv.put(ART_KEY_ARTIST, art.getArtist());
		cv.put(ART_KEY_DESCRIPTION, art.getDescription());
		cv.put(ART_KEY_TYPE, art.getType());
		db.insert(TABLE_ART, null, cv);
		db.close();
		return true;
	}
	
	/**
	 * Retrieve artwork from database
	 * @param name name of the artwork
	 * @return artwork artwork object
	 */
	public List<ArtWork> getArtwork(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ART, new String[] { KEY_ID,
				ART_KEY_NAME, ART_KEY_ARTIST, ART_KEY_DESCRIPTION, ART_KEY_TYPE }, ART_KEY_NAME + " MATCH ?",
				new String[]{ name }, null, null, null, null);
		
		List<ArtWork> artwork = new ArrayList<ArtWork>();
		if ( cursor != null) cursor.moveToFirst();
		while(cursor.moveToNext()) {
			Log.d("getArtwork", cursor.getString(1));
			artwork.add(new ArtWork(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), "test"));
		}
		return artwork;
	}

	
	/**
	 * Gets a list of all art in database
	 * @return artList list of all artworks in db
	 */
	public List<ArtWork> getAllArtWork() {
		List<ArtWork> artList = new ArrayList<ArtWork>();
		
		String selectQuery = "SELECT * FROM " + TABLE_ART;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()) {
			do {
				ArtWork artwork = new ArtWork();
				artwork.setID(cursor.getString(0));
				artwork.setName(cursor.getString(1));
				artwork.setArtist(cursor.getString(2));
				artwork.setDescriptiion(cursor.getString(3));
				artwork.setType(cursor.getString(4));
				artList.add(artwork);
			} while (cursor.moveToNext());
		}
		
		return artList;
	}
	/**
	 * Method for updating db from web
	 * @param url url to update database
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void updateDb(String url, File file) throws IOException, ParserConfigurationException, SAXException {
		WebParser wp = new WebParser(file);
		List<ArtWork> ls = ((WebParser) wp).getArt();
		for (ArtWork art : ls) {
			addArtwork(art);
		}
		
	}

}
