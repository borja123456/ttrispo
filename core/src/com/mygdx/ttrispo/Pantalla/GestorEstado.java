package com.mygdx.ttrispo.Pantalla;

import sun.security.util.DerEncoder;

public class GestorEstado {
    public static final int SINPIEZA = 2;
    public static final int REPOSO = 0;
    public static final int CAER = 1;
    public static final int DERECHA = 3;
    public static final int IZQUIERDA = 4;

    private final Partida partida;
    private float velocity = 0.05f;
    private float contador = 0;
    private boolean flagSinFicha = true;
    private int estado = REPOSO;

    public GestorEstado(Partida partida) {
        this.partida = partida;
    }

    public int getEstado(float delta) {

        if (flagSinFicha) {
            estado = SINPIEZA;
        } else {
            if (estado == IZQUIERDA){
                contador += delta;
            }
            else if (estado == DERECHA){
                contador += delta;
            }
            else if (contador < velocity) {
                contador += delta;
                estado = REPOSO;

            }
            else {
                contador = 0;
                estado = CAER;
            }
        }
        return estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFlagSinFicha(boolean flagSinFicha) {
        this.flagSinFicha = flagSinFicha;
    }
}
