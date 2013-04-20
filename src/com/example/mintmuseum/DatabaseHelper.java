package com.example.mintmuseum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
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
	
	private static final String ART_TABLE_CREATE = "CREATE TABLE " + TABLE_ART + " ("
			+ KEY_ID + " INTEGER FINAL KEY, " + ART_KEY_NAME + " TEXT NOT NULL, " + ART_KEY_ARTIST + " TEXT NOT NULL" +
					ART_KEY_DESCRIPTION + " TEXT);"; 
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ART_TABLE_CREATE);
		
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
	public void addArtwork(ArtWork art) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put(ART_KEY_NAME, art.getName());
		cv.put(ART_KEY_ARTIST, art.getArtist());
		cv.put(ART_KEY_DESCRIPTION, art.getDescription());
		
		db.insert(TABLE_ART, null, cv);
		db.close();
	}
	
	/**
	 * Retrieve artwork from database
	 * @param name name of the artwork
	 * @return artwork artwork object
	 */
	public List<ArtWork> getArtwork(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ART, new String[] { KEY_ID,
				ART_KEY_NAME}, ART_KEY_NAME + "=?",
				new String[]{ name }, null, null, null, null);
		
		List<ArtWork> artwork = new ArrayList<ArtWork>();
		if ( cursor != null) cursor.moveToFirst();
		while(cursor.moveToNext()) {
			Log.d("getArtwork", cursor.getString(1));
			artwork.add(new ArtWork(cursor.getString(0), cursor.getString(1), "test", "test"));
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
				
				artList.add(artwork);
			} while (cursor.moveToNext());
		}
		
		return artList;
	}
	
	public String getTagValue(String name, Element e) {
		NodeList nList = e.getElementsByTagName(name).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		
		return nValue.getNodeValue();
	}
	
	/**
	 * Method for retrieving xml from web compoents
	 * @return 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public NodeList getXML() throws IOException, ParserConfigurationException, SAXException {
	
		URLConnection test = new URL("http://webpages.uncc.edu/~cburke16/test.xml").openConnection();
	
		File tempFile = new File("temp");
		FileWriter fw = new FileWriter(tempFile);
		
		//Use tidy to fix any xml problems and write xml to a file
		Tidy tidy = new Tidy();
		tidy.setXmlTags(true);
		tidy.parse(test.getInputStream(), fw);
		
		DocumentBuilder docBuild = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuild.parse(tempFile);
		doc.getDocumentElement().normalize();
		
		return doc.getElementsByTagName("painting");
	}
	
	/**
	 * Adds elements from xml retreived from web to update database
	 * @param list NodeList from xml
	 */
	public void updateDb(NodeList list) {
		
		for (int i = 0; i < list.getLength(); i++) {
			Node mNode = list.item(i);
			if (mNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elm = (Element) mNode;
				ArtWork art = new ArtWork(getTagValue("ID", elm), getTagValue("Title", elm), getTagValue("Description", elm), getTagValue("Artist", elm));
				this.addArtwork(art);
			}
			
		}
		
	}

}
