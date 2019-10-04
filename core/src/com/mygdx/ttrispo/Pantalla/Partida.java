package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.ttrispo.Tablero;

public class Partida extends PantallaBase  {
    private Tablero tablero;
    private GestorEstado gEstado;
    private GestorPiezas gPieza;

    public Partida(){
        gEstado = new GestorEstado(this);
        gPieza = new GestorPiezas(this);
        Procesador pc = new Procesador(gEstado);
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
        cicloDeVida(delta);
        stage.draw();
    }

    private void cicloDeVida(float delta) {
        Pieza currentPieza;

        switch (gEstado.getEstado(delta)){
            // Pieza en reposo
            case (GestorEstado.REPOSO):

                break;
            case (GestorEstado.SINPIEZA):
                this.insertarNextPieza();
                gEstado.setFlagSinFicha(false);
                break;

            case (GestorEstado.DERECHA):
                currentPieza = gPieza.getCurrentPieza();
                int posicionPiezaDerecha [][] = currentPieza.getPosicionDerecha();
                tablero.cambiarBloque(posicionPiezaDerecha ,currentPieza.getTipo());
                currentPieza.setF(currentPieza.c + 1);

            // La pieza intenta caer
            case (GestorEstado.CAER):
                currentPieza = gPieza.getCurrentPieza();

                int posicionPiezaAbajo [][] = currentPieza.getPosicionAbajo();
                tablero.cambiarBloque(currentPieza.getPosicionPieza(),Pieza.VACIA);
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
        Pieza pieza = gPieza.getNextPieza();
        tablero.cambiarBloque(pieza.getPosicionPieza(),pieza.getTipo());
        return false;
    }
}
