package com.cake;

public class Ball {
	public float pos_x;
	public float pos_y;
	public float vel_x;
	public float vel_y;

	public void update(float dt) {
		pos_x += vel_x;
		pos_y += vel_y;
	}
}
