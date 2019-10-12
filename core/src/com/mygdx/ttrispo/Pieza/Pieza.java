package com.mygdx.ttrispo.Pieza;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ttrispo.Gestores.GestorRecursos;

public class Pieza {

    public static final int T = 1;
    public static final int S = 2;
    public static final int Z = 3;
    public static final int J = 4;
    public static final int L = 5;
    public static final int I = 6;
    public static final int O = 7;

    public static final int VACIA = 0;

    protected int fila, columna, numBlock;
    protected int tipo, giro;
    protected Texture texture;
    protected Texture imagen;

    public Pieza(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.numBlock = 4;
        this.tipo = this.T;
        this.giro = 1;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public int[][] getPosicionPieza() {
        int[][] r = new int[numBlock][2];
        return r;
    }

    public int[][] getPosicionAbajo() {
        int[][] r = this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][0]++;
        }
        return r;
    }

    public int[][] getPosicionDerecha() {
        int[][] r = this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][1]++;
        }
        return r;
    }

    public int[][] getPosicionIzquierda() {
        int[][] r = this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][1]--;
        }
        return r;
    }

    public void bloquear() {
        this.fila = 0;
        this.columna = 5;
        this.giro = 1;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setGiro(int giro) {
        this.giro += giro;
    }

    public int getGiro() {
        return giro;
    }

    public void girarDer() {
        if (giro < 4) {
            giro++;
        } else {
            giro = 1;
        }
    }

    public void girarIz() {
        if (giro > 1) {
            giro--;
        } else {
            giro = 4;
        }
    }

    public int getColumna() {
        return this.columna;
    }

    public int getFila() {
        return this.fila;
    }

    public Texture getImagen() {
        return this.imagen;
    }
}
