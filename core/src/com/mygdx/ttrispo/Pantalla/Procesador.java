package com.mygdx.ttrispo.Pantalla;

import com.badlogic.gdx.InputAdapter;

public class Procesador extends InputAdapter {
    GestorEstado gestorEstado;
    public Procesador(GestorEstado g){
        gestorEstado=g;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gestorEstado
        return true;
    }
}
