package com.mygdx.ttrispo.Pieza;

import com.mygdx.ttrispo.Gestores.GestorRecursos;

public class PiezaO extends Pieza {

    public PiezaO(int f, int c) {
        super(f, c);
        tipo = O;
        this.texture = GestorRecursos.get("O.jpg");
        this.imagen = GestorRecursos.get("OCompleta.png");
    }

    public int[][] getPosicionPieza() {
        int[][] r = new int[super.numBlock][2];
        switch (giro) {
            case (1): // 0º -> 90º  (orientacion 1 a orientacion 2)
                r[0][0] = this.fila; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna;

                r[2][0] = this.fila + 1;
                r[2][1] = this.columna - 1;

                r[3][0] = this.fila + 1;
                r[3][1] = this.columna;
                break;
            case (2): // 90º -> 180º  (orientacion 2 a orientacion 3)
                r[0][0] = this.fila; // Fila
                r[0][1] = this.columna; // Columna

                r[1][0] = this.fila + 1;
                r[1][1] = this.columna;

                r[2][0] = this.fila + 1;
                r[2][1] = this.columna - 1;

                r[3][0] = this.fila;
                r[3][1] = this.columna - 1;
                break;
            case (3): // 180º -> 270º  (orientacion 3 a orientacion 4)
                r[0][0] = this.fila + 1; // Fila
                r[0][1] = this.columna; // Columna

                r[1][0] = this.fila + 1;
                r[1][1] = this.columna - 1;

                r[2][0] = this.fila;
                r[2][1] = this.columna - 1;

                r[3][0] = this.fila;
                r[3][1] = this.columna;
                break;
            case (4): // 270º -> 0º  (orientacion 4 a orientacion 1)
                r[0][0] = this.fila + 1; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna - 1;

                r[2][0] = this.fila;
                r[2][1] = this.columna;

                r[3][0] = this.fila + 1;
                r[3][1] = this.columna;
                break;
        }
        return r;
    }
}
