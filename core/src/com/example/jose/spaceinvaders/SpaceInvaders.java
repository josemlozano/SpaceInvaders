package com.example.jose.spaceinvaders;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class SpaceInvaders extends Game implements ApplicationListener {

	private Map map;
	private Juego juego;
	private FinJuego fin;
	private WinGame win;
	
	@Override
	public void create () {
		setStartScreen();
	}

	public void setGameScreen(int puntos){
		map = new Map(this, puntos);
		setScreen(map);
	}

	public void setStartScreen(){
		juego = new Juego(this);
		setScreen(juego);
	}

	public void setEndGame(int puntos){
		fin = new FinJuego(this, puntos);
		setScreen(fin);
	}

	public void winGame(int puntos){
		win = new WinGame(this, puntos);
		setScreen(win);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
