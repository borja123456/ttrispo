package com.mygdx.ttrispo.Gestores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.ttrispo.Pantalla.Partida;
import com.mygdx.ttrispo.Pieza.Pieza;
import com.mygdx.ttrispo.Pieza.PiezaI;
import com.mygdx.ttrispo.Pieza.PiezaJ;
import com.mygdx.ttrispo.Pieza.PiezaL;
import com.mygdx.ttrispo.Pieza.PiezaO;
import com.mygdx.ttrispo.Pieza.PiezaS;
import com.mygdx.ttrispo.Pieza.PiezaT;
import com.mygdx.ttrispo.Pieza.PiezaZ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class GestorPiezas {
    private final Partida partida;
    // Colecion de pieza
    private Pieza piezas[];
    //    private PiezaT pT;
//    private PiezaS pS;
//    private PiezaZ pZ;
//    private PiezaI pI;
//    private PiezaO pO;
//    private PiezaL pL;
//    private PiezaJ pJ;
//    public static Queue<Pieza> piezasEncoladas;
    public static ArrayList<Integer> piezasEncoladas;
    private Pieza currentPieza;
    public static int aleatorio;
    private Random rand;


    public GestorPiezas(Partida partida) {
//        piezasEncoladas = new ArrayDeque<Pieza>();
        piezasEncoladas = new ArrayList<Integer>();
        this.partida = partida;
        this.addPiezas();
        rellenarCola();
    }

//    private void rellenarCola() {
//        for(int i=0;i<2;i++){
//            generarPiezaAleatoria();
//            piezasEncoladas.add(newPieza());
//        }
//    }

    private void rellenarCola() {
        while (piezasEncoladas.size() < 4) {
            piezasEncoladas.add(getTypePiezaRandom());
        }
    }

    public void generarPiezaAleatoria() {
        this.rand = new Random();
        this.aleatorio = rand.nextInt(7) + 1;
    }

    private int getTypePiezaRandom() {
        this.rand = new Random();
        return rand.nextInt(7) + 1;
    }

    //    private Pieza newPieza(){
//        ArrayList<Pieza> piezas = new ArrayList<>();
//        Pieza piezaAux;
//        this.pT=new PiezaT(0, 5);
//        this.pS=new PiezaS(0, 5);
//        this.pZ=new PiezaZ(0, 5);
//        this.pI=new PiezaI(0, 5);
//        this.pO=new PiezaO(0, 5);
//        this.pL=new PiezaL(0, 5);
//        this.pJ=new PiezaJ(0, 5);
//        piezas.add(null);
//        piezas.add(pT);
//        piezas.add(pS);
//        piezas.add(pZ);
//        piezas.add(pI);
//        piezas.add(pO);
//        piezas.add(pL);
//        piezas.add(pJ);
//        piezaAux = piezas.get(this.aleatorio);
//        return piezaAux;
//    }
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

    public Pieza sPieza() {
        rellenarCola();
        return currentPieza;
    }

    public Texture getTexture(int tipo) {
        return piezas[tipo].getTexture();
    }

    public Texture getNombreTexture(int tipo) {
        return Pieza.getImagen(tipo);
    }

    public int getTipoNextPieza() {
        return 0;
    }

    public void bloquearPieza() {
        currentPieza.bloquear();
        currentPieza = null;
    }

    public Texture getImagenNextPieza() {
        return piezas[piezasEncoladas.get(1)].getImagen();
    }
}
