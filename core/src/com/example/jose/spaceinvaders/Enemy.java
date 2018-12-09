package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by josik on 07/02/2017.
 */

public class Enemy extends Sprite {

    private float speed;
    private int vidas;

    public Enemy(Sprite sprite, float speed, int vidas){
        super(sprite);
        this.speed = speed;
        this.vidas = vidas;
        setSize(getWidth(),getHeight() * 1.5f);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());

        super.draw(batch);
    }

    public void update(float delta){
        setX(getX()+speed + delta);
    }

    public boolean vida (){
        vidas--;
        if (vidas == 0){
            return true;
        }
        return false;
    }

    public boolean volver (){
        if(getX() < -1 || getX() > 1300){
            return true;
        }
        return false;
    }

    public void setSpeed() {
        this.speed = -speed;
    }


}
