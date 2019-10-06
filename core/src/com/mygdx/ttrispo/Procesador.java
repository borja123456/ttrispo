package com.mygdx.ttrispo;

import com.badlogic.gdx.InputAdapter;
import com.mygdx.ttrispo.Pantalla.GestorEstado;

public class Procesador extends InputAdapter {
    GestorEstado gestorEstado;
    public Procesador(GestorEstado g){
        gestorEstado=g;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gestorEstado.setEstado(gestorEstado.GIRO);
        return true;
    }

}
