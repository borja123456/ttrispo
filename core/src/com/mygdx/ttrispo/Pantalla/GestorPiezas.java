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
}
