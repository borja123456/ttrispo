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
    private Texture imagen;
    private Partida partida;
    private BitmapFont font;

    private State state;
    private Image gameover;

    private TextButton retry;
    private Skin skin;

    public PantallaGameOver(final MyGdxGame game){
        super(game);
        //this.partida=partida;
        //imagen = new Texture("bg_go.png");
        //font = new BitmapFont();

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        retry = new TextButton("Retry", skin);

        //gameover = new Image(GestorRecursos.get("bg_go.png"));
        //gameover.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/8); //Hay que coger extactamente las coord para ponerlo arriba!!
        //stage.addActor(gameover);

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
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.4f,0.2f,0.7f,0.7f);
        stage.act();
        stage.draw();
    }

    /*@Override
        public void draw(float delta) {
            //super.draw(batch, parentAlpha);
            //batch.draw(imagen, Gdx.graphics.getHeight()/8, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3);
            //batch.setColor(1, 1, 1, 0.9f);
            font.getData().setScale(8);
           // font.draw(batch, "" + Tablero.puntuacion, Gdx.graphics.getWidth()/5, 93*Gdx.graphics.getHeight()/100);
        }*/

    @Override
    public void dispose() {
        stage.dispose();
    }
}
