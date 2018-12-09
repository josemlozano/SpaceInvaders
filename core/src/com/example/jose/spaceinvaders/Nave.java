package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by josik on 07/02/2017.
 */

public class Nave extends Sprite {

    public Nave(Sprite sprite){
        super(sprite);
        setSize(getWidth(),getHeight()*1.5f);
    }

}
