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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		
		Tab tab = actionBar.newTab();
		tab.setText("painting");
		TabListener<PaintingFragment> tl = new TabListener<PaintingFragment>(this,
				"painting", PaintingFragment.class);
		tab.setTabListener(tl);
		actionBar.addTab(tab);

		
		tab = actionBar.newTab();
		tab.setText("Info");
		TabListener<InfoFragment> tl2 = new TabListener<InfoFragment>(this,
				"info", InfoFragment.class);
		tab.setTabListener(tl2);
		actionBar.addTab(tab);
		
		tab = actionBar.newTab();
		tab.setText("Artist");
		TabListener<ArtistFragment> tl3 = new TabListener<ArtistFragment>(this,
				"Artist", ArtistFragment.class);
		tab.setTabListener(tl3);
		actionBar.addTab(tab);

	}

	private class TabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			Bundle bundle = new Bundle();
			bundle.putString("artwork", getIntent().getStringExtra("name"));
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				mFragment.setArguments(bundle);
				
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// User selected the already selected tab. Usually do nothing.
		}
	}



	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_painting, menu);
		return true;
	}

}	

