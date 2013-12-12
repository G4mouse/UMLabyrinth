package com.me.mygdxgame;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.R.id;




import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
            	
    	
    	super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;			        
		initialize(new Tiled(), cfg);
			
			
			}
	    	
      
    }
