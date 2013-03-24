package com.example.mintmuseum;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener{
	Button scan;
	Button search;
	Button join;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Get the buttons
		scan = (Button) findViewById(R.id.scan);
		search = (Button) findViewById(R.id.search);
		join = (Button) findViewById(R.id.join);
		scan.setOnClickListener(this);
		search.setOnClickListener(this);
		join.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent myIntent;
		switch (v.getId()) {
		
		case R.id.search: 
			myIntent = new Intent(MainActivity.this, SearchActivity.class);
			MainActivity.this.startActivity(myIntent);
			break;
		case R.id.scan:
			// Do the QR Code scan intnet here
			break;
		case R.id.join:
		
			
		}
			
		
	}

}
