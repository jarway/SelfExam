package com.jarway.appbasics;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

public class DrawableActivity extends Activity {
	private static final String TAG = "appbasics.DrawableActivity";
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i(TAG, "handleMessage in Handler");
			Resources res = getResources();
			TransitionDrawable transDrawable =
				(TransitionDrawable)res.getDrawable(R.drawable.transition_drawable);
			
			ImageView transImage = (ImageView)findViewById(R.id.transImage);
			transImage.setImageDrawable(transDrawable);
			transDrawable.startTransition(3000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawable);
		
		ImageView btnImage = (ImageView)findViewById(R.id.btnImage);
		//Bitmap srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.blue_btn);
		Bitmap srcBmp = ((BitmapDrawable)btnImage.getDrawable()).getBitmap();
		Log.i(TAG, "source bitmpa, width:" + srcBmp.getWidth() + ", height:" + srcBmp.getHeight());
		btnImage.setImageBitmap(addGlow(srcBmp));
		
		mHandler.sendEmptyMessageDelayed(0, 2000);
	}
	
	public Bitmap addGlow(Bitmap srcBmp) {
		Bitmap dstBmp = Bitmap.createBitmap(
				srcBmp.getWidth() + 30, srcBmp.getHeight() + 30,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(dstBmp);
		canvas.drawColor(0, PorterDuff.Mode.CLEAR); //setup default color
		
		//create a blur paint for capturing alpha
		Paint blurPaint = new Paint();
		blurPaint.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
		
		int[] offsetXY = new int[2]; //record offset between alphaBmp and srcBmp later
		Bitmap alphaBmp = srcBmp.extractAlpha(blurPaint, offsetXY); //capture alpha into a bitmap
		
		//create a color paint
		Paint glowPaint = new Paint();
		glowPaint.setColor(0xFF000000);
		
		//paint glow on bottom for captured alpha region
		canvas.drawBitmap(alphaBmp, offsetXY[0], offsetXY[1], glowPaint);
		alphaBmp.recycle(); //free memory
		
		//paint image source on top of glow
		canvas.drawBitmap(srcBmp, 0, 0, null); 
		return dstBmp;
	}
}
