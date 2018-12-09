package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by josik on 15/02/2017.
 */

public class Juego implements Screen {

    private SpaceInvaders spaceInvaders;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture start;

    public Juego(SpaceInvaders spaceInvaders) {
        this.spaceInvaders = spaceInvaders;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,10 * aspectRatio,10);
        start = new Texture("inicio.jpg");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(start,0,0,1440,2560);
        batch.end();

        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > 420 && Gdx.input.getX() < 1450 &&
            Gdx.input.getY() > 1085 &&  Gdx.input.getY() < 1460)
            spaceInvaders.setGameScreen(0);
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
