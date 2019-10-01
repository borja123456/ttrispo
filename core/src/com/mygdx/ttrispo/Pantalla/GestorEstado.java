package com.mygdx.ttrispo.Pantalla;

public class GestorEstado {
    public static final int SINPIEZA = 2;
    public static final int REPOSO = 0;
    public static final int CAER = 1;

    private final Partida partida;
    private int velocity = 1;
    private float contador = 0;
    private boolean flagSinFicha = true;

    public GestorEstado(Partida partida) {
        this.partida = partida;
    }

    public int getEstado(float delta) {
        int estado = REPOSO;
        if (flagSinFicha) {
            estado = SINPIEZA;
        } else {
            if (contador < velocity) {
                contador += delta;
                estado = REPOSO;

            } else {
                contador = 0;
                estado = CAER;
            }
        }
        return estado;
    }

    public void setFlagSinFicha(boolean flagSinFicha) {
        this.flagSinFicha = flagSinFicha;
    }
}
