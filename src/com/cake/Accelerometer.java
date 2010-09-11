package com.cake;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Simple class for accessing the x, y, z values of the accelerometer in a static way
 */
public final class Accelerometer {
	private static SensorManager sensorManager;
    private static Sensor sensor;
    private static boolean started = false;
    
    private static float x;
    private static float y;
    private static float z;
    
    private static SensorEventListener listener = new SensorEventListener() {
    	
    	public void onAccuracyChanged(Sensor sensor, int accuracy) {
    		// Part of the interface but not used here
    	}

    	public void onSensorChanged(SensorEvent event) {
    		x = event.values[0];
    		y = event.values[1];
    		z = event.values[2];
    	}
    };
    
	private Accelerometer() {
	}

    public static float getX() {
    	return (x + 0.9f);
    }
    
    public static float getY() {
    	return y;
    }
    
    public static float getZ() {
    	return z;
    }
    
	public static void start(SensorManager sensorManager) {
		if (started) {
			return;
		}
		if (sensor == null) {
	        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	        if (sensors.size() == 0)
	        	throw new IllegalStateException("No accelerometer available");

	        sensor = sensors.get(0);
	        Accelerometer.sensorManager = sensorManager;
		}
    	sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
		started = true;
	}
	
	public static void stop() {
		if (!started) {
			return;
		}
		started = false;
		
		sensorManager.unregisterListener(listener);
	}
}
