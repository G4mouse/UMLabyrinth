package com.me.mygdxgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	MediaPlayer mediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
			
//		setVolumeControlStream(AudioManager.STREAM_MUSIC);
//		mediaPlayer = new MediaPlayer();
//		try{
//			AssetManager assetManager = getAssets();
//			AssetFileDescriptor descriptor = assetManager.openFd("jingle.mp3");
//			mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
//			mediaPlayer.prepare();			
//			mediaPlayer.setLooping(true);
//			mediaPlayer.setVolume(1, 1);
//		} catch (IOException e){
//			Toast.makeText(this, "Could not load music file", Toast.LENGTH_LONG);
//			mediaPlayer = null;
//		}
//		
		
		
	UMLEngine.musicThread = new Thread(){
			public void run(){
				Intent bgmusic = new Intent(getApplicationContext(), UMLMusic.class);
				startService(bgmusic);
				UMLEngine.context = getApplicationContext();
			}
		
		
	};
	UMLEngine.musicThread.start();
	
	final UMLEngine engine = new UMLEngine();
	
	
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MediaPlayer mp = MediaPlayer.create(MainMenu.this, R.raw.beep);
				mp.start();
				Intent intentLevelSelect = new Intent(MainMenu.this, MainActivity.class);
				startActivity(intentLevelSelect);
			}
		});
		
b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MediaPlayer mp = MediaPlayer.create(MainMenu.this, R.raw.beep);
				mp.start();
				Intent intentOptions = new Intent(MainMenu.this, Options.class);
				startActivity(intentOptions);
			}
		});


b3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		MediaPlayer mp = MediaPlayer.create(MainMenu.this, R.raw.beep);
		mp.start();
		
		boolean clean = false;
		engine.onExit(v);
		
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		
	}
		
//		AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
//		builder.setMessage("Are you sure you want to exit?");
//		builder.setCancelable(false);
//		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				MainMenu.this.finish();
				
				
			
	});
//		
//		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.cancel();
//				
//			}
//		});
//		AlertDialog alert = builder.create();
//		alert.show();
//	}
//});
		
		
		
		
		
	}

	//@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main_menu, menu);
//		
//		return true;
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if(item.getItemId() == R.id.item1)
//			Log.d("Option", "Option 1 was clicked");
//		return super.onOptionsItemSelected(item);
//	}
	
	

	@Override
	protected void onResume() {
		
		super.onResume();
		if(mediaPlayer != null){
			mediaPlayer.start();
		}
		
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		if(mediaPlayer != null){
			mediaPlayer.pause();
			if(isFinishing()){
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
	}

	
}
