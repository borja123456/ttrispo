package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ttrispo.Gestores.GestorEstado;
import com.mygdx.ttrispo.Gestores.GestorPiezas;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pieza.Pieza;
import com.mygdx.ttrispo.Procesador;
import com.mygdx.ttrispo.Tablero;

public class Partida extends PantallaBase {
    private GestorRecursos gRecursos;
    private Tablero tablero;
    private ProgresoPartida progresoPartida;
    private GestorEstado gEstado;
    private GestorPiezas gPieza;
    public static float x, y;
    private Procesador pc;
    private GameOver gameOver;
    
    private static int puntuacion;

    public Partida() {
        gEstado = new GestorEstado(this);
        gPieza = new GestorPiezas(this);
        pc = new Procesador(gEstado);
        gameOver = new GameOver(this);

        Gdx.input.setInputProcessor(pc);

        gRecursos.cargarImagenes();
    }

    @Override
    public void show() {
        super.show();
        tablero = new Tablero(this);
        progresoPartida = new ProgresoPartida(this);
        stage.addActor(tablero);
        stage.addActor(progresoPartida);
        tablero.setPosition(x, y);
        
        this.puntuacion = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        // Ciclo de vida
        cicloDeVida(delta);
        stage.draw(); // pintar los actores
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void cicloDeVida(float delta) {
        switch (gEstado.getEstado(delta)) {
            // Pieza en reposo
            case (GestorEstado.REPOSO):
                if (gPieza.getCurrentPieza() == null) {
                    gEstado.setEstado(GestorEstado.SINPIEZA);
                }
                break;
            case (GestorEstado.SINPIEZA):
                sinPiezaState();
                break;
            case (GestorEstado.IZQUIERDA):
                moverIzquierdaState();
                break;
            case (GestorEstado.DERECHA):
                moverDerechaState();
                break;
            // La pieza intenta caer
            case (GestorEstado.CAER):
                caerState();
                break;
            case (GestorEstado.GIRO):
                giroState();
                break;
            case (GestorEstado.BLOQUEAR):
                bloquearPieza();
                break;
        }
    }

    private void bloquearPieza() {
        // La pieza no puede bajar
        Pieza currentPieza = gPieza.getCurrentPieza();
        tablero.cambiarBloque(currentPieza.getPosicionPieza(), currentPieza.getTipo());
        if (tablero.comprobarGameOver(currentPieza.getPosicionPieza())) {
            stage.clear();
            stage.addActor(gameOver);
        }
        tablero.comprobarLineaCompleta();
        gPieza.bloquearPieza();
        gEstado.setEstado(GestorEstado.SINPIEZA);
    }

    private void giroState() {
        Pieza currentPieza;
        currentPieza = gPieza.getCurrentPieza();
        tablero.cambiarBloque(currentPieza.getPosicionPieza(), Pieza.VACIA);
        currentPieza.girarDer();
        int piezaGirada[][] = currentPieza.getPosicionPieza();
        if (tablero.isColision(piezaGirada)) {
            // La pieza no puede girar
            currentPieza.girarIz();
            tablero.cambiarBloque(currentPieza.getPosicionPieza(), currentPieza.getTipo());
        } else {
            tablero.cambiarBloque(currentPieza.getPosicionPieza(), currentPieza.getTipo());
        }
        gEstado.setEstado(GestorEstado.REPOSO);
    }

    private void caerState() {
        Pieza currentPieza;
        currentPieza = gPieza.getCurrentPieza();

        int posicionPiezaAbajo[][] = currentPieza.getPosicionAbajo();
        tablero.cambiarBloque(currentPieza.getPosicionPieza(), Pieza.VACIA);
        if (tablero.isColision(posicionPiezaAbajo)) {
            gEstado.setEstado(GestorEstado.BLOQUEAR);
        } else {
            // La pieza puede baja
            tablero.cambiarBloque(posicionPiezaAbajo, currentPieza.getTipo());
            currentPieza.setFila(currentPieza.getFila() + 1);
            gEstado.setEstado(GestorEstado.REPOSO);
        }
    }

    private void moverDerechaState() {
        Pieza currentPieza;
        currentPieza = gPieza.getCurrentPieza();
        int posicionPiezaDerecha[][] = currentPieza.getPosicionDerecha();
        tablero.cambiarBloque(currentPieza.getPosicionPieza(), Pieza.VACIA);
        if (tablero.isColision(posicionPiezaDerecha)) {
            tablero.cambiarBloque(currentPieza.getPosicionPieza(), currentPieza.getTipo());
            //si no puede seguir moviendo a la derecha pues ahi se queda
        } else {
            tablero.cambiarBloque(posicionPiezaDerecha, currentPieza.getTipo());
            currentPieza.setColumna(currentPieza.getColumna() + 1);
        }
        //Cambia a reposo. pero esto hay que refactorizarlo por el amor de dios
        gEstado.setEstado(gEstado.REPOSO);
    }

    private void moverIzquierdaState() {
        Pieza currentPieza;
        currentPieza = gPieza.getCurrentPieza();
        int posicionPiezaIzquierda[][] = currentPieza.getPosicionIzquierda();
        tablero.cambiarBloque(currentPieza.getPosicionPieza(), Pieza.VACIA);
        if (tablero.isColision(posicionPiezaIzquierda)) {
            tablero.cambiarBloque(currentPieza.getPosicionPieza(), currentPieza.getTipo());
            //si no puede seguir moviendo a la izquierda pues ahi se queda
        } else {
            tablero.cambiarBloque(posicionPiezaIzquierda, currentPieza.getTipo());
            currentPieza.setColumna(currentPieza.getColumna() - 1);
        }
        //Cambia a reposo. pero esto hay que refactorizarlo por el amor de dios
        gEstado.setEstado(gEstado.REPOSO);
    }

    private void sinPiezaState() {
        Pieza pieza = gPieza.getCurrentPieza();
        tablero.cambiarBloque(pieza.getPosicionPieza(), pieza.getTipo());
        tablero.setNextPiezaImagen(gPieza.getImagenNextPieza());
        gEstado.setEstado(GestorEstado.REPOSO);
    }

    public Texture getTexturaPieza(int tipo){
        return gPieza.getTexturaBloque(tipo);
    }

    public Texture getImagenNextPieza() {
        return gPieza.getImagenNextPieza();
    }
    
    public int getPuntuacion(){
        return this.puntuacion;
    }
    
    public void setPuntuacion(int i) {
        puntuacion+=i;
    }
}
