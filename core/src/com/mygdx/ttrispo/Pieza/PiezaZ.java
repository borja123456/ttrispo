package com.mygdx.ttrispo.Pieza;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ttrispo.Gestores.GestorRecursos;

public class PiezaZ extends Pieza{

    public PiezaZ(int f, int c) {
        super(f, c);
        tipo = Z;
        this.texture = GestorRecursos.get("Z.jpg");
    }

    public int [][] getPosicionPieza(){
        int [][] r = new int[super.numBlock][2];
        switch (giro){
            case(1):
                r[0][0] = this.fila; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna;

                r[2][0] = this.fila + 1;
                r[2][1] = this.columna;

                r[3][0] = this.fila + 1;
                r[3][1] = this.columna + 1;
                break;
            case(2):
                r[0][0] = this.fila + 1; // Fila
                r[0][1] = this.columna; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna;

                r[2][0] = this.fila;
                r[2][1] = this.columna + 1;

                r[3][0] = this.fila - 1;
                r[3][1] = this.columna + 1;
                break;

            case(3):
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna - 1; // Columna

                r[1][0] = this.fila - 1;
                r[1][1] = this.columna;

                r[2][0] = this.fila;
                r[2][1] = this.columna;

                r[3][0] = this.fila;
                r[3][1] = this.columna + 1;
                break;

            case(4):
                r[0][0] = this.fila - 1; // Fila
                r[0][1] = this.columna; // Columna

                r[1][0] = this.fila;
                r[1][1] = this.columna;

                r[2][0] = this.fila;
                r[2][1] = this.columna - 1;

                r[3][0] = this.fila + 1;
                r[3][1] = this.columna - 1;
                break;
        }
        return r;
    }

}