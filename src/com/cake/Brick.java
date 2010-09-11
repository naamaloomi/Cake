package com.cake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Brick extends Body{

	public void update(float dt) {
	}

	public Brick(float pos_x, float pos_y, Paint paint, Bitmap bitmap) {
		super(pos_x, pos_y, paint, bitmap);
	}

	public void handleWalls(int x_max, int y_max) {
	}	

}
