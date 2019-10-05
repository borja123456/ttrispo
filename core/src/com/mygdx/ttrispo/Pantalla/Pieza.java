package com.mygdx.ttrispo.Pantalla;

public class Pieza {

    public static final int T = 1;//Hay que cambiar esto de si se quiere probar otra pieza, y la otra pieza debe llevar el 1
    public static final int S = 2;
    public static final int Z = 3;
    public static final int J = 4;
    public static final int L = 5;
    public static final int I = 6;
    public static final int O = 7;

    public static final int VACIA = 0;

    protected int f,c, numBlock;
    protected int giro = 1;
    protected int tipo;

    public Pieza(int f,int c) {
        this.f = f;
        this.c = c;
        tipo = Pieza.T;
        this.numBlock = 4;
    }

    protected int [][] getPosicionPieza(){
        int [][] r = new int[numBlock][2];
        switch (giro){
            case(1):
                //Bloque 1
                r[0][0] = this.f; // Fila
                r[0][1] = this.c - 1; // Columna

                r[1][0] = this.f;
                r[1][1] = this.c;

                r[2][0] = this.f;
                r[2][1] = this.c + 1;

                r[3][0] = this.f + 1;
                r[3][1] = this.c;
                break;
            case(2):
                r[0][0] = this.f; // Fila
                r[0][1] = this.c; // Columna

                r[1][0] = this.f - 1;
                r[1][1] = this.c;

                r[2][0] = this.f + 1;
                r[2][1] = this.c;

                r[3][0] = this.f;
                r[3][1] = this.c - 1;
                break;
            case(3):
                r[0][0] = this.f; // Fila
                r[0][1] = this.c; // Columna

                r[1][0] = this.f;
                r[1][1] = this.c - 1;

                r[2][0] = this.f - 1;
                r[2][1] = this.c;

                r[3][0] = this.f;
                r[3][1] = this.c + 1;
                break;
            case(4):
                r[0][0] = this.f; // Fila
                r[0][1] = this.c; // Columna

                r[1][0] = this.f - 1;
                r[1][1] = this.c;

                r[2][0] = this.f;
                r[2][1] = this.c + 1;

                r[3][0] = this.f + 1;
                r[3][1] = this.c;
                break;
        }
        return r;
    }

    public int [][] getPosicionAbajo(){
        int [][] r = this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][0]++;
        }
        return r;
    }

    public int [][] getPosicionDerecha(){
        int[][] r= this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][1]++;
        }
        return r;
    }
    public int [][] getPosicionIzquierda(){
        int[][] r= this.getPosicionPieza();
        for (int i = 0; i < r.length; i++) {
            r[i][1]--;
        }
        return r;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getTipo() {
        return this.tipo;
    }
}
