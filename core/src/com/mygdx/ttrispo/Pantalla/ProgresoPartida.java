package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.ttrispo.Tablero;

public class ProgresoPartida extends Actor {
    private BitmapFont font;
    private Partida partida;
    private Tablero tablero;

    public ProgresoPartida(Partida partida, Tablero tablero){
        this.partida = partida;
        this.font = new BitmapFont();
        this.tablero = tablero;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.getData().setScale(8);
        font.draw(batch, ""+tablero.getPuntuacion(),
                Gdx.graphics.getWidth()/5, 94*Gdx.graphics.getHeight()/3);
    }
}
