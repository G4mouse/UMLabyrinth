package com.me.mygdxgame.entities;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.screens.Play;


import com.me.mygdxgame.entities.Player;




public class Player extends Sprite implements InputProcessor {
	
	
	
	//for pause screen
	
	
	
	//timer
	private static final ScheduledExecutorService worker = 
			  Executors.newSingleThreadScheduledExecutor();
	
	/**the velocity*/
	private Vector2 velocity = new Vector2();
	private float speed = 92, gravity = 0f;
	//private float speed = 60 * 2, gravity = 60 * 1.8f;
	private TiledMapTileLayer collisionLayer;
	private String blockedKey = "blocked";
	private String exitKey = "exit";
	private String itemKey = "item";
	private boolean canJump;
	public static int activeTouch = 0;
	
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer){
	
		super(sprite);	
		this.collisionLayer = collisionLayer;
	}
	

	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}


	
	public void update(final float delta) {
		// apply gravity
		//velocity.y -= gravity * delta;

		// clamp velocity
		//if(velocity.y > speed)
		//	velocity.y = speed;
		//else if(velocity.y < -speed)
		//	velocity.y = -speed;

		// save old position
		float oldX = getX(), oldY = getY();
		boolean collisionX = false, collisionY = false;

		// move on x
		setX(getX() + velocity.x * delta);

		if(velocity.x < 0) // going left
			collisionX = collidesLeft();
		else if(velocity.x > 0) // going right
			collisionX = collidesRight();

		// react to x collision
		if(collisionX) {
			setX(oldX);
			velocity.x = 0;
		}

		// move on y
		setY(getY() + velocity.y * delta);

		if(velocity.y < 0) // going down
			canJump = collisionY = collidesBottom();
		else if(velocity.y > 0) // going up
			collisionY = collidesTop();

		// react to y collision
		if(collisionY) {
			setY(oldY);
			velocity.y = 0;
		}

		if(exit()){

			Play.winning(delta);
			
			Runnable task = new Runnable() {
			    public void run() {
			    	System.exit(0);
			    }

				
			  };
			  worker.schedule(task, 1000, TimeUnit.MILLISECONDS);
		}
			
			
		
		}
		
	public boolean exit() {
		int x = (int) getX();
		int y = (int) getY();
		com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		
			if(cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(exitKey))
				return true;
		return false;
	}
		


	private boolean isCellBlocked(float x, float y) {
		com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
	}
	
	private boolean isItem(float x, float y) {
		com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(itemKey);
	}
	
	

	public boolean collidesRight() {
		for(float step = 0; step < getHeight(); step += (collisionLayer.getTileHeight() / 2))
			if(isCellBlocked(getX() + getWidth() -92, getY() + step))
				return true;
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(getX(), getY() + step))
				return true;
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(getX() + step, getY() + getHeight() -92))
				return true;
		return false;

	}

	public boolean collidesBottom() {
		for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(getX() + step, getY()))
				return true;
		return false;
	}
	
	

	
	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public float getPosX(){
		return getX();
	}
	public float getPosY(){
		return getY();
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.W:
			velocity.y= speed;
			Play.animateUp();
			this.update(1f);
			Play.positionCheck(1f);
			break;
		case Keys.A:
			
			velocity.x= -speed;
			Play.animateLeft();
			this.update(1f);
			Play.positionCheck(1f);
			break;
		case Keys.D:
			velocity.x= speed;
			Play.animateRight();
			
			this.update(1f);
			Play.positionCheck(1f);
			break;
		case Keys.S:
		
			velocity.y= -speed;
			Play.animateDown();
			this.update(1f);
			Play.positionCheck(1f);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.A:
			Play.positionCheck(1f);
			
			velocity.x =0;
		case Keys.D:
			Play.positionCheck(1f);
			
			velocity.x =0;
		case Keys.W:
			Play.positionCheck(1f);
			
			velocity.y=0;
		case Keys.S:
			Play.positionCheck(1f);
			
			velocity.y=0;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
	
		
		
		Vector3 v3 = new Vector3(screenX, screenY, 0);


		Play.getCamera().unproject(v3);
		
		
		
		  if( v3.x > getX() && (v3.y > getY() - 92) && (v3.y < getY() +92)){
			  velocity.x= speed;
				Play.animateRight();
				
				this.update(1f); 
				
		  }
		  if( v3.x < getX() && (v3.y > getY() - 92) && (v3.y < getY() +92)){
			  velocity.x= -speed;
				Play.animateLeft();
				
				this.update(1f);
		  }
		   if( v3.y> getY() && (v3.x > getX() - 92) && (v3.x < getX() +92)){
			  velocity.y= speed;
				Play.animateUp();
				
				this.update(1f);
		  }
		   if( v3.y  <= getY()  && (v3.x > getX() - 92) && (v3.x < getX() +92)){
			  velocity.y= -speed;
				Play.animateDown();
				
				this.update(1f);
		   }
			
		 	  
		 
	
		
	      return false;
		
	}
	
	
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		 if( screenX > getX()){
			 
			  Play.positionCheck(1f);
				
				velocity.y=0;  
		  }
		  if( screenX < getX()){
			  Play.positionCheck(1f);
				
				velocity.y=0;
		  }
		  if( screenY > getY()){
			  Play.positionCheck(1f);
				
				velocity.y=0;
		  }
		  if( screenY <= getY()){
			  Play.positionCheck(1f);
				
				velocity.y=0;
		  }		  
		  

		
		
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
