package com.cake; 

import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Paddle extends Body {
		private int friction = 10;

		public Paddle(float pos_x, Paint paint, Bitmap bitmap) {
			super(pos_x, 300, paint, bitmap);
		}

		/* Friction will increase/decrease the speed of the paddle
		   higher = faster */
		public int getFriction() {
				return friction;
		}

		public void setFriction(int friction) {
				this.friction = friction;
		}

		public void update(float stuff) {
				float input_x = Accelerometer.getX();

				/* Needs to be possible to keep paddle still,
				   so we'll won't move on smaller values */
				if( input_x > 0.4) {
					this.pos_x -= input_x * friction;
				}
		}

		public void handleWalls(int x_max, int y_max) {
			if (pos_x <= 0 || pos_x > (x_max - bitmap.getWidth()) ) {
				vel_x = 0;
			}
		}
}
