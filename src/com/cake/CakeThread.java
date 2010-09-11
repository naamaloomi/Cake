package com.cake;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

class CakeThread extends Thread {
	private SurfaceHolder _surfaceHolder;
	private CakeView view;
	private boolean _run = false;

	public CakeThread(SurfaceHolder surfaceHolder, CakeView panel) {
		_surfaceHolder = surfaceHolder;
		view = panel;
	}

	public void setRunning(boolean run) {
		_run = run;
	}

	@Override
		public void run() {
			Canvas c;
			while (_run) {
				c = null;
				try {
					c = _surfaceHolder.lockCanvas(null);
					synchronized (_surfaceHolder) {
						view.onDraw(c);
					}
				} finally {
					// do this in a finally so that if an exception is thrown
					// during the above, we don't leave the Surface in an
					// inconsistent state
					if (c != null) {
						_surfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}
}
