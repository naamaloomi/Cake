package com.cake; 

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class CakeView extends SurfaceView implements SurfaceHolder.Callback {
	private CakeThread thread;
	private int ballx;
	private int bally;
	private Paint circlePaint = new Paint();
	private Paint textPaint = new Paint();
	private Bitmap bitmapBall;

	public CakeView(Context context) {
		super(context);
		getHolder().addCallback(this);
		thread = new CakeThread(getHolder(), this);
		bitmapBall = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
		initPaints();
	}

	private void initPaints() {
		circlePaint.setARGB(255, 255, 0, 0);
		circlePaint.setAntiAlias(true);
		circlePaint.setStyle(Style.STROKE);
		circlePaint.setStrokeWidth(20);
		textPaint.setARGB(255, 0, 0, 255);
		textPaint.setTextSize(16);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawRGB(0,0,0);
		canvas.save();
		canvas.translate(ballx,bally);
		canvas.drawBitmap(bitmapBall, 0, 0, circlePaint);
		canvas.restore();
		canvas.drawText("X: " + Accelerometer.getX() + "\nY: " + Accelerometer.getY(), 100, 200, textPaint);
	}

	public void updateBall(int x, int y) {
		ballx = x;
		bally = y;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// simply copied from sample application LunarLander:
		// we have to tell thread to shut down & wait for it to finish, or else
		// it might touch the Surface after we return and explode
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	}

	public int getBallWidth() {
		return bitmapBall.getWidth();
	}

	public int getBallHeight() {
		return bitmapBall.getHeight();
	}
}
