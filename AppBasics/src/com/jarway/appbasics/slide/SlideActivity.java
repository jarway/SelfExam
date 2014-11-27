package com.jarway.appbasics.slide;

import com.jarway.appbasics.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class SlideActivity extends FragmentActivity {
	private static final String TAG = "appbasics.SlideActivity";
	private static final int NUM_PAGES = 3;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter; //provide pages to the view pager.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide);
		
		mPager = (ViewPager)findViewById(R.id.pager);
		mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setPageTransformer(true, new ZoomOutPageTransformer());
	}
	
	public void onPageOneClick(View view) {
		Log.i(TAG, "onPageOneClick");
		mPager.setCurrentItem(0);
	}

	public void onPageTwoClick(View view) {
		Log.i(TAG, "onPageTwoClick");
		mPager.setCurrentItem(1);
	}
	
	public void onPageThreeClick(View view) {
		Log.i(TAG, "onPageThreeClick");
		mPager.setCurrentItem(2);
	}
	
	@Override
	public void onBackPressed() {
		if (mPager.getCurrentItem() == 0)
			super.onBackPressed();
		else
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
	}
	
	private class SlidePagerAdapter extends FragmentStatePagerAdapter {
		public SlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new SlidePageFragment(position);
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
	
}
