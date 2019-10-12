package com.mygdx.ttrispo.Gestores;

//import sun.security.util.DerEncoder;

import com.mygdx.ttrispo.Pantalla.Partida;

public class GestorEstado {
    public static final int SINPIEZA = 2;
    public static final int REPOSO = 0;
    public static final int CAER = 1;
    public static final int DERECHA = 3;
    public static final int IZQUIERDA = 4;
    public static final int GIRO = 5;
    public static final int BLOQUEAR = 6;

    private final Partida partida;
    private float velocity = 0.3f;
    private float contador = 0;
    private int estado = SINPIEZA;

    public GestorEstado(Partida partida) {
        this.partida = partida;
    }

    public int getEstado(float delta) {
        if (contador < velocity) {
            contador += delta;
        } else {
            contador = 0;
            estado = CAER;
        }
        return estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
