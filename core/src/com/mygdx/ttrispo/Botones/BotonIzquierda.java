package com.mygdx.ttrispo.Botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.ttrispo.Gestores.GestorEstado;
import com.mygdx.ttrispo.MyGdxGame;
import com.mygdx.ttrispo.Pantalla.Partida;

public class BotonIzquierda extends TextButton {
    private final int POSICION_X= Gdx.graphics.getWidth();
    private final int POSICION_Y = Gdx.graphics.getHeight();
    private final static int ANCHO=300;
    private final int ALTO=300;
    public BotonIzquierda(String text, Skin skin, final GestorEstado gestorEstado) {
        super(text, skin);
        this.setPosition(POSICION_X,POSICION_Y);
        this.setSize(ANCHO,ALTO);
        this.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gestorEstado.setEstado(gestorEstado.IZQUIERDA);
            }
        });
    }
}
