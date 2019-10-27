package com.mygdx.ttrispo.Botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.ttrispo.MyGdxGame;

public class BotonIzquierda extends TextButton {
    private static int POSICION_X= Gdx.graphics.getWidth();
    private int POSICION_Y = Gdx.graphics.getHeight();
    private static int ANCHO=300;
    private static int ALTO=300;
    public BotonIzquierda(String text, Skin skin, MyGdxGame game) {
        super(text, skin);
        this.setPosition(POSICION_X,POSICION_Y);
        this.setSize(ANCHO,ALTO);
        this.addCaptureListener();
    }
}
