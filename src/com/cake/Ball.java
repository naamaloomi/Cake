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

	public void handleWalls(int x_max, int y_max) {
		if (pos_x <= 0 || pos_x >= (x_max - bitmap.getWidth()))
			vel_x *= -1;
		if (pos_y <= 0 || pos_y >= (y_max - bitmap.getHeight()))
			vel_y *= -1;

	}	

}
