package com.mygdx.ttrispo.Pantalla;

import java.util.ArrayDeque;
import java.util.Queue;

public class GestorPiezas {
    private final Partida partida;
    private Queue<Integer> piezas = new ArrayDeque<>();
    private  Pieza currentPieza;

    public GestorPiezas(Partida partida) {
        this.partida = partida;
    }

    public Pieza getCurrentPieza() {
        if(this.currentPieza == null){
            this.currentPieza = new Pieza(0,5);
        }
        return currentPieza;
    }

    public Pieza getNextPieza() {
        this.currentPieza = null;
        return this.getCurrentPieza();
    }
}
