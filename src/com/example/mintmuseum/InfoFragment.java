package com.example.mintmuseum;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		TextView tv = (TextView) getActivity().findViewById(R.id.infoFrag);
		tv.setText("Some info about the painting would go here, like compositional stuff and problem solving");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
		return inflator.inflate(R.layout.info_frag, container, false);
		
	}

}
