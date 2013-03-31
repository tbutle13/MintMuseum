package com.example.mintmuseum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class MuseumDbAdapter {
	
	


	public static final int DATABASE_VERISON = 1;
	public static final String DATABASE_NAME = "MintMuseum.db";
	public static final String FTS_VIRTUAL_TABLE = "Artwork";
	public static final String SEARCH_VALUE = "SearchValue";
	private static final String TAG = "MuseumDBHelper";

	private SQLiteDatabase mDb;
	public static final String DATABASE_CREATE = 
			"CREATE VIRUTAL TABLE " + FTS_VIRTUAL_TABLE + " USING fts3(" +
					ArtWork.TABLE_NAME_TITLE + "," +
					ArtWork.TABLE_NAME_DESCRIPTION + "," +
					ArtWork.TABLE_NAME_ARTIST +"," +
					SEARCH_VALUE + "," +
					" UNIQUE (" +ArtWork.TABLE_NAME_ID+ "));";
	
	private Context mCtx;
	private DatabaseHelper mDbHelper;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERISON);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Log.w(TAG, DATABASE_CREATE);
			db.execSQL(DATABASE_CREATE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(TAG, "Upgrading");
			db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
			onCreate(db);
		}
	}
	public MuseumDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}
	
	public MuseumDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		if(mDbHelper != null) {
			mDbHelper.close();
		}
	}
	
	public long createArtwork(String title, String description, String artist, String id) {
		ContentValues initialValues = new ContentValues();
		String searchValue = title + " " +
							description + " " +
							artist + " " +
							id;
		initialValues.put(ArtWork.TABLE_NAME_TITLE, title);
		initialValues.put(ArtWork.TABLE_NAME_DESCRIPTION, description);
		initialValues.put(ArtWork.TABLE_NAME_ARTIST, artist);
		initialValues.put(ArtWork.TABLE_NAME_ID, id);
		initialValues.put(SEARCH_VALUE, searchValue);
		
		return mDb.insert(FTS_VIRTUAL_TABLE, null, initialValues);
	}
	
	public Cursor searchArtwork(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		String query = "SELECT docid as _id," +
		ArtWork.TABLE_NAME_ID + "," +
		ArtWork.TABLE_NAME_TITLE + "," +
		ArtWork.TABLE_NAME_DESCRIPTION + ","+
		ArtWork.TABLE_NAME_ARTIST +
		" from " + FTS_VIRTUAL_TABLE +
		" where " + SEARCH_VALUE + " MATACH '" + inputText +"';";
		
		Log.w(TAG, query);
		Cursor mCursor = mDb.rawQuery(query, null);
		if (mCursor != null)
			mCursor.moveToFirst();
		
		return mCursor;
		
	}
}
	
	

