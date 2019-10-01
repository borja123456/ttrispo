package com.mygdx.ttrispo.Pantalla;

public class PiezaS {

    public static final int T = 1;
    public static final int I = 2;

    protected int f,c, numBlock;
    private int giro = 1;
    private int tipo;

    public PiezaS(int f,int c) {
        this.f = f;
        this.c = c;
        tipo = Pieza.T;
        this.numBlock = 4;
    }

    protected int [][] getPosicionPiezaS(){
        int [][] r = new int[numBlock][4];
        switch (giro){
            case(1):
                r[0][0] = this.f + 1; // Fila
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
                r[0][1] = this.c - 1; // Columna

                r[1][0] = this.f + 1;
                r[1][1] = this.c - 1;

                r[2][0] = this.f + 1;
                r[2][1] = this.c;

                r[3][0] = this.f + 2;
                r[3][1] = this.c;
                break;
        } 
        return r;
    }

    public int [][] bajarS(){
        int [][] r = new int[4][2];
        switch (giro){
            case(1):
                r[0][0] = this.f + 2; // Fila
                r[0][1] = this.c - 1; // Columna

                r[1][0] = this.f + 1;
                r[1][1] = this.c;

                r[2][0] = this.f + 1;
                r[2][1] = this.c + 1;

                r[3][0] = this.f + 2;
                r[3][1] = this.c;
                break;
            case(2):
                r[0][0] = this.f + 1; // Fila
                r[0][1] = this.c - 1; // Columna

                r[1][0] = this.f + 2;
                r[1][1] = this.c - 1;

                r[2][0] = this.f + 2;
                r[2][1] = this.c;

                r[3][0] = this.f + 3;
                r[3][1] = this.c;
                break;
        }
        //TODO Preguntar si la pieza cae
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