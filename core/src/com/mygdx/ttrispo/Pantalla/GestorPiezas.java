package com.mygdx.ttrispo.Pantalla;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GestorPiezas {
    private final Partida partida;
    private LinkedList<Pieza> colaPiezasSiguientes = new LinkedList<>();
    private ArrayList<Pieza> piezas = new ArrayList<>();
    private PiezaT pT;
    private PiezaS pS;
    private PiezaZ pZ;
    private PiezaI pI;
    private PiezaO pO;
    private PiezaL pL;
    private PiezaJ pJ;
    private Pieza currentPieza, piezaAux;
    public static int aleatorio;
    private Random rand;


    public GestorPiezas(Partida partida) {
        this.partida = partida;
    }

    public void generarPiezaAleatoria(){
        for (int i = 0; i <= 1; i++){
            Random numRandom = new Random();
            int posicion = numRandom.nextInt(7) + 1;

            Pieza piezaSiguiente = piezas.get(posicion);
            this.colaPiezasSiguientes.add(piezaSiguiente);
        }
    }
    public LinkedList<Pieza> getColaPiezasSiguientes() {
        return this.colaPiezasSiguientes;
    }

    private Pieza newPieza(){
        this.pT=new PiezaT(0, 5);
        this.pS=new PiezaS(0, 5);
        this.pZ=new PiezaZ(0, 5);
        this.pI=new PiezaI(0, 5);
        this.pO=new PiezaO(0, 5);
        this.pL=new PiezaL(0, 5);
        this.pJ=new PiezaJ(0, 5);
        this.piezas.add(null);
        this.piezas.add(pT);
        this.piezas.add(pS);
        this.piezas.add(pZ);
        this.piezas.add(pI);
        this.piezas.add(pO);
        this.piezas.add(pL);
        this.piezas.add(pJ);
        piezaAux = piezas.get(this.aleatorio);
        piezaAux.setTipo(aleatorio);
        this.piezas.clear();
        return piezaAux;
    }

    public Pieza getCurrentPieza() {
        if(this.currentPieza == null){
            generarPiezaAleatoria();
            this.currentPieza = newPieza();
        }
        return currentPieza;
    }

    public Pieza getNextPieza() {
        this.currentPieza = null;
        return this.getCurrentPieza();
    }
}
