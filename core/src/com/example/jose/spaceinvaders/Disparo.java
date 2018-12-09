package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by josik on 15/02/2017.
 */

public class Disparo extends Sprite {

    private float speed;

    public Disparo(Sprite sprite, float speed){
        super(sprite);
        this.speed = speed;
        setSize(getWidth(),getHeight()*1.5f);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta){
        setY(getY()+speed + delta);
    }

    public boolean disponible(){
        if(getY() > 2560 || getY() < 0){
            return true;
        }
        return false;
    }
}
