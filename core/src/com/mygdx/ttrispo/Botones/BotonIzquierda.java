package com.mygdx.ttrispo.Botones;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BotonIzquierda extends TextButton {
    private static int POSICION_X=45;
    private static int POSICION_Y=68;
    public BotonIzquierda(String text, Skin skin) {
        super(text, skin);
        this.setPosition(POSICION_X,POSICION_Y);
    }
}
