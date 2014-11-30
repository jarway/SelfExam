package com.jarway.appbasics.draw;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AnimShapeSurfView extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = "appbasics.AnimShapeSurfView";
	private Paint mFgPaint;
	private float mCenterX, mCenterY, mLineLen = 0;
	private int mDegree = 0;
	private Timer mTimer = new Timer();
	private SurfaceHolder mSurfHolder;
	//private Handler mHandler = new Handler();
	
	private TimerTask mTmrTask = new TimerTask() {
		@Override
		public void run() {
			Log.i(TAG, "run in mTmrTask");
			Canvas canvas = null;
			try {
				canvas = mSurfHolder.lockCanvas();
				if (canvas != null)
					draw(canvas); //can be invoked in another thread
			}
			finally {
				if (canvas != null)
					mSurfHolder.unlockCanvasAndPost(canvas);
			}
//			mHandler.post(new Runnable() {
//				@Override
//				public void run() {
//					Log.i(TAG, "run in mHandler");
//				}
//			});
		}
	};
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Log.i(TAG, "draw");
		canvas.drawColor(0xFFFFFFFF);
		
		float lenX = mLineLen * (float)Math.cos(mDegree * Math.PI / 180);
		float lenY = mLineLen * (float)Math.sin(mDegree * Math.PI / 180);
		
		canvas.drawLine(mCenterX + lenX / 2, mCenterY + lenY / 2,
				mCenterX - lenX / 2, mCenterY - lenY / 2, mFgPaint);
		mDegree = (mDegree + 2) % 360;
	}
	
	public AnimShapeSurfView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mFgPaint = new Paint();
		mFgPaint.setAntiAlias(true);
		mFgPaint.setColor(Color.RED);
		mFgPaint.setStrokeWidth(2);
		
		mSurfHolder = getHolder();
		mSurfHolder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surfaceCreated");
		mTimer.schedule(mTmrTask, 0, 50);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i(TAG, "surfaceChanged");
		
		mCenterX = width / 2;
		mCenterY = height / 2;
		mLineLen = (float)Math.sqrt(width * width + height * height);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i(TAG, "surfaceDestroyed");
		mTimer.cancel();
	}

}
