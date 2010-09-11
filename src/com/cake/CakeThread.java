package com.cake;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

class CakeThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private CakeView view;
	private boolean isRunning = false;

	private float ballx;
	private float bally;
	private float ballVelx;
	private float ballVely;
	private final float dt = 0.1f;

	public CakeThread(SurfaceHolder surfaceHolder, CakeView panel) {
		this.surfaceHolder = surfaceHolder;
		view = panel;
		ballx = 50;
		bally = 50;
		ballVelx = 10;
		ballVely = 10;
	}

	public void setRunning(boolean r) {
		isRunning = r;
	}

	@Override
	public void run() {
		isRunning = true;
		Canvas c;
		while (isRunning) {
			c = null;
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					calcBallPos();
					view.updateBall((int)ballx, (int)bally);
					view.onDraw(c);
				}
			} finally {
				// do this in a finally so that if an exception is thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}

	private void calcBallPos() {
		ballx = ballx + ballVelx * dt;
		bally = bally + ballVely * dt;

		if (ballx < 10 || ballx > 230)
			ballVelx *= -1;
		if (bally < 10 || bally > 320)
			ballVely *= -1;
	}
}
