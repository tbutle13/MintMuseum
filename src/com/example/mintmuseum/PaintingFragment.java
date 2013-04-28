package com.example.mintmuseum;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class PaintingFragment extends Fragment {
	private static final String IMAGES_DIR = "res/drawable";
	private Context context;
	private ImageView mImView;
	

	public static PaintingFragment newInstance(int index) {
		PaintingFragment f = new PaintingFragment();
		
		return f;
		
	}
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflator.inflate(R.layout.painting_frag, container, false);
		mImView = (ImageView)v.findViewById(R.id.painting);
		ArtWork art = (ArtWork) getArguments().getSerializable("artwork");
		
		mImView.setImageResource(getResources().getIdentifier(art.getID(), "drawable", "com.example.mintmuseum"));
		
		return v;
		
	}

}
