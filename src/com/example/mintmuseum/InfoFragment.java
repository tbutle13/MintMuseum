package com.example.mintmuseum;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {
	TextView mTextView;
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
		View v = inflator.inflate(R.layout.info_frag, container, false);
		mTextView = (TextView) v.findViewById(R.id.infoFrag);
		String description = getArguments().getString("description");
		mTextView.setText(description);
		return v;
	}

}
