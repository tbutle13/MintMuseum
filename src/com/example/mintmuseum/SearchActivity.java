package com.example.mintmuseum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class SearchActivity extends Activity implements OnQueryTextListener, OnClickListener, OnItemClickListener{

	private TextView mTextView;
	private ListView mListView;
	private SearchView mSearchView;
	private Intent mIntent;
	private DatabaseHelper dbHelper;
	private List<String> artworks;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		File files = this.getFilesDir();
		
		//Stuff for developing
	
		dbHelper = new DatabaseHelper(this);
		dbHelper.addArtwork(new ArtWork("test", "test", "DaVinci", "A Cool painting", "painting"));
		/*try {
			//dbHelper.updateDb("test", files);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		mTextView = (TextView) findViewById(R.id.banner);
		mListView = (ListView) findViewById(R.id.results_list);
	
		mListView.setOnItemClickListener(this);
		handleIntent(getIntent());
		
		
	}
	/**
	 * Method for handling intents
	 * @param intent Intent to be handled
	 */
	private void handleIntent(Intent intent) {
	
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			//Intent paintingIntent = new Intent(this, PaintingActivity.class);
			//paintingIntent.setData(intent.getData());
			//startActivity(paintingIntent);
		} else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			Log.v("Debug", "Search started");
			showResults(query);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search, menu);
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
			mSearchView = (SearchView) menu.findItem(R.id.search_bar2).getActionView();
			mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
			mSearchView.setIconifiedByDefault(false);
			mSearchView.setOnQueryTextListener(this);
			
		}
		return true;
	}
	/**
	 * Search the database for the artwork name
	 * @param query The search query
	 */
	
	public void showResults(String query) {
		
		dbHelper = new DatabaseHelper(this);
		artworks = new ArrayList<String>();
		for(ArtWork art : dbHelper.getArtwork(query)) {
			artworks.add(art.getName());
		}
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, artworks);
		mListView.setAdapter(aa);
		
			
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.search_bar2:
				Log.v("Search", "made bar");
				return true;
			default:
				return false;
		}	
	}
	//Android function for 
	@Override
	public boolean onQueryTextChange(String newText) {
		//Log.v("query","onQueryTextChange");
		mTextView.setText("Searching for " + newText);
		return false;
	}
	
	@Override
	public boolean onQueryTextSubmit(String query) {
		Log.v("query", "submitted");
		
		showResults(query);
		return false;
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.results_list) {
			mIntent = new Intent(this, PaintingActivity.class);
			startActivity(mIntent);
			//Log.w("test", "test");
		}
	}
	/**
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		mIntent = new Intent(this, PaintingActivity.class);
		String name = ((TextView)view).getText().toString();
		ArtWork mArt = (ArtWork) dbHelper.getArtwork(name).get(0);
		mIntent.putExtra("art", mArt);
		startActivity(mIntent);
		//Log.v("lols", "lols");
	
		
	}
	
}
	
	


