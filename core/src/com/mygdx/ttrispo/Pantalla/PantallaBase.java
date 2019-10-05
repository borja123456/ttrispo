package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.ttrispo.Tablero;


import java.util.ArrayList;


public class PantallaBase implements Screen {

    protected Stage stage;
    protected Skin skin;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch sp;
    private Texture img;
    private int alto, largo;


    public PantallaBase() {
        shapeRenderer = new ShapeRenderer();
        sp = new SpriteBatch();
        img = new Texture("tablero.png");
        alto = img.getHeight();
        largo = img.getWidth();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sp.begin();

        sp.draw(img, 80,Tablero.tablero.length*40,
                largo*2.5f,alto*2.5f);

        sp.end();
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
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        shapeRenderer.dispose();
        sp.dispose();
    }
}

