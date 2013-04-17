package com.example.mintmuseum;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShowPaitingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_paiting);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_paiting, menu);
		return true;
	}

}
