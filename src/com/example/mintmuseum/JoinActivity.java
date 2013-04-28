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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		Group group = (Group) getIntent().getSerializableExtra("group");
		mButton = (Button) findViewById(R.id.join);
		mTextView = (TextView) findViewById(R.id.group_banner);
		mTextView.setText("Welcome to " + group.getName());
		
		
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
			startActivity(mIntent);
			break;
		}
	}

}
