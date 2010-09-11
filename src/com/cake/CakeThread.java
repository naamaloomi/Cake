package com.cake;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

class CakeThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private CakeView view;
	private boolean isRunning = false;
	private Ball ball;
	private final float dt = 0.1f;

	public CakeThread(SurfaceHolder surfaceHolder, CakeView panel) {
		this.surfaceHolder = surfaceHolder;
		view = panel;
		ball = new Ball(); 
		ball.pos_x = 50;
		ball.pos_y = 50;
		ball.vel_x = 5;
		ball.vel_y = 10;
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
					handleCollisions();
					view.updateBall((int)ball.pos_x, (int)ball.pos_y);
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
		ball.update(dt);
	}

	private void handleCollisions() {
	
		if (ball.pos_x <= 0 || ball.pos_x > (240 - view.getBallWidth()))
			ball.vel_x *= -1;
		if (ball.pos_y <= 0 || ball.pos_y > (320 - view.getBallHeight()))
			ball.vel_y *= -1;
	
	}
}
