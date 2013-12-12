package com.me.mygdxgame;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class UMLEngine {
	
	//Constants that will be used in the game
	public static final int GAME_THREAD_DELAY = 4000;	
	public static final int MAIN_MENU_MUSIC = R.raw.jingle;
	public static final int R_VOLUME = 100;
	public static final int  L_VOLUME = 100;
	public static final boolean LOOP_BACKGROUND_MUSIC = true;
	public static Context context;
	public static Thread musicThread;
	
	
	// Kill game and exit
	//This is another way of using the exit function from here
	
	public boolean onExit(View v){
		try{
			
			Intent bgmusic = new Intent(context, UMLMusic.class);
			context.stopService(bgmusic);
			musicThread.stop();
			return true;
			
		}
		catch(Exception e){
			return false;
		}
	}
	
}
