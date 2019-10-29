package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {
    public static float ratioPixelesHeight, ratioPixelesWidth;
    private Partida partida;

    public PantallaInicio pantallaInicio;
    //public Partida partida;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;


    @Override
    public void create() {
        GestorRecursos.cargarImagenes();
        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();
        pantallaInicio = new PantallaInicio(this);
        //partida = new Partida(this);
        pantallaGameOver = new PantallaGameOver(this);
        pantallaAjustes = new PantallaAjustes(this);
        this.setScreen(pantallaAjustes);
    }

//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(1, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
////        batch.begin();
////        batch.draw(img,0,0);
////        batch.end();
//    }

    @Override
    public void dispose() {
//        batch.dispose();
//        img.dispose();
    }




}
