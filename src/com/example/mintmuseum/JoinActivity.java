package com.example.mintmuseum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JoinActivity extends Activity implements OnClickListener {
	WebParser wp;
	Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
	
		mButton = (Button) findViewById(R.id.join);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join, menu);
		return true;
	}



	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent mIntent;
		switch(view.getId()) {
		case R.id.join:
			mIntent = new Intent(this, PaintingActivity.class);
			mIntent.putExtra("name", mButton.getText());
			break;
		}
	}

}
