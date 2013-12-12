package com.me.mygdxgame;

//import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.Texture.TextureFilter;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.screens.Play;

public class Tiled extends Game {
	
	
	
	@Override
	public void create() {		
		setScreen(new Play());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
