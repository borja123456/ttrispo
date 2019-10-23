package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.ttrispo.MyGdxGame;


import javax.swing.plaf.nimbus.State;

public class PantallaGameOver extends PantallaBase {
    private State state;
    private Skin skin;
    private TextButton retry;

    public PantallaGameOver(final MyGdxGame game){
        super(game);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        retry = new TextButton("Retry", skin);

        retry.setSize(300,100);
        retry.setPosition(Gdx.graphics.getWidth()/2.65f, Gdx.graphics.getHeight()/2);
        stage.addActor(retry);

        retry.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.pantallaInicio);
            }
        });
    }


    @Override
    public void show() {
        super.show();
        //Gdx.input.setInputProcessor(stage); //procesa todos los eventos de los actores: el botón AKA: sale rojo cuando pulsas!!
    }

    @Override
    public void hide() {
        super.hide();
        //Gdx.input.setInputProcessor(null); //para dejar de usar este stage cuando cambiemos de pantalla
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.4f,0.2f,0.7f,0.7f); //morada
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
