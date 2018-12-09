package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by josik on 19/02/2017.
 */

public class WinGame implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture win;
    private SpaceInvaders space;
    private int puntos;

    public WinGame(SpaceInvaders space, int puntos){
        this.space = space;
        this.puntos = puntos;
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,10 * aspectRatio,10);
        win = new Texture("win.jpg");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(win,0,0,1440,2560);
        setPuntos(batch);
        batch.end();

        if (Gdx.input.isTouched()) {
            space.setGameScreen(puntos);
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

    }

    private void setPuntos(SpriteBatch batch){
        BitmapFont font = new BitmapFont();
        font.getData().setScale(10,10);
        GlyphLayout layout = new GlyphLayout(font, "Puntos: "+puntos);
        font.draw(batch,layout,50, 2400);
    }
}
