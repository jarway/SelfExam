package com.jarway.appbasics.draw;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AnimShapeView extends View {
	private static final String TAG = "appbasics.AnimShapeView";
	private Paint mFgPaint;
	private float mCenterX, mCenterY, mLineLen = 0;
	private int mDegree = 0;
	private Timer mTimer = new Timer();
	private Handler mHandler = new Handler();
	
	private TimerTask mTmrTask = new TimerTask() {
		@Override
		public void run() {
			Log.i(TAG, "run in mTmrTask");
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					Log.i(TAG, "run in mHandler");
					AnimShapeView.this.invalidate(); //must run in main thread
				}
			});
		}
	};
	
	protected void onDetachedFromWindow() {
		Log.i(TAG, "onDetachedFromWindow");
		mTimer.cancel();
	}
	
	public AnimShapeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mFgPaint = new Paint();
		mFgPaint.setAntiAlias(true);
		mFgPaint.setColor(Color.BLACK);
		mFgPaint.setStrokeWidth(2);
		
		mTimer.schedule(mTmrTask, 0, 50);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i(TAG, "onDraw");
		
		float lenX = mLineLen * (float)Math.cos(mDegree * Math.PI / 180);
		float lenY = mLineLen * (float)Math.sin(mDegree * Math.PI / 180);
		
		canvas.drawLine(mCenterX + lenX / 2, mCenterY + lenY / 2,
				mCenterX - lenX / 2, mCenterY - lenY / 2, mFgPaint);
		mDegree = (mDegree + 2) % 360;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i(TAG, "onSizeChanged");
		
		mCenterX = w / 2;
		mCenterY = h / 2;
		mLineLen = (float)Math.sqrt(w * w + h * h);
	}

}
