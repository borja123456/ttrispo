package com.mygdx.ttrispo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.ttrispo.Pantalla.Pieza;

public class Tablero extends Actor {
    private int tablero[][];
    private Texture img;
    private Pieza a;
    private int as = 0, asd[];

    public Tablero() {
        this.tablero = new int[10][20];
        img = new Texture("badlogic.jpg");
        this.setWidth(img.getWidth());
        this.setHeight(img.getHeight());
        a = new Pieza(4,0);
        asd = new int[2];
        this.asd[0] = 2;
        this.asd[1] = 0;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        int x,y;
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {
                if(this.tablero[i][j] == 1){
                    x = 128 * i;
                    y =  Gdx.graphics.getHeight()- 128 - 123*j;
                    batch.draw(img,x,y,0,0,128,128);
                }
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    public void insertarPieza(int bloques[][], int tipo) {
        for (int i = 0; i < bloques.length; i++) {
            tablero[bloques[i][1]][bloques[i][0]] = tipo;
        }
    }
}
