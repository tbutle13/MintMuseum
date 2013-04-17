package com.example.mintmuseum;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class SearchActivity extends Activity implements OnQueryTextListener, OnClickListener, OnItemClickListener{

	private TextView mTextView;
	private ListView mListView;
	private SearchView mSearchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		dbHelper.addArtwork(new ArtWork("12!@3", "test", "artist"));
		dbHelper.addArtwork(new ArtWork("1034801234", "another help", "lols"));
		dbHelper.addArtwork(new ArtWork("109283091283", "slkjdafl", "balls"));
		mTextView = (TextView) findViewById(R.id.banner);
		mListView = (ListView) findViewById(R.id.results_list);
		Button tempBtn = (Button) findViewById(R.id.button_search);
		tempBtn.setOnClickListener(this);
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
		//For prototype will replace with db operations
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		List<String> artworks = new ArrayList<String>();
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
	
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		Log.v("query","onQueryTextChange");
		mTextView.setText("Searching for " + newText);
		return false;
	}
	
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		Log.v("query", "onQueryTextSubmit");
		return false;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		if (v.getId() == R.id.results_list) {
			Log.w("test", "test");
		}
	}
	/**
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Log.v("lols", "lols");
		//startActivity(new Intent(this, PaintingActivity.class));
		
	}
	
}
	
	


