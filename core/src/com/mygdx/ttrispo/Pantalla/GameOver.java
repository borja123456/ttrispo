package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.ttrispo.Tablero;

public class GameOver extends Actor {
    private Texture imagen;
    private Partida partida;
    private BitmapFont font;

    public GameOver(Partida partida){
        this.partida=partida;
        imagen = new Texture("bg_go.png");
        font = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(imagen, Gdx.graphics.getHeight()/8, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3);
        batch.setColor(1, 1, 1, 0.9f);
        font.getData().setScale(2f);
        font.draw(batch, "PUNTUACION: " + Tablero.puntuacion, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2);
    }

}
