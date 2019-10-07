package com.mygdx.ttrispo.Pantalla;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class GestorPiezas {
    private final Partida partida;
    //private Queue<Pieza> piezas = new ArrayDeque<>();
    private PiezaT pT;
    private PiezaS pS;
    private PiezaZ pZ;
    private PiezaI pI;
    private PiezaO pO;
    private PiezaL pL;
    private PiezaJ pJ;
    public static Queue<Pieza> piezasEncoladas;
    private Pieza currentPieza;
    public static int aleatorio;
    private Random rand;


    public GestorPiezas(Partida partida) {
        piezasEncoladas = new ArrayDeque<Pieza>();
        this.partida = partida;
        rellenarCola();
    }

    private void rellenarCola() {
        for(int i=0;i<2;i++){
            generarPiezaAleatoria();
            piezasEncoladas.add(newPieza());
        }
    }

    public void generarPiezaAleatoria(){
        this.rand = new Random();
        this.aleatorio = rand.nextInt(7)+1;
    }

    private Pieza newPieza(){
        ArrayList<Pieza> piezas = new ArrayList<>();
        Pieza piezaAux;
        this.pT=new PiezaT(0, 5);
        this.pS=new PiezaS(0, 5);
        this.pZ=new PiezaZ(0, 5);
        this.pI=new PiezaI(0, 5);
        this.pO=new PiezaO(0, 5);
        this.pL=new PiezaL(0, 5);
        this.pJ=new PiezaJ(0, 5);
        piezas.add(null);
        piezas.add(pT);
        piezas.add(pS);
        piezas.add(pZ);
        piezas.add(pI);
        piezas.add(pO);
        piezas.add(pL);
        piezas.add(pJ);
        piezaAux = piezas.get(this.aleatorio);
        return piezaAux;
    }

    public Pieza getCurrentPieza() {
        return currentPieza;
    }

    public Pieza getNextPieza() {

        currentPieza = piezasEncoladas.poll();
        if(piezasEncoladas.size()==0) {
            rellenarCola();
        }
        return currentPieza;
    }
}
