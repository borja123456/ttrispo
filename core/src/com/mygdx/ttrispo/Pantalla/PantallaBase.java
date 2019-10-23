package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.ttrispo.MyGdxGame;

public class PantallaBase implements Screen {

    protected Stage stage;
    protected Skin skin;
    private ShapeRenderer shapeRenderer;
    private Texture backGround;
    private int altura, anchura;
    protected SpriteBatch batch;
    private MyGdxGame game;

    public PantallaBase(MyGdxGame game) {
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        backGround = new Texture("background.jpeg");
        anchura = backGround.getWidth();
        altura = backGround.getHeight();
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //procesa todos los eventos de los actores: el botón AKA: sale rojo cuando pulsas!!
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null); //para dejar de usar este stage cuando cambiemos de pantalla
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f,0.1f, 1f);

       /* batch.begin();
        batch.draw(backGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end(); */
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
    public void dispose() {
        stage.dispose();
        skin.dispose();
        shapeRenderer.dispose();
    }
}

