package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {

    public static float ratioPixelesHeight, ratioPixelesWidth;
    private Partida partida;

    @Override
    public void create() {

        GestorRecursos.cargarImagenes();
        partida = new Partida();

        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();


        this.setScreen(partida);
    }


    @Override
    public void dispose() {
//        batch.dispose();
//        img.dispose();
    }




}
