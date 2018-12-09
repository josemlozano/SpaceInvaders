package com.example.jose.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by josik on 14/02/2017.
 */

public class Map implements Screen, InputProcessor {

    private Random r = new Random();
    private TiledMap fondo;
    private SpaceInvaders space;
    private OrthogonalTiledMapRenderer renderer;
    private Stage stage;
    private Enemy[] enemy1 = new Enemy[8];
    private Enemy[] enemy2 = new Enemy[8];
    private Enemy[] enemy3 = new Enemy[8];
    private Nave nave;
    private Disparo disparoN, disparoE1, disparoE2, disparoE3, disparoE4, disparoE5, disparoE6, disparoE7;
    private OrthographicCamera camera;
    private String[] enemigos = new String[]{"debil1.png","debil2.png","debil3.png",
            "medio1.png","medio2.png","medio3.png",
            "fuerte1.png","fuerte2.png","fuerte3.png"};
    private Rectangle naveRect;
    private Rectangle[] enemyRect1 = new Rectangle[8];
    private Rectangle[] enemyRect2 = new Rectangle[8];
    private Rectangle[] enemyRect3 = new Rectangle[8];
    private Rectangle dispNRect, dispERect1, dispERect2, dispERect3, dispERect4, dispERect5, dispERect6, dispERect7;
    private Sound invaderMuerto = Gdx.audio.newSound(Gdx.files.internal("invaderkilled.wav")),
            explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav")),
            disparo = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));

    private int vidas = 5;
    private Sprite vida;
    private boolean isTouched = false;
    private boolean isStart = false;
    private int pantalla = 1;
    private int cont = 0;
    private int puntos;

    private BitmapFont font = new BitmapFont();
    private GlyphLayout layout;
    private GlyphLayout layout2;

    private float time = 0;
    private String t = "";

    public Map(SpaceInvaders space, int puntos) {
        this.space = space;
        this.puntos = puntos;
        Gdx.input.setCatchBackKey(true);
    }

    public void update(){
        naveRect = new Rectangle(nave.getX(),nave.getY(),nave.getWidth()-48,nave.getHeight()-48);
        for (int i = 0; i < 8; i++) {
            if (enemy1[i] != null)
            enemyRect1[i] = new Rectangle(enemy1[i].getX(), enemy1[i].getY(), enemy1[i].getWidth(),
                    enemy1[i].getHeight());
        }

        for (int i = 0; i < 8; i++) {
            if (enemy2[i] != null)
                enemyRect2[i] = new Rectangle(enemy2[i].getX(), enemy2[i].getY(), enemy2[i].getWidth(),
                        enemy2[i].getHeight());
        }

        for (int i = 0; i < 8; i++) {
            if (enemy3[i] != null)
                enemyRect3[i] = new Rectangle(enemy3[i].getX(), enemy3[i].getY(), enemy3[i].getWidth(),
                        enemy3[i].getHeight());
        }
        dispNRect = new Rectangle(disparoN.getX(),disparoN.getY(),disparoN.getWidth(),disparoN.getHeight());
        dispERect1 = new Rectangle(disparoE1.getX(),disparoE1.getY(),disparoE1.getWidth(),disparoE1.getHeight());
        dispERect2 = new Rectangle(disparoE2.getX(),disparoE2.getY(),disparoE2.getWidth(),disparoE2.getHeight());
        dispERect3 = new Rectangle(disparoE3.getX(),disparoE3.getY(),disparoE3.getWidth(),disparoE3.getHeight());
        dispERect4 = new Rectangle(disparoE4.getX(),disparoE4.getY(),disparoE4.getWidth(),disparoE4.getHeight());
        dispERect5 = new Rectangle(disparoE5.getX(),disparoE5.getY(),disparoE5.getWidth(),disparoE5.getHeight());
        dispERect6 = new Rectangle(disparoE6.getX(),disparoE6.getY(),disparoE6.getWidth(),disparoE6.getHeight());
        dispERect7 = new Rectangle(disparoE7.getX(),disparoE7.getY(),disparoE7.getWidth(),disparoE7.getHeight());

        if (isStart) {
            if (naveRect.overlaps(dispERect1) || naveRect.overlaps(dispERect2) || naveRect.overlaps(dispERect3)
                    || naveRect.overlaps(dispERect4) || naveRect.overlaps(dispERect5) || naveRect.overlaps(dispERect6)
                    || naveRect.overlaps(dispERect7)) {
                nave.setX((1440 / 2) - (250 / 2));
                nave.setY(50);
                if (vidas > 0 && vidas < 6) {
                    if (isTouched) {
                        explosion.play();
                        vidas--;
                        isTouched = false;
                        isStart = false;
                        cont = 0;
                        Gdx.input.vibrate(300);
                    }
                }
                if (vidas == 4) {
                    vida = new Sprite(new Texture("vidas4.png"));
                    vida.setPosition(20, 2500);
                    vida.setSize(400, 50);
                } else if (vidas == 3) {
                    vida = new Sprite(new Texture("vidas3.png"));
                    vida.setPosition(20, 2500);
                    vida.setSize(300, 50);
                } else if (vidas == 2) {
                    vida = new Sprite(new Texture("vidas2.png"));
                    vida.setPosition(20, 2500);
                    vida.setSize(200, 50);
                } else if (vidas == 1) {
                    vida = new Sprite(new Texture("vidas1.png"));
                    vida.setPosition(20, 2500);
                    vida.setSize(100, 50);
                } else if (vidas == 0) {
                    vida = new Sprite(new Texture("vidas0.png"));
                    Gdx.input.vibrate(1000);
                    vida.setPosition(20, 2500);
                    vida.setSize(100, 50);
                    space.setEndGame(puntos);
                }

                if (naveRect.overlaps(dispERect1)) {
                    disparoE1.setY(-70);
                }
                if (naveRect.overlaps(dispERect2)) {
                    disparoE2.setY(-70);
                }
                if (naveRect.overlaps(dispERect3)) {
                    disparoE3.setY(-70);
                }
                if (naveRect.overlaps(dispERect4)) {
                    disparoE4.setY(-70);
                }

                if (naveRect.overlaps(dispERect5)) {
                    disparoE5.setY(-70);
                }

                if (naveRect.overlaps(dispERect6)) {
                    disparoE6.setY(-70);
                }

                if (naveRect.overlaps(dispERect7)) {
                    disparoE7.setY(-70);
                }
            }

            if (disparoN.disponible()) {
                disparoN.setPosition(nave.getX() + (250 / 2) - 10, 200);
                disparo.play();
            }

            for (int i = 0; i < 8; i++) {
                if (enemyRect1[i].overlaps(dispNRect) && enemy1[i] != null) {
                    if (enemy1[i].vida()) {
                        invaderMuerto.play();
                        enemy1[i] = null;
                    }
                    disparoN.setY(2561);
                    puntos++;
                }
            }

            for (int i = 0; i < 8; i++) {
                if (enemyRect2[i].overlaps(dispNRect) && enemy2[i] != null) {
                    if (enemy2[i].vida()) {
                        invaderMuerto.play();
                        enemy2[i] = null;
                    }
                    disparoN.setY(2561);
                    puntos += 2;
                }
            }

            for (int i = 0; i < 8; i++) {
                if (enemyRect3[i].overlaps(dispNRect) && enemy3[i] != null) {
                    if (enemy3[i].vida()) {
                        invaderMuerto.play();
                        enemy3[i] = null;
                    }
                    disparoN.setY(2561);
                    puntos += 3;
                }
            }
        } else {
            disparoN.setY(2561);
        }

        int index = r.nextInt(8);
        if (disparoE1.disponible() && enemy1[index] != null) {
            disparoE1.setPosition(enemy1[index].getX(), enemy1[index].getY() - 100);
        }
        index = r.nextInt(8);
        if (disparoE2.disponible() && enemy2[index] != null) {
            disparoE2.setPosition(enemy2[index].getX(), enemy2[index].getY() - 100);
        }
        index = r.nextInt(8);
        if (disparoE3.disponible() && enemy3[index] != null) {
            disparoE3.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
        }


        index = r.nextInt(8);
        if (disparoE4.disponible() && enemy3[index] != null) {
            disparoE4.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
        }

        if(pantalla == 3){
            index = r.nextInt(8);
            if (disparoE5.disponible() && enemy2[index] != null) {
                disparoE5.setPosition(enemy2[index].getX(), enemy2[index].getY() - 100);
            }
            index = r.nextInt(8);
            if (disparoE6.disponible() && enemy3[index] != null) {
                disparoE6.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
            }
            index = r.nextInt(8);
            if (disparoE7.disponible() && enemy3[index] != null) {
                disparoE7.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
            }
        }


        for (int i = 0; i < 8; i++){
            if (enemy1[i] != null) {
                if (enemy1[i].volver()) {
                    for (int j = 0; j < 8; j++) {
                        if (enemy1[j] != null) {
                            enemy1[j].setSpeed();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++){
            if (enemy2[i] != null) {
                if (enemy2[i].volver()) {
                    for (int j = 0; j < 8; j++) {
                        if (enemy2[j] != null) {
                            enemy2[j].setSpeed();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++){
            if (enemy3[i] != null) {
                if (enemy3[i].volver()) {
                    for (int j = 0; j < 8; j++) {
                        if (enemy3[j] != null) {
                            enemy3[j].setSpeed();
                        }
                    }
                }
            }
        }

        if(muertos()){
            isStart = false;
            cont = 0;
            if (pantalla < 3) {
                pantalla++;
                sprites();
            } else {
                space.winGame(puntos);
            }
        }

        if (cont < 200) {
            cont++;
        } else {
            isStart = true;
            isTouched = true;
        }

        time += Gdx.graphics.getDeltaTime();
		if ((time % 10) >= 0 && (time % 10) <= 0.1){
			for (int i = 0; i < 8; i++){
                if (enemy1[i] != null) {
                    enemy1[i].setY(enemy1[i].getY() - 10);
                }
                if (enemy2[i] != null) {
                    enemy2[i].setY(enemy2[i].getY() - 10);
                }
                if (enemy3[i] != null) {
                    enemy3[i].setY(enemy3[i].getY() - 10);
                }
            }
		}
    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        fondo = loader.load("fHoriz.tmx");

        renderer = new OrthogonalTiledMapRenderer(fondo);
        camera=new OrthographicCamera();
        TiledMapTileLayer layer = (TiledMapTileLayer) fondo.getLayers().get(0);
        Vector3 center = new Vector3(layer.getWidth()*layer.getTileWidth()/2,
                layer.getHeight()*layer.getTileHeight()/2,0);
        camera.position.set(center);
        sprites();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        nave.draw(renderer.getBatch());
        for (int i = 0; i < 8; i++) {
            if (enemy1[i] != null)
                enemy1[i].draw(renderer.getBatch());
        }

        for (int i = 0; i < 8; i++) {
            if (enemy2[i] != null)
                enemy2[i].draw(renderer.getBatch());
        }

        for (int i = 0; i < 8; i++) {
            if (enemy3[i] != null)
                enemy3[i].draw(renderer.getBatch());
        }

        disparoN.draw(renderer.getBatch());
        disparoE1.draw(renderer.getBatch());
        disparoE2.draw(renderer.getBatch());
        disparoE3.draw(renderer.getBatch());
        disparoE4.draw(renderer.getBatch());
        if (pantalla == 3){
            disparoE5.draw(renderer.getBatch());
            disparoE6.draw(renderer.getBatch());
            disparoE7.draw(renderer.getBatch());
        }
        vida.draw(renderer.getBatch());
        setPuntos(renderer.getBatch());

        t = "" + (int)time;
        tiempo(renderer.getBatch());

        renderer.getBatch().end();

        if (Gdx.input.isTouched ()) {
            isTouched = true;
            isStart = true;
            nave.setPosition(Gdx.input.getX() - (250/2),50);
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            space.setEndGame(puntos);
        }


        update();
    }

    public void sprites(){
        font.getData().setScale(5,5);
        layout = new GlyphLayout();
        layout2 = new GlyphLayout();
        nave = new Nave(new Sprite(new Texture("nave.png")));
        nave.setPosition((1440/2) - (250/2),50);
        nave.setSize(250,179);

        if (vidas == 5) {
            vida = new Sprite(new Texture("vidas5.png"));
            vida.setPosition(20, 2500);
            vida.setSize(500, 50);
        }

        for (int i = 0; i < 8; i++) {
            enemy1[i] = new Enemy(new Sprite(new Texture(enemigos[(-1 + pantalla)])),
                    1.5f,(1 + (pantalla % 2)));
            enemy1[i].setPosition(i*140, 1200);
            enemy1[i].setSize(137, 100);
        }

        for (int i = 0; i < 8; i++) {
            enemy2[i] = new Enemy(new Sprite(new Texture(enemigos[(2 + pantalla)])),
                    1.5f,(2 + (pantalla % 2)));
            enemy2[i].setPosition(i*140, 1350);
            enemy2[i].setSize(137, 100);
        }

        for (int i = 0; i < 8; i++) {
            enemy3[i] = new Enemy(new Sprite(new Texture(enemigos[(5 + pantalla)])),
                    1.5f,(3 + (pantalla % 2)));
            enemy3[i].setPosition(i*140, 1500);
            enemy3[i].setSize(137, 100);
        }

        disparoN = new Disparo(new Sprite(new Texture("disparo.png")),50);
        disparoN.setPosition(nave.getX() + (250/2) - 10,200);
        disparoN.setSize(20,68);

        if (pantalla == 1) {
            disparoE1 = new Disparo(new Sprite(new Texture("disparo.png")), -4);

            disparoE1.setPosition(enemy1[0].getX(), enemy1[0].getY() - 100);
            disparoE1.setSize(20, 68);

            int index = r.nextInt(8);
            disparoE2 = new Disparo(new Sprite(new Texture("disparo.png")), -4);
            disparoE2.setPosition(enemy2[index].getX(), enemy2[index].getY() - 100);
            disparoE2.setSize(20, 68);

            index = r.nextInt(8);
            disparoE3 = new Disparo(new Sprite(new Texture("disparo.png")), -4);
            disparoE3.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
            disparoE3.setSize(20, 68);

            index = r.nextInt(8);
            disparoE4 = new Disparo(new Sprite(new Texture("disparo.png")), -4);
            disparoE4.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
            disparoE4.setSize(20, 68);
        }

        int index = r.nextInt(8);
        disparoE5 = new Disparo(new Sprite(new Texture("disparo.png")), -4);
        disparoE5.setPosition(enemy2[index].getX(), enemy2[index].getY() - 100);
        disparoE5.setSize(20, 68);

        index = r.nextInt(8);
        disparoE6 = new Disparo(new Sprite(new Texture("disparo.png")), -4);
        disparoE6.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
        disparoE6.setSize(20, 68);

        index = r.nextInt(8);
        disparoE7 = new Disparo(new Sprite(new Texture("disparo.png")), -4);
        disparoE7.setPosition(enemy3[index].getX(), enemy3[index].getY() - 100);
        disparoE7.setSize(20, 68);

        stage = new Stage(new ScreenViewport());

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        fondo.dispose();
        renderer.dispose();
        nave.getTexture().dispose();
        for (int i = 0; i < 8; i++)
            if (enemy1[i] != null)
                enemy1[i].getTexture().dispose();
        for (int i = 0; i < 8; i++)
            if (enemy2[i] != null)
                enemy2[i].getTexture().dispose();
        for (int i = 0; i < 8; i++)
            if (enemy3[i] != null)
                enemy3[i].getTexture().dispose();
        disparoN.getTexture().dispose();
        disparoE1.getTexture().dispose();
        disparoE2.getTexture().dispose();
        disparoE3.getTexture().dispose();
        disparoE4.getTexture().dispose();
        disparo.dispose();
        invaderMuerto.dispose();
        explosion.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean muertos (){
        for (int i = 0; i < 8; i++){
            if (enemy1[i] != null || enemy2[i] != null || enemy3[i] != null ){
                return false;
            }
        }
        return true;
    }

    private void setPuntos(Batch batch){
        layout2.setText(font, "Puntos: "+puntos);
        font.draw(batch,layout2,800, 2560);
    }

    private void tiempo (Batch batch){
        layout.setText(font, "T - " + t);
        font.draw(batch,layout,800, 2400);
    }
}
