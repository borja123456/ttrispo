package com.mygdx.ttrispo.Pieza;

public class PiezaL extends Pieza{

    public PiezaL(int f, int c) {
        super(f, c);
        tipo = L;
        this.texture = getTexture(this.tipo);
    }

    public int [][] getPosicionPieza(){ // ¿Deberia llamarse giroPiezaLDerecha el metodo?
        int [][] r = new int[super.numBlock][2]; // Cada fila del array representa las coordenadas de uno de los bloques que forman la pieza
        switch (giro){
            case(1):
                // 0º -> 90º  (orientacion 1 a orientacion 2)
                r[0][0] = this.fila + 2; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila + 2;
                r[1][1] = this.columna;

                r[2][0] = this.fila + 2;
                r[2][1] = this.columna + 1;

                r[3][0] = this.fila + 1;
                r[3][1] = this.columna + 1;
                //giro = 2; // ¿Actualizar la nueva "orientacion" de la pieza?
                break;
            case(2): // 90º -> 180º  (orientacion 2 a orientacion 3)
                r[0][0] = this.fila; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila + 1;
                r[1][1] = this.columna - 1;

                r[2][0] = this.fila + 2;
                r[2][1] = this.columna - 1;

                r[3][0] = this.fila + 2;
                r[3][1] = this.columna;
               // giro = 3; // ¿Actualizar la nueva "orientacion" de la pieza?
                break;
            case(3): // 180º -> 270º  (orientacion 3 a orientacion 4)
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila - 1;
                r[1][1] = this.columna;

                r[2][0] = this.fila - 1;
                r[2][1] = this.columna + 1;

                r[3][0] = this.fila;
                r[3][1] = this.columna + 1;
               // giro = 4; // ¿Actualizar la nueva "orientacion" de la pieza?
                break;
            case(4): // 270º -> 0º  (orientacion 4 a orientacion 1)
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna; // Columna

                r[1][0] = this.fila - 1;
                r[1][1] = this.columna + 1;

                r[2][0] = this.fila;
                r[2][1] = this.columna;

                r[3][0] = this.fila + 1;
                r[3][1] = this.columna;
               // giro = 1; // ¿Actualizar la nueva "orientacion" de la pieza?
                break;
        }
        return r;
    }
}