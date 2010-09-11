package com.cake;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball extends Body{

	public void update(float dt) {
		pos_x += vel_x * dt;
		pos_y += vel_y * dt;
	}

	public Ball(float pos_x, float pos_y, Paint paint, Bitmap bitmap) {
		super(pos_x, pos_y, paint, bitmap);
	}	

}
