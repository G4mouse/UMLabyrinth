package com.me.mygdxgame;


import android.app.Activity;
import android.content.Intent;
//import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
//import android.view.Menu;
import android.widget.VideoView;

public class SplashScreen extends Activity {

	// Splash screen timer
    private static int SPLASH_TIME_OUT = 7000;
	
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		//this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		
		 VideoView video = (VideoView) findViewById(R.id.videoView1);
	        video.setVideoPath("android.resource://com.me.mygdxgame/raw/" + R.raw.splashscreen);
	        video.start();
	       // video.setOnCompletionListener((OnCompletionListener) this);
	        
	        new Handler().postDelayed(new Runnable() {
	        	 
	            /*
	             * Showing splash screen with a timer. This will be useful when you
	             * want to show case your app logo / company
	             */
	 
	            @Override
	            public void run() {
	                // This method will be executed once the timer is over
	                // Start your app main activity
	                Intent MainMenu = new Intent(SplashScreen.this, MainMenu.class);
	                startActivity(MainMenu);
	                
	                // Screen fades in to Splash Screen and then fades out to Main Menu
	                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	                
	                // close this activity
	                SplashScreen.this.finish();
	                
	             
	            }
	        }, SPLASH_TIME_OUT);
		 
		
	}
	
	

	//@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.splash_screen, menu);
//		return true;
//	}

}
