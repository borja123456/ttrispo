package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Tablero;

import java.util.ArrayList;
import java.util.Random;

public class Partida extends PantallaBase {
    private Tablero tablero;
    private GestorEstado gEstado;
    private GestorPiezas gPieza;
    //public static ArrayList<Pieza> piezas = new ArrayList<Pieza>();
    //private Random rand;
    //private int num;

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
        Gdx.gl.glClearColor(0.5f, 0.5f,0.5f, 1f);
        // Ciclo de vida
        cicloDeVida(delta);
        stage.draw();
    }

    private void cicloDeVida(float delta) {
        PiezaT currentPieza;//-----------------------------------
        //PiezaI currentPieza2 = new PiezaI(0, 4);//-----------------------------------
        //piezas.add(currentPieza);
        //piezas.add(currentPieza2);
        switch (gEstado.getEstado(delta)){
            // PiezaT en reposo
            case (GestorEstado.REPOSO):

                break;
            case (GestorEstado.SINPIEZA):
                this.insertarNextPieza();
                gEstado.setFlagSinFicha(false);
                break;
            // La pieza intenta caer
            case (GestorEstado.CAER):
                currentPieza = gPieza.getCurrentPieza();//-----------------------------------

                int posicionPiezaAbajo [][] = currentPieza.getPosicionAbajo();
                tablero.cambiarBloque(currentPieza.getPosicionPieza(), PiezaT.VACIA);
                if(tablero.isColision(posicionPiezaAbajo)){
                    // La pieza no puede bajar
                    tablero.cambiarBloque(currentPieza.getPosicionPieza() ,currentPieza.getTipo());
                    gEstado.setFlagSinFicha(true);

                }else{
                    // La pieza puede baja
                    tablero.cambiarBloque(posicionPiezaAbajo ,currentPieza.getTipo());
                    currentPieza.setF(currentPieza.f + 1);
                }
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public boolean insertarNextPieza() {
        PiezaT pieza = gPieza.getNextPieza();
        tablero.cambiarBloque(pieza.getPosicionPieza(),pieza.getTipo());
        return false;
    }
}
