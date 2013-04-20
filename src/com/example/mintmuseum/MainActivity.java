package com.example.mintmuseum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create Buttons
		Button scan = (Button) findViewById(R.id.scan);
		Button search = (Button) findViewById(R.id.search);
		Button join = (Button) findViewById(R.id.join);
		
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
			myIntent = new Intent("com.google.zxing.client.android.SCAN");
			myIntent.putExtra("SCAN_MODE","QR_CODE_MODE");
			startActivityForResult(myIntent, 0);
			break;
		case R.id.join:
			myIntent = new Intent();
			startActivity(myIntent);
			
		}
			
	}
	
	public void onActivityResult(int req, int resu, Intent intent) {
		Log.w("LOL", "scan");
		//startActivity(new Intent(this, PaintingActivity.class));
	}

}
