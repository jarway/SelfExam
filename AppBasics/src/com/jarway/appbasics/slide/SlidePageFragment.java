package com.jarway.appbasics.slide;

import com.jarway.appbasics.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SlidePageFragment extends Fragment {
	private int mPageNumber;
	
	public SlidePageFragment(int pageNumber) {
		super();
		mPageNumber = pageNumber;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup)inflater.inflate(
				R.layout.fragment_slide_page, container, false);
		
		int bgColor;
		switch (mPageNumber) {
		case 0:
			bgColor = 0xffc981db;
			break;
		case 1:
			bgColor = 0xff18cff0;
			break;
		case 2:
			bgColor = 0xff97de62;
			break;
		default:
			bgColor = 0xffffffff;
		}
		rootView.setBackgroundColor(bgColor);
		
		TextView titleText = (TextView)rootView.findViewById(R.id.titleText);
		titleText.setText(getString(R.string.title_template_step, mPageNumber + 1));
		
		return rootView;
	}
}
