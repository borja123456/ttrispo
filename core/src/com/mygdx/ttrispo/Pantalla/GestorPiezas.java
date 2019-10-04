package com.mygdx.ttrispo.Pantalla;

import java.util.ArrayDeque;
import java.util.Queue;

public class GestorPiezas {
    private final Partida partida;
    private Queue<Integer> piezas = new ArrayDeque<>();
    private PiezaT currentPieza;

    public GestorPiezas(Partida partida) {
        this.partida = partida;
    }

    public PiezaT getCurrentPieza() {
        if(this.currentPieza == null){
            this.currentPieza = new PiezaT(0,5);
        }
        return currentPieza;
    }

    public PiezaT getNextPieza() {
        this.currentPieza = null;
        return this.getCurrentPieza();
    }
}
