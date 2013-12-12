package com.me.mygdxgame.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Cool extends Sprite {

	
	public boolean collected = false;
	private TiledMapTileLayer collisionLayer;
	
	




public Cool(Sprite sprite, TiledMapTileLayer collisionLayer){
	
	super(sprite);	
	this.collisionLayer = collisionLayer;
	}

public void draw(SpriteBatch spriteBatch){
	update(Gdx.graphics.getDeltaTime());
	super.draw(spriteBatch);
}


public void update(float delta) {
	

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
public void setCollected(){
	
	collected = true;
}
public boolean getCollected(){
	
	return collected;
}
}
	
	

