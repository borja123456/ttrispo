package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.ttrispo.Tablero;

public class Partida extends PantallaBase {
    private Tablero tablero;
    private GestorEstado gEstado;
    private GestorPiezas gPieza;
    private boolean flag = false;

    public Partida(){
        gEstado = new GestorEstado(this);
        gPieza = new GestorPiezas(this);
    }

    @Override
    public void show() {
        super.show();
        tablero = new Tablero();
        stage.addActor(tablero);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.4f, 0.8f,0.3f, 1f);
        // Ciclo de vida
        switch (gEstado.getEstado(delta)){
            // Pieza en reposo
            case (GestorEstado.REPOSO):
                if(!flag){
                    System.out.println("La pieza esta en reposo");
                    flag = true;
                }
                break;
            // La pieza intenta caer
            case (GestorEstado.CAER):
                System.out.println("La pieza cae");
                flag = false;
                break;
        }
        stage.draw();
    }
}
