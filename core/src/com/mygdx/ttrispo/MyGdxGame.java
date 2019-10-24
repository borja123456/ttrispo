package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {
    //    SpriteBatch batch;
//    Texture img;
//    float vx = 0, vy = 0, posicionX = 0, posicionY = 0, ax = 0f, ay = -4;
//    int width, height;

    public Partida partida;
    public PantallaInicio pantallaInicio;
    public PantallaGameOver pantallaGameOver;


    @Override
    public void create() {

        GestorRecursos.cargarImagenes();
        partida = new Partida(this);
        pantallaInicio = new PantallaInicio(this);
        pantallaGameOver = new PantallaGameOver(this);
//        batch = new SpriteBatch();
//        img = new Texture("badlogic.jpg");
//        height = img.getHeight();
//        width = img.getWidth();
//        posicionX = Gdx.graphics.getWidth() / 2 - (width / 2);
//        posicionY = Gdx.graphics.getHeight() - height / 2 ;
        this.setScreen(pantallaGameOver);
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
