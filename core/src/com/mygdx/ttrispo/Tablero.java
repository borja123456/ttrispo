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
        this.tablero = new int[5][12];
        img = new Texture("badlogic.jpg");
        this.setWidth(img.getWidth());
        this.setHeight(img.getHeight());
        a = new Pieza();
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
                if(this.tablero[i][j] > 0){
                    x = img.getWidth() * i;
                    y =  Gdx.graphics.getHeight()- img.getHeight() - img.getHeight()*j;
                    batch.draw(img,x,y);
                }
            }
        }
        if (as > 500){
        }
        as++;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
}
