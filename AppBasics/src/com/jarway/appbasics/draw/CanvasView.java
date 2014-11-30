package com.jarway.appbasics.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CanvasView extends View {
	private static final String TAG = "appbasics.CanvasView";
	private Paint mPaint = new Paint();
	private RectF mCanvasRectF;
	
	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i(TAG, "onDraw, canvas width:" + canvas.getWidth() + ", height:" + canvas.getHeight());
		
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.CYAN);
		
		//mCanvasRectF = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.drawOval(mCanvasRectF, mPaint);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i(TAG, "onSizeChanged, w:" + w + ", h:" + h + ", oldw:" + oldw + ", oldh:" + oldh);
		mCanvasRectF = new RectF(0, 0, w, h);
	}
}
