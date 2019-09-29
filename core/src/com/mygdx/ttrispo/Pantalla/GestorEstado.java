package com.mygdx.ttrispo.Pantalla;

public class GestorEstado {
    private final Partida partida;
    private int velocity = 2;
    private float contador = 0;

    public GestorEstado(Partida partida) {
        this.partida = partida;
    }

    public int getEstado(float delta){
        int estado = 0;
        if(contador < velocity){
            contador += delta;
            estado = 0;

        }else{
            contador = 0;
            estado = 1;
        }
        return estado;
    }
}
