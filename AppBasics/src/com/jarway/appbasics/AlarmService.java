package com.jarway.appbasics;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

public class AlarmService extends Service {
	private static final String TAG = "appbasics.AlarmService";
	
	private Timer mTimer = new Timer();
	private TimerTask mTmrTask;
	
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		super.onCreate();
	}
	
	private void showNotification() {		
		NotificationManager notifiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE); 
		Notification.Builder notifiBuilder = new Notification.Builder(this);
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendIntent = PendingIntent.getActivity(
				this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notifiBuilder.setContentIntent(pendIntent)
			.setDefaults(Notification.DEFAULT_ALL)
			.setSmallIcon(R.drawable.ic_launcher)
			//.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher))
			.setTicker("Alarm...")
			.setContentTitle("Alarm from App Basics")
			.setContentText(new Date().toString())
			.setAutoCancel(true);
		
		Notification notifi = notifiBuilder.getNotification();
		notifiManager.notify(0, notifi);
		
		// Old usage before API 11
		/*
    	Intent intent = new Intent(this, MainActivity.class);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
    	
    	Notification notification = new Notification();
    	notification.icon = R.drawable.ic_launcher;
    	notification.tickerText = "Alarm from App Basics";
    	notification.defaults = Notification.DEFAULT_ALL;
    	notification.setLatestEventInfo(this, "Alarm from App Basics", new Date().toString(), pendingIntent);
    	
    	NotificationManager notificationMngr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    	notificationMngr.notify(0, notification);
		*/
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		
		if (mTmrTask != null)
			mTmrTask.cancel();
		
		mTmrTask = new TimerTask() {
			@Override
			public void run() {
				Log.i(TAG, "run in TimerTask");
				showNotification();
			}
		};
		
		mTimer.schedule(mTmrTask, 5000);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
