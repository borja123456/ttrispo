package com.mygdx.ttrispo.Botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.ttrispo.Gestores.GestorEstado;

public class BotonAbajo extends TextButton {
    private static int POSICION_X= Gdx.graphics.getWidth()/6;
    private int POSICION_Y = Gdx.graphics.getHeight()/4;
    private static int ANCHO=300;
    private static int ALTO=300;
    public BotonAbajo(String text, Skin skin, final GestorEstado gestorEstado) {
        super(text, skin);
        this.setPosition(POSICION_X,POSICION_Y);
        this.setSize(ANCHO,ALTO);
        this.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestorEstado.setEstado(gestorEstado.CAER);
            }
        });
    }
}
