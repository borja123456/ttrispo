package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {
//    SpriteBatch batch;
//    Texture img;
//    float vx = 0, vy = 0, x = 0, y = 0, ax = 0f, ay = -4;
//    int width, height;
    private Partida partida;
    public static float ratioPixelesHeight, ratioPixelesWidth;

    @Override
    public void create() {

        GestorRecursos.cargarImagenes();
        partida = new Partida();
        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();
//        batch = new SpriteBatch();
//        img = new Texture("badlogic.jpg");
//        height = img.getHeight();
//        width = img.getWidth();
//        x = Gdx.graphics.getWidth() / 2 - (width / 2);
//        y = Gdx.graphics.getHeight() - height / 2 ;

        this.setScreen(partida);
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
