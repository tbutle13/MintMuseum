package com.example.mintmuseum;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;


public class SearchActivity extends Activity {
	
	//Will be replace by pulling from db
	String[] artwork = new String[] {"Art1", "Art2", "Art3", "Art4"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, artwork);
		AutoCompleteTextView search = (AutoCompleteTextView) findViewById(R.id.search_bar);
		search.setAdapter(aa);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search, menu);
		return true;
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
