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

    public void  cambiarBloque(int bloques[][], int tipo) {
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
    /*
    A REFACTORIZAR.
    El numero de filas y columnas. No consigo obtener correctamente el el numero
     */
    public boolean comprobarLineaCompleta() {
        int numeroColumnas = 10;
        int filas = 20;
        int valorFila = 0;
        for(int i=0;i<20;i++){
            for(int j=0; j<10;j++){
                if(tablero[j][i]!=0){
                    valorFila ++;
                }

            }
            if(valorFila==numeroColumnas){
                System.out.println("Premio");
                eliminarfila(i);
                bajarFilaAnterior(i);
            }

            valorFila =0;
        }
        return false;
    }

    private void eliminarfila(int fila) {
        for(int j=0; j<10;j++){
            tablero[j][fila]=0;
        }
    }

    /*
    se recoge la fila eliminada y desde ahi hasta arriba se copia la fila anterior en la actual.
    Habria que darle una vuelta en el siguiente sprint porque es un poco chapuza.
  */
    /*
    Posible refatorizacion: Si son todpo 0 pues que no baje mas, pero eso ya mas adelante
     */
    private void bajarFilaAnterior(int fila) {
        for(;fila>0;fila--){
            for(int c=0;c<10;c++){
                tablero[c][fila]= tablero[c][fila-1];
            }
        }

    }
}