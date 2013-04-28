package com.example.mintmuseum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GroupActivity extends Activity implements OnClickListener, OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent mIntent;
		switch (view.getId()) {
			case R.id.join:
				mIntent = new Intent(this, JoinActivity.class);
				startActivity(mIntent);
			case R.id.create:
				mIntent = new Intent(this, CreateActivity.class);
				startActivity(mIntent);
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent mIntent;
		mIntent = new Intent(this, PaintingActivity.class);
		String name = ((TextView)view).getText().toString();
		mIntent.putExtra("artwork", name);
		startActivity(mIntent);
	}

}