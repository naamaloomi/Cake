package com.cake;
 
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
 
public class CakeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		makeFullScreen();
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new CakeView(this));
    }

	@Override
	protected void onResume() {
		super.onResume();

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Accelerometer.start(sensorManager);
	}

	@Override
	protected void onPause() {
			super.onPause();
			Accelerometer.stop();
	}

	private void makeFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
