package com.mygdx.ttrispo.Botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BotonBase extends ImageButton {
    private final int POSICION_X;
    private final int POSICION_Y;
    private final int ANCHO;
    private final int ALTO;
    private final String IMAGEN_ARRIBA;
    private final String IMAGEN_ABAJO;

    public BotonBase(int posicion_x, int posicion_y, int ancho, int alto, String imagen_arriba, String imagen_abajo, ChangeListener listener) {
        super(new Skin(Gdx.files.internal("skins/default/skin/uiskin.json")));
        POSICION_X = posicion_x;
        POSICION_Y = posicion_y;
        ANCHO = ancho;
        ALTO = alto;
        IMAGEN_ARRIBA = imagen_arriba;
        IMAGEN_ABAJO = imagen_abajo;
        this.addCaptureListener(listener);
    }
}

