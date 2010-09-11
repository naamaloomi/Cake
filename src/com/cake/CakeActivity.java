package com.cake;

import android.app.Activity;
import android.os.Bundle;

public class CakeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	makeFullscreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    prive void makeFullScreen() {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
