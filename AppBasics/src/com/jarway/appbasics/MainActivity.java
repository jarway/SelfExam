package com.jarway.appbasics;

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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
