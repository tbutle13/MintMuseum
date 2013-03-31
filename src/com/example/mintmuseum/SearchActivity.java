package com.example.mintmuseum;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
public class SearchActivity extends Activity implements OnQueryTextListener {

	private TextView mTextView;
	private ListView mListView;
	private SearchView mSearchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		mTextView = (TextView) findViewById(R.id.banner);
		mListView = (ListView) findViewById(R.id.results_list);
		handleIntent(getIntent());
			
	}
	/**
	 * Method for handling intents
	 * @param intent Intent to be handled
	 */
	private void handleIntent(Intent intent) {
		// TODO Auto-generated method stub
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			Intent paintingIntent = new Intent(this, PaintingActivity.class);
			paintingIntent.setData(intent.getData());
			startActivity(paintingIntent);
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
		String[] str = { "Artowrk1", "Some other piece", "This art", "Mona Lisa" };
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, str);
		mListView.setAdapter(aa);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}

            }
        });
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.search_bar2:
				onSearchRequested();
				Log.v("Search", "TEst");
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

}
