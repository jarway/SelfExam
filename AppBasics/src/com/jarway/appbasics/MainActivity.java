package com.jarway.appbasics;

import com.jarway.appbasics.slide.SlideActivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
	private static final String TAG = "appbasics.MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
    	Log.i(TAG, "onDestroy");
    	super.onDestroy();
    }
    
    public void onStartAlarmSvcClick(View view) {
    	Log.i(TAG, "onStartAlarmSvcClick");
    	Intent svcIntent = new Intent(this, AlarmService.class);
    	startService(svcIntent);
    }

    public void onStopAlarmSvcClick(View view) {
    	Log.i(TAG, "onStopAlarmSvcClick");
    	Intent svcIntent = new Intent(this, AlarmService.class);
    	stopService(svcIntent);
    }
    
    public void onDrawableActClick(View view) {
    	Log.i(TAG, "onDrawableActClick");
    	Intent intent = new Intent(this, DrawableActivity.class);
    	startActivity(intent);
    }
    
    public void onCrossfadeActClick(View view) {
    	Log.i(TAG, "onCrossfadeActClick");
    	Intent intent = new Intent(this, CrossfadeActivity.class);
    	startActivity(intent);
    }
    
    public void onSlideActClick(View view) {
    	Log.i(TAG, "onSlideActClick");
    	Intent intent = new Intent(this, SlideActivity.class);
    	startActivity(intent);
    }
}
