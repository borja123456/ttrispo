package com.mygdx.ttrispo.Pantalla;

public class Pieza {
    int centro[];
    int giro = 0;

    public Pieza(int x,int y) {
        this.centro = centro;
    }

    public int [][] bajar(int x, int y){
        int [][] r = new int[4][2];
        for (int i = 0; i < r.length; i++) {
            switch (i){
                case(1):
                    r[i][1] = y + 1;
                    break;
                case(2):
                    r[i][1] = y + 1;
                    break;
                case(3):
                    r[i][1] = y + 1;
                    break;
                case(4):
                    r[i][1] = y + 1;
                    break;
            }
        }
        return r;
    }

}
