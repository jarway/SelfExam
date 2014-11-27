package com.jarway.appbasics;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CardFlipActivity extends Activity implements OnBackStackChangedListener {
	private static final String TAG = "appbasics.CardFlipActivity";
	private boolean mShowingBack = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_flip);
		
		if (savedInstanceState == null) {
			getFragmentManager()
				.beginTransaction()
				.add(R.id.container, new CardFrontFragment())
				.commit();
		}
		else {
			mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
		}
		
		getFragmentManager().addOnBackStackChangedListener(this);
	}
	
	public void onContainerClick(View view) {
		Log.i(TAG, "onContainerClick");
		flipCard();
	}
	
	private void flipCard() {
		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}
		
		mShowingBack = true;
		getFragmentManager()
			.beginTransaction()
			.setCustomAnimations(
					R.animator.card_flip_right_in, R.animator.card_flip_right_out,
					R.animator.card_flip_left_in, R.animator.card_flip_left_out)
			.replace(R.id.container, new CardBackFragment())
			.addToBackStack(null)
			.commit();
	}
	
	@Override
	public void onBackStackChanged() {
		Log.i(TAG, "onBackStackChanged, entry count:" + getFragmentManager().getBackStackEntryCount());
		mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
	}
	
	public class CardFrontFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_front, container, false);
		}
	}
	
	public class CardBackFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_back, container, false);
		}
	}
}
