package com.mygdx.ttrispo.Pantalla;

public class Pieza {

    protected static final int VACIA = 0;
    public static final int T = 1; //PARA TESTEAR UNA PIEZA, POR EL MOMENTO, HAY QUE AJUSTAR ESTE 1 A LA FICHA QUE SE QUIERA TESTEAR, Y CAMBIAR ESTA PIEZA T A OTRO NUMERO
    public static final int S = 2;
    public static final int Z = 3;
    public static final int J = 4;
    public static final int L = 5;
    public static final int I = 6;
    public static final int O = 7;

    int f,c, numBlock;
    protected int giro = 1;
    protected int tipo;


    public Pieza(int f, int c){
        this.f = f;
        this.c = c;
        this.numBlock = 4; 
    }

    protected int [][] getPosicionPieza(){
        return null;
    }

    public int [][] getPosicionAbajo(){
        int [][] r = this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][0]++;
        }
        return r;
    }

    public int getTipo() {
        return tipo;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setC(int c) {
        this.c = c;
    }
}
