package com.cake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Body {
	public float pos_x;
	public float pos_y;
	public float vel_x;
	public float vel_y;
	public float acc_x;
	public float acc_y;

	protected Bitmap bitmap;
	protected Paint paint;

	public Body(float pos_x, float pos_y, Paint paint, Bitmap bitmap)  {
		this.paint = paint;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.acc_x = 0;
		this.acc_y = 0;
		this.vel_x = 0;
		this.vel_y = 0;
		this.bitmap = bitmap;
	}

	protected Body() {
	}
	public abstract void update(float dt);
	public void draw(Canvas canvas){
		canvas.save();
		canvas.translate(pos_x,pos_y);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		canvas.restore();
	}
	public void setPaint(Paint paint) {
		this.paint = paint;	
	}

	public abstract void handleWalls(int x_max, int y_max);
}
