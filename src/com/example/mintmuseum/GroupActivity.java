package com.example.mintmuseum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GroupActivity extends Activity implements OnClickListener, OnItemClickListener {
	WebParser wp;
	ListView mListView;
	List<Group> groups;
	List<String> names;
	Group group;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		wp = new WebParser();
		
		groups = wp.getClasses();
		names = new ArrayList<String>();
		
		for (Group g : groups) {
			names.add(g.getName());
		}
		mListView = (ListView) findViewById(R.id.results_list);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, names);
		mListView.setAdapter(aa);
		mListView.setOnItemClickListener(this);
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
	public Group getGroup(String name) {
		Group g = new Group();
		
		for (Group gg : groups) {
			if (gg.getName().equalsIgnoreCase(name)) g = gg;
		}
		return g;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent mIntent;
		Log.d("item click", "you click it");
		mIntent = new Intent(this, JoinActivity.class);
		group = getGroup(((TextView)view).getText().toString());
		mIntent.putExtra("group", group);
		startActivity(mIntent);
	}

}
