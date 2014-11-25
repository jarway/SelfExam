package com.jarway.appbasics;

import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class CrossfadeActivity extends Activity {
	private static final String TAG = "appbasics.CrossfadeActivity";
	private View mContentView;
	private View mLoadingView;
	private int mShortAnimationDuration;
		
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i(TAG, "handleMessage in mHandler");
			crossfade();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.activity_crossfade);
		
		mContentView = findViewById(R.id.content);
		mLoadingView = findViewById(R.id.loading_spinner);
		
		mContentView.setVisibility(View.GONE);
		mShortAnimationDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);
		
		mHandler.sendEmptyMessageDelayed(0, 2000);
	}

	private void crossfade() {
		mContentView.setAlpha(0f);
		mContentView.setVisibility(View.VISIBLE);
		
		mContentView.animate()
			.alpha(1f)
			.setDuration(mShortAnimationDuration)
			.setListener(null);
		mLoadingView.animate()
			.alpha(0f)
			.setDuration(mShortAnimationDuration)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoadingView.setVisibility(View.GONE);
				}
			});
	}
}
