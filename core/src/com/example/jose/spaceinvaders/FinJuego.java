package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by josik on 19/02/2017.
 */

public class FinJuego implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture fin;
    private int puntos;
    private SpaceInvaders space;
    private boolean stop;

    public FinJuego(SpaceInvaders space, int puntos){
        this.space = space;
        this.puntos = puntos;
        this.stop = true;
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,10 * aspectRatio,10);
        fin = new Texture("finjuego.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(fin,0,0,1440,2560);
        setPuntos(batch);
        batch.end();

        if(stop) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop = false;
        }

        if (Gdx.input.isTouched()) {
            space.setGameScreen(0);
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
        fin.dispose();
    }

    private void setPuntos(SpriteBatch batch){
        BitmapFont font = new BitmapFont();
        font.getData().setScale(10,10);
        GlyphLayout layout = new GlyphLayout(font, "Puntos: "+puntos);
        font.draw(batch,layout,50, 2400);
    }
}
