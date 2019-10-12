package com.mygdx.ttrispo.Gestores;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ttrispo.Pantalla.Partida;
import com.mygdx.ttrispo.Pieza.Pieza;
import com.mygdx.ttrispo.Pieza.PiezaI;
import com.mygdx.ttrispo.Pieza.PiezaJ;
import com.mygdx.ttrispo.Pieza.PiezaL;
import com.mygdx.ttrispo.Pieza.PiezaO;
import com.mygdx.ttrispo.Pieza.PiezaS;
import com.mygdx.ttrispo.Pieza.PiezaT;
import com.mygdx.ttrispo.Pieza.PiezaZ;

import java.util.ArrayList;
import java.util.Random;

public class GestorPiezas {
    private final Partida partida;
    // Colecion de pieza
    private Pieza piezas[];
    public static ArrayList<Integer> piezasEncoladas;
    private Pieza currentPieza;
    public static int aleatorio;
    private Random rand;

    public GestorPiezas(Partida partida) {
        piezasEncoladas = new ArrayList<Integer>();
        this.partida = partida;
        this.addPiezas();
        rellenarCola();
    }

    private void rellenarCola() {
        while (piezasEncoladas.size() < 4) {
            piezasEncoladas.add(getTypePiezaRandom());
        }
    }

    private int getTypePiezaRandom() {
        this.rand = new Random();
        return rand.nextInt(7) + 1;
    }

    private void addPiezas() {
        this.piezas = new Pieza[8];
        piezas[Pieza.T] = new PiezaT(0, 5);
        piezas[Pieza.S] = new PiezaS(0, 5);
        piezas[Pieza.Z] = new PiezaZ(0, 5);
        piezas[Pieza.I] = new PiezaI(0, 5);
        piezas[Pieza.O] = new PiezaO(0, 5);
        piezas[Pieza.L] = new PiezaL(0, 5);
        piezas[Pieza.J] = new PiezaJ(0, 5);
    }

    public Pieza getCurrentPieza() {
        if(currentPieza == null){
            currentPieza = piezas[piezasEncoladas.remove(0)];
            rellenarCola();
        }
        return currentPieza;
    }

    public void bloquearPieza() {
        currentPieza.bloquear();
        currentPieza = null;
    }

    public Texture getImagenNextPieza() {
        return piezas[piezasEncoladas.get(1)].getImagen();
    }
}
