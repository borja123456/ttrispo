package com.mygdx.ttrispo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.ttrispo.Pantalla.GestorPiezas;
import com.mygdx.ttrispo.Pantalla.PiezaS;

public class Tablero extends Actor {
    public static int tablero[][];
    private Texture img, img2;

    public static final int size = 25; //pieza
    public static int TableroX = size*10;
    public static int TableroY = size*20;
    private float ekis, de;


    public Tablero(float x, float y) {
        this.tablero = new int[TableroX/size][TableroY/size];

        img2 = new Texture("bg_tablero.png");
        //this.setWidth(img.getWidth());
        //this.setHeight(img.getHeight());
        this.ekis=x;
        this.de=y;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        pintarPieza();
        int x,y;setScaleX(50);
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {
                if(this.tablero[i][j] >= 1){
                    x = 60*i;
                    y = Gdx.graphics.getHeight() - 60*j;
                    batch.setColor(1, 1, 1, 1f);
                    batch.draw(img,x+ekis,y+de,0,0,55,55);
                }if(this.tablero[i][j] == 0){
                    x = 60*i;
                    y = Gdx.graphics.getHeight() - 60*j;
                    batch.draw(img2,x+ekis,y+de,0,0,50,50);
                    batch.setColor(1, 1, 1, 0.6f);
                }
            }
        }

    }

    private void pintarPieza() {
        img = PiezaS.getColor(GestorPiezas.aleatorio);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void cambiarBloque(int bloques[][], int tipo) {
        int columnas, filas;
        for (int i = 0; i < bloques.length; i++) {
            columnas = bloques[i][1];
            filas = bloques[i][0];
            // Comprobar si se sale de la pantalla
            if(columnas < 10 && columnas >= 0 && filas >= 0 && filas < 20){
                tablero[bloques[i][1]][bloques[i][0]] = tipo;
            }
        }
    }

   /*
   Este metodo es dios en la tierra.
   Implemento un try catch para cuando se sale de array.
    */

    public boolean isColision(int bloques[][]){
        int columnas, filas;
        try {
            for (int i = 0; i < bloques.length; i++) {
                columnas = bloques[i][1];
                filas = bloques[i][0];
                // Comprobar si se sale de la pantalla
                if (columnas >= 10 || filas >= 20) {
                    return true;
                }
                // Colisiona con otro bloque
                if (tablero[bloques[i][1]][bloques[i][0]] != 0) {
                    return true;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return true;
        }
        return false;
    }

    public boolean comprobarLineaCompleta() {
        int numeroColumnas = 10;
        int filas = 20;
        int valorFila = 0;
        for(int i=0;i<this.tablero.length;i++){
            for(int j=0; j<this.tablero[i].length;j++){
                valorFila += tablero[i][j];
            }

            if(valorFila==numeroColumnas){
                System.out.println("Premio");
                eliminarfila(i);
                //bajarFilaAnterior();
            }

            valorFila =0;
        }
        return false;
    }

    private void eliminarfila(int fila) {
        for(int j=0; j<this.tablero[fila].length;j++){
            tablero[fila][j]=0;
        }
    }

    /*private void bajarFilaAnterior(int fila) {
        //implementando.....
        //TODO
    }*/
}
