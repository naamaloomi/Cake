package com.cake;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

class CakeThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private CakeView view;
	private boolean isRunning = false;

	public CakeThread(SurfaceHolder surfaceHolder, CakeView panel) {
		surfaceHolder = surfaceHolder;
		view = panel;
	}

	public void setRunning(boolean r) {
		isRunning = r;
	}

	@Override
	public void run() {
		Canvas c;
		while (isRunning) {
			c = null;
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
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
}
