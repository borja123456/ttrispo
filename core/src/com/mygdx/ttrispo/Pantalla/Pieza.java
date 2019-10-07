package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Pieza {

    public static final int T = 1;//Hay que cambiar esto de si se quiere probar otra pieza, y la otra pieza debe llevar el 1
    public static final int S = 2;
    public static final int Z = 3;
    public static final int J = 4;
    public static final int L = 5;
    public static final int I = 6;
    public static final int O = 7;

    public static Texture color, imagen;

    public static final int VACIA = 0;

    protected int f,c, numBlock;
    protected int tipo, giro;

    public Pieza(int f,int c) {
        this.f = f;
        this.c = c;
        this.numBlock = 4;
        this.tipo = this.T;
        this.giro = 1;
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

    public static Texture getColor(int tipo) {
        Texture aux = new Texture("T.jpg");
        switch (tipo){
            case 1:
                aux = new Texture("T.jpg");
                break;
            case 2:
                aux = new Texture("S.jpg");
                break;
            case 3:
                aux = new Texture("Z.jpg");
                break;
            case 4:
                aux = new Texture("J.jpg");
                break;
            case 5:
                aux = new Texture("L.jpg");
                break;
            case 6:
                aux = new Texture("I.jpg");
                break;
            case 7:
                aux = new Texture("O.jpg");
                break;
        }

        return aux;
    }



    public static TextureRegion getImagen(int tipo) {
        TextureRegion aux = new TextureRegion(new Texture("SCompleta.png"));

        switch (tipo){
            case 1:
                aux = new TextureRegion(new Texture("TCompleta.png"));
                break;
            case 2:
                aux = new TextureRegion(new Texture("SCompleta.png"));
                break;
            case 3:
                aux = new TextureRegion(new Texture("ZCompleta.png"));
                break;
            case 4:
                aux = new TextureRegion(new Texture("JCompleta.png"));
                break;
            case 5:
                aux = new TextureRegion(new Texture("LCompleta.png"));
                break;
            case 6:
                aux = new TextureRegion(new Texture("ICompleta.png"));
                break;
            case 7:
                aux = new TextureRegion(new Texture("OCompleta.png"));
                break;
        }

        return aux;
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

    public void setGiro(int giro) {
        this.giro += giro;
    }

    public int getGiro() {
        return giro;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setGiroInicial() {
        this.giro=1;
    }
}
