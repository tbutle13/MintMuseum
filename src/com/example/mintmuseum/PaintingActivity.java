package com.example.mintmuseum;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

public class PaintingActivity extends Activity{
	Fragment mFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_painting);	
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//actionBar.setDisplayShowTitleEnabled(false);
		
		Tab tab = actionBar.newTab().setText("Artwork")
				.setTabListener(new MyTabListener<PaintingFragment>(this, "Painting", PaintingFragment.class));
		
		tab = actionBar.newTab().setText("Info")
				.setTabListener(new MyTabListener<InfoFragment>(this, "Info", InfoFragment.class));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_painting, menu);
		return true;
	}
	
	public static class MyTabListener<T extends Fragment> implements TabListener {
		private Fragment mFragment;
		private Activity mActivity;
		private Class<T> mClass;
		private String mTag;
		public MyTabListener(Activity activity, String tag, Class<T> clz){
			mActivity = activity;
			mClass = clz;
			mTag = tag;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(mFragment == null) {
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
				ft.attach(mFragment);
			}
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			if(mFragment != null) {
				ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
				ft.detach(mFragment);
			}
		
			}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
	
		
	}
	

}	

