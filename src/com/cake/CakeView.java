package com.cake; 

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;

class CakeView extends SurfaceView implements SurfaceHolder.Callback {
	private CakeThread thread;

	public CakeView(Context context) {
		super(context);
		getHolder().addCallback(this);
		thread = new CakeThread(getHolder(), this);
	}

	@Override
	public void onDraw(Canvas canvas) {
		Paint circlePaint = new Paint();
		circlePaint.setARGB(255, 255, 0, 0);
		circlePaint.setAntiAlias(true);
		circlePaint.setStyle(Style.STROKE);
		circlePaint.setStrokeWidth(20);
		canvas.drawCircle(50, 50, 10, circlePaint);


	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
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
}
