package com.example.mintmuseum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class JoinActivity extends Activity implements OnClickListener {
	WebParser wp;
	Button mButton;
	TextView mTextView;
	ArtWork art;
	Group group;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		group = (Group) getIntent().getSerializableExtra("group");
		mButton = (Button) findViewById(R.id.view_painting);
		mButton.setOnClickListener(this);
		mTextView = (TextView) findViewById(R.id.group_banner);
		mTextView.setText("Welcome to " + group.getName());
		//mButton.setOnClickListener(this);
		
		
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
		case R.id.view_painting:
			mIntent = new Intent(this, PaintingActivity.class);
			mIntent.putExtra("art", group.getArt());
			startActivity(mIntent);
			break;
		}
	}

}
