package com.me.mygdxgame.screens;

import java.awt.Point;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.me.mygdxgame.entities.Aboots;
import com.me.mygdxgame.entities.Antic;
import com.me.mygdxgame.entities.Boots;
import com.me.mygdxgame.entities.Cool;
import com.me.mygdxgame.entities.Item;
import com.me.mygdxgame.entities.IvItem;
import com.me.mygdxgame.entities.Player;
import com.sun.net.ssl.internal.www.protocol.https.Handler;





public class Play implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private static OrthographicCamera camera;
	private static int hasItem = 0;
	public static int nextThing =1;
	private static int hasCool = 0;
	private static int hasBoot = 0;
	private static Player player;
	private static Item item;
	private static Item item2;
	
	private static IvItem ivitem;
	private static IvItem ivitem2;
	
	private static Cool cool;
	private static Antic antic;
	private static Boots boot;
	private static Aboots aboot;
	
	
	
	
	
	private static final ScheduledExecutorService worker = 
			  Executors.newSingleThreadScheduledExecutor();
	private static final String TimeInterval = null;
	
	//For in game Buttons
	private static Stage stage;
	static Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	public static TextButton button = new TextButton(" YOU WIN", skin);
	
	// setup the dimensions of the menu buttons
    private static final float BUTTON_WIDTH = 300f;
    private static final float BUTTON_HEIGHT = 60f;
    private static final float BUTTON_SPACING = 10f;
	
	
 
    
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		
		   
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		getCamera().position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		getCamera().update();
		
		
		renderer.setView(getCamera());
		renderer.render();
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch());
		
		
        //font.draw(renderer.getSpriteBatch(), "Hola mundo", -100f, 10f);
		
		
		//new code
		item.draw(renderer.getSpriteBatch());
		item2.draw(renderer.getSpriteBatch());
		ivitem.draw(renderer.getSpriteBatch());
		ivitem2.draw(renderer.getSpriteBatch());
		cool.draw(renderer.getSpriteBatch());
		antic.draw(renderer.getSpriteBatch());
		boot.draw(renderer.getSpriteBatch());
		aboot.draw(renderer.getSpriteBatch());
		
		
		
		//stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		//stage.draw();
		
		
		
		renderer.getSpriteBatch().end();
		
		
		
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
		//camera.viewportWidth = 1280;
		//camera.viewportHeight = 720;
		
		
		getCamera().viewportWidth = 2280;
		getCamera().viewportHeight = 1720;
		getCamera().update();
		
		
		//stage.setViewport(640, 480, false);
		//stage.getCamera().position.set(640/2, 480/2, 0);
		
		
		
		//buttons?	
		

		

		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		map = new TmxMapLoader().load("maps/fullmap.tmx");
	
		renderer = new OrthogonalTiledMapRenderer(map);
		
		setCamera(new OrthographicCamera(1,h/w));
		
		//for button
		//stage = new Stage();
		
		
		//if(player.exit()){
		//stage.addActor(button);
		//}
		
		
		
		//new item code
		item  = new Item(new Sprite(new Texture("img/Avs.png")), (TiledMapTileLayer) map.getLayers().get(0));
		item.setPosition(92*2 ,92*5) ;
		//new item 2 code
		item2  = new Item(new Sprite(new Texture("img/Avs.png")), (TiledMapTileLayer) map.getLayers().get(0));
		item2.setPosition(92*3 ,92*19) ;
		
		ivitem  = new IvItem(new Sprite(new Texture("img/virus.png")), (TiledMapTileLayer) map.getLayers().get(0));
		ivitem.setPosition(92*1 ,92*18) ;
		//new item 2 code
		ivitem2  = new IvItem(new Sprite(new Texture("img/virus.png")), (TiledMapTileLayer) map.getLayers().get(0));
		ivitem2.setPosition(92*1 ,92*19) ;
		
		
		cool  = new Cool(new Sprite(new Texture("img/Coolant.png")), (TiledMapTileLayer) map.getLayers().get(0));
		cool.setPosition(92*4 ,92*32) ;
		//new item 2 code
		boot  = new Boots(new Sprite(new Texture("img/Avb.png")), (TiledMapTileLayer) map.getLayers().get(0));
		boot.setPosition(92*10 ,92*28) ;
		
		aboot  = new Aboots(new Sprite(new Texture("img/microchip.png")), (TiledMapTileLayer) map.getLayers().get(0));
		aboot.setPosition(92*42 ,92*20) ;
		//new item 2 code
		antic  = new Antic(new Sprite(new Texture("img/doorV.png")), (TiledMapTileLayer) map.getLayers().get(0));
		antic.setPosition(92*16 ,92*12) ;
		
		
		
		
		
		player = new Player(new Sprite(new Texture("img/Avatar_u1_f.png")), (TiledMapTileLayer) map.getLayers().get(0));
		
		player.setPosition(92*3,92*4);
		//player.setPosition(10 * player.getCollisionLayer().getTileWidth(),(player.getCollisionLayer().getHeight() - 19) * player.getCollisionLayer().getTileHeight()) ;
		Gdx.input.setInputProcessor(player);
		
		
		
	}
	
	public static void winning(float delta){
		
	//	int posX= (int) player.getPosX();
		//int posY= (int) player.getPosY();
		//button.setPosition(posX, posY);
		Texture tex12 = new Texture("img/Avatar_Victory_f.png");
		player.setTexture(tex12);
		//stage.addActor(button);
		Gdx.input.setInputProcessor(null);
		Runnable task44 = new Runnable() {
			
		    public void run() {
		    
		    System.exit(0);
		    }

		    
		  };
		  worker.schedule(task44, 500, TimeUnit.MILLISECONDS);
		
		
	}
	
	
	public static void positionCheck(float delta){
		
		//Vector3 pos1 = new Vector3(player.getX(),player.getY(), 0);
		//Vector3 pos2 = new Vector3(item.getX(),item.getY(), 0);
		
		if(player.getPosX() == item.getPosX() && player.getPosY() == item.getPosY() && item.getCollected() == false){
		//if(pos1.x == pos2.x && pos1.y == pos2.y){
		item.setColor(0.0f,0.0f,0.0f,0.0f);	
		item.setCollected();
		hasItem +=1;
		System.out.println("HasItem X= " + hasItem);
		}
		if(player.getPosX() == cool.getPosX() && player.getPosY() == cool.getPosY() && cool.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			cool.setColor(0.0f,0.0f,0.0f,0.0f);	
			cool.setCollected();
			hasCool +=1;
			}
		if(player.getPosX() == boot.getPosX() && player.getPosY() == boot.getPosY() && boot.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			boot.setColor(0.0f,0.0f,0.0f,0.0f);	
			boot.setCollected();
			hasBoot +=1;
			}
		
		if(player.getPosX() == item2.getPosX() && player.getPosY() == item2.getPosY() && item2.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			item2.setColor(0.0f,0.0f,0.0f,0.0f);	
			item2.setCollected();
			hasItem +=1;
		}
		if(player.getPosX() == ivitem.getPosX() && player.getPosY() == ivitem.getPosY() && hasItem >= 1 && ivitem.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			ivitem.setColor(0.0f,0.0f,0.0f,0.0f);
			hasItem -=1;
			ivitem.setCollected();
			System.out.println("This is collected = " + hasItem);
		}
		if(player.getPosX() == ivitem2.getPosX() && player.getPosY() == ivitem2.getPosY() && hasItem >= 1 && ivitem2.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			ivitem2.setColor(0.0f,0.0f,0.0f,0.0f);
			hasItem -=1;
			ivitem2.setCollected();
			System.out.println("This is collected = " + hasItem);
		}
		if(player.getPosX() == antic.getPosX() && player.getPosY() == antic.getPosY() && hasCool >= 1 && antic.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			antic.setColor(0.0f,0.0f,0.0f,0.0f);
			hasCool -=1;
			antic.setCollected();
		}
		if(player.getPosX() == aboot.getPosX() && player.getPosY() == aboot.getPosY() && hasBoot >= 1 && aboot.getCollected() == false){
			//if(pos1.x == pos2.x && pos1.y == pos2.y){
			aboot.setColor(0.0f,0.0f,0.0f,0.0f);
			hasBoot -=1;
			aboot.setCollected();
		}
		if(player.getPosX() == aboot.getPosX() && player.getPosY() == aboot.getPosY() && hasBoot < 1 && aboot.getCollected() == false){
			aboot.setColor(0.0f,0.0f,0.0f,0.0f);
			Texture tex7 = new Texture("img/Avatar_Death_f.png");
			player.setTexture(tex7);
				 
			      isDead();
			 
		}
		
			  if(player.getPosX() == antic.getPosX() && player.getPosY() == antic.getPosY() && hasCool < 1 && antic.getCollected() == false){
					antic.setColor(0.0f,0.0f,0.0f,0.0f);
					Texture tex9 = new Texture("img/Avatar_Death_f.png");
					player.setTexture(tex9);
					
						
					      isDead();
					    }

						
				
		
		
	
		
		if(player.getPosX() == ivitem2.getPosX() && player.getPosY() == ivitem2.getPosY() && hasItem < 1 && ivitem2.getCollected() == false){
			ivitem2.setColor(0.0f,0.0f,0.0f,0.0f);
			Texture tex = new Texture("img/Avatar_Death_f.png");
			player.setTexture(tex);
			isDead();
			
		}
		if(player.getPosX() == ivitem.getPosX() && player.getPosY() == ivitem.getPosY() && hasItem < 1 && ivitem.getCollected() == false){
			ivitem.setColor(0.0f,0.0f,0.0f,0.0f);
			Texture tex = new Texture("img/Avatar_Death_f.png");
			player.setTexture(tex);
			
			      isDead();
			   
		}
	}
	
	private static  void isDead() {
		Gdx.input.setInputProcessor(null);
		Runnable task44 = new Runnable() {
			
		    public void run() {
		    
		    System.exit(0);
		    }

		    
		  };
		  worker.schedule(task44, 500, TimeUnit.MILLISECONDS);
		
		
		
		
	}
	
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
		
	}
	

	@Override
	public void pause() {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();
		item.getTexture().dispose();
	}

	public static void animateUp() {
		
		
		switch(nextThing){
		case 1:
			Texture tex = new Texture("img/Avatar_u2_f.png");
			player.setTexture(tex);
			nextThing = 2;
			break;
		 
		case 2:
			Texture tex2 = new Texture("img/Avatar_u1_f.png");
			player.setTexture(tex2);
			nextThing = 1;
			break;
		 }  
		
	}

	public static void animateLeft() {
		switch(nextThing){
		case 1:
			Texture tex3 = new Texture("img/Avatar_L1_f.png");
			player.setTexture(tex3);
			nextThing = 2;
			break;
		 
		case 2:
			Texture tex4 = new Texture("img/Avatar_L2_f.png");
			player.setTexture(tex4);
			nextThing = 1;
			break;
		 }  
	}
	public static void animateDown() {
		switch(nextThing){
		case 1:
			Texture tex5 = new Texture("img/Avatar_d1_f.png");
			player.setTexture(tex5);
			nextThing = 2;
			break;
		 
		case 2:
			Texture tex6 = new Texture("img/Avatar_d2_f.png");
			player.setTexture(tex6);
			nextThing = 1;
			break;
		 }  
	}
	public static void animateRight() {
		switch(nextThing){
		case 1:
			Texture tex7 = new Texture("img/Avatar_R1_f.png");
			player.setTexture(tex7);
			nextThing = 2;
			break;
		 
		case 2:
			Texture tex8 = new Texture("img/Avatar_R2_f.png");
			player.setTexture(tex8);
			nextThing = 1;
			break;
		 }  
	}

	public static OrthographicCamera getCamera() {
		return camera;
	}

	public static void setCamera(OrthographicCamera camera) {
		Play.camera = camera;
	}

	

	
	
	
}
