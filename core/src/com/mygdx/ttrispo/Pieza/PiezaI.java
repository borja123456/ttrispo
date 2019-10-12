package com.mygdx.ttrispo.Pieza;

public class PiezaI extends Pieza {

    public PiezaI(int f, int c) {
        super(f, c);
        tipo = I;
        this.texture = getTexture(this.tipo);
    }

    public int[][] getPosicionPieza() {
        int[][] r = new int[super.numBlock][2];
        switch (giro) {
            case (1): // 0º -> 90º  (orientacion 1 a orientacion 2)
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna - 1;

                r[2][0] = this.fila + 1;
                r[2][1] = this.columna - 1;

                r[3][0] = this.fila + 2;
                r[3][1] = this.columna - 1;
                break;
            case (2): // 90º -> 180º  (orientacion 2 a orientacion 3)
                r[0][0] = this.fila + 2; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila + 2;
                r[1][1] = this.columna;

                r[2][0] = this.fila + 2;
                r[2][1] = this.columna + 1;

                r[3][0] = this.fila + 2;
                r[3][1] = this.columna + 2;
                break;
            case (3): // 180º -> 270º  (orientacion 3 a orientacion 4)
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna + 2; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna + 2;

                r[2][0] = this.fila + 1;
                r[2][1] = this.columna + 2;

                r[3][0] = this.fila + 2;
                r[3][1] = this.columna + 2;
                break;
            case (4): // 270º -> 0º  (orientacion 4 a orientacion 1)
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila - 1;
                r[1][1] = this.columna;

                r[2][0] = this.fila - 1;
                r[2][1] = this.columna + 1;

                r[3][0] = this.fila - 1;
                r[3][1] = this.columna + 2;
                break;
        }
        return r;
    }
}
