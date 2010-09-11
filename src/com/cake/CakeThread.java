package com.cake;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

class CakeThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private CakeView view;
	private boolean isRunning = false;


	
	private Ball ball;
	private Paddle paddle;
	private List<Body> bricks;

	private final float dt = 0.1f;
	private Bitmap bitmapBackground;
	private Paint paintBackground;

	public CakeThread(SurfaceHolder surfaceHolder, CakeView panel, Context context) {
		bitmapBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		paintBackground = new Paint();

 	    bricks = new ArrayList<Body>(10);
		bricks.add(new Brick(10, 10, new Paint(), BitmapFactory.decodeResource(context.getResources(), R.drawable.brick1)));
		ball = new Ball(50,50, new Paint(), BitmapFactory.decodeResource(context.getResources(), R.drawable.ball));
		ball.vel_x = 5;
		ball.vel_y = 10;
		paddle = new Paddle(120, new Paint(), BitmapFactory.decodeResource(context.getResources(), R.drawable.paddel_basic));
<<<<<<< HEAD

=======
		ball.vel_x = 3;
>>>>>>> 7cfd3af293811ba3e364a95b1068bd6537549d9d
		this.surfaceHolder = surfaceHolder;
		view = panel;
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
<<<<<<< HEAD
					c.drawBitmap(bitmapBackground, 0, 0, paintBackground);
=======
					c.drawRGB(0,0,0);
					for (Body b : bricks) {
						b.update(dt);
						b.draw(c);
					}
>>>>>>> 7cfd3af293811ba3e364a95b1068bd6537549d9d
					paddle.update(dt);
					paddle.draw(c);
					ball.update(dt);
					ball.draw(c);

					handleCollisions();

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
		ball.handleWalls(240,320);
		paddle.handleWalls(240,320);

		// Handle collisions between ball-paddle and ball-bricks
	}
}
