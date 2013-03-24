package com.example.mintmuseum;

import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;

public class PaintingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_painting);
		ImageView painting = (ImageView) findViewById(R.id.painting);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_painting, menu);
		return true;
	}

}
