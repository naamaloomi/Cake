package com.cake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Vibrator;
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
	private Bitmap bitmapWon;
	private Bitmap bitmapLost;
	private Paint paintBackground;

	public CakeThread(SurfaceHolder surfaceHolder, CakeView panel, Context context) {
		bitmapBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		bitmapWon = BitmapFactory.decodeResource(context.getResources(), R.drawable.cake);
		bitmapLost = BitmapFactory.decodeResource(context.getResources(), R.drawable.lost);
		paintBackground = new Paint();

 	    bricks = new ArrayList<Body>(10);
		Paint p = new Paint();
		for (int j =0; j < 6; j++) {
			for (int i =0; i< 6 ; i++) {
				bricks.add(new Brick(0+ i *40 , 30 + j*10, p, BitmapFactory.decodeResource(context.getResources(), R.drawable.brick1)));
			}
		}
		ball = new Ball(200,200, new Paint(), BitmapFactory.decodeResource(context.getResources(), R.drawable.ball));
		ball.vel_x = -10;
		ball.vel_y = -20;
		paddle = new Paddle(120, new Paint(), BitmapFactory.decodeResource(context.getResources(), R.drawable.paddel_basic));
		paddle.vel_x = 3;
		this.surfaceHolder = surfaceHolder;
		view = panel;
	}

	public void setRunning(boolean r) {
		isRunning = r;
	}

	@Override
	public void run() {
		isRunning = true;
		Canvas c = null;
		boolean won = false;
		while (isRunning) {
			c = null;
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					c.drawBitmap(bitmapBackground, 0, 0, paintBackground);
					for (Body b : bricks) {
						b.update(dt);
						b.draw(c);
					}
					paddle.update(dt);
					paddle.draw(c);
					ball.update(dt);
					ball.draw(c);

					handleCollisions();

					if (ball.isAtBottom(310))
						isRunning = false;

					if (bricks.size() == 0){
						won = true;
						isRunning = false;
					}
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
		try {
		c = surfaceHolder.lockCanvas(null);
		c.drawBitmap(bitmapBackground, 0, 0, new Paint());
		if (won) 
			c.drawBitmap(bitmapWon, (240-bitmapWon.getWidth())/2, 100, new Paint());
		else
			c.drawBitmap(bitmapLost, (240-bitmapLost.getWidth())/2, 70, new Paint());
		} finally {
			surfaceHolder.unlockCanvasAndPost(c);
		}
	}

	private void calcBallPos() {
		ball.update(dt);
	}

	private void handleCollisions() {
		ball.handleWalls(240,320);
		paddle.handleWalls(240,320);

		// Handle collisions between ball-paddle and ball-bricks
		float ball_middle_x = ball.pos_x + ball.getWidth();
		float ball_middle_y = ball.pos_y + ball.getHeight();

		// Is ball colliding with paddle?
		if (ball.pos_y + ball.getHeight() >= paddle.pos_y && 
			ball.pos_x < paddle.pos_x + paddle.getWidth() &&
			ball.pos_x > paddle.pos_x) {
				ball.vel_y *= -1;
				CakeActivity.vibrator.vibrate((long) 10);
		}

		// Is ball colliding with left side of paddle?

		// Is ball colliding with right side of paddle?
		
		// Is ball colliding with bricks?
		List<Body> removeList = new ArrayList<Body>();
		for (Body b : bricks) {
			// Top or Bottom of brick
			if ((ball.pos_y < b.pos_y + b.getHeight() && ball.pos_y + ball.getHeight() > b.pos_y ) &&
			  	ball.pos_x < b.pos_x + b.getWidth() &&
			ball.pos_x > b.pos_x) {
				ball.vel_y *= -1;
				CakeActivity.vibrator.vibrate((long) 10);
				bricks.remove(b);
				break;
			}else  if ((ball.pos_x < b.pos_x + b.getWidth() && ball.pos_x + ball.getWidth() > b.pos_x ) &&
			  	ball_middle_y < b.pos_y + b.getHeight() &&
				ball_middle_y > b.pos_y) {
				ball.vel_x *= -1;
				CakeActivity.vibrator.vibrate((long) 10);
				bricks.remove(b);
				break;

			}

			// Left side
			
			// Right side
		}
		
		// Is ball colliding with side of bricks?
	}
}
