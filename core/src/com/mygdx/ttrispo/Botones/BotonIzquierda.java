package com.mygdx.ttrispo.Botones;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.ttrispo.MyGdxGame;

public class BotonIzquierda extends TextButton {
    private int POSICION_X;
    private int POSICION_Y;
    private static int ANCHO=300;
    private static int ALTO=300;
    public BotonIzquierda(String text, Skin skin, MyGdxGame game) {
        super(text, skin);
        POSICION_X;
        this.setPosition(POSICION_X,POSICION_Y);
        this.setSize(ANCHO,ALTO);
        this.addCaptureListener();
    }
}
