package com.mygdx.ttrispo.Pantalla;

public class GestorEstado {
    static final int REPOSO = 0;
    static final int CAER = 1;

    private final Partida partida;
    private int velocity = 2;
    private float contador = 0;

    public GestorEstado(Partida partida) {
        this.partida = partida;
    }

    public int getEstado(float delta){
        int estado = REPOSO;
        if(contador < velocity){
            contador += delta;
            estado = REPOSO;

        }else{
            contador = 0;
            estado = CAER;
        }
        return estado;
    }
}
