package com.mygdx.ttrispo.Pantalla;

public class PiezaT extends Pieza{

    public PiezaT(int f, int c) {
        super(f, c);
        tipo = T;
    }

    protected int [][] getPosicionPieza(){
        int [][] r = new int[super.numBlock][2];
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
}