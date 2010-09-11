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

		public void update(float dt) {
				float input_x = Accelerometer.getX();

				/* Needs to be possible to keep paddle still,
				   so we'll won't move on smaller values */
				if(input_x > 0.4 || input_x < -0.4) {
					this.vel_x = input_x * friction;
				}

				pos_x -= vel_x * dt;
		}

		public void handleWalls(int x_max, int y_max) {
			/* Walls are blocking */
			if (pos_x <= 0) {
				pos_x = 0;
			} else if ( pos_x > (x_max - bitmap.getWidth()) ) {
					pos_x = (x_max - bitmap.getWidth());
			}
		}
}
