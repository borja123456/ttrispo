package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {
    public static float ratioPixelesHeight, ratioPixelesWidth;
    private Partida partida;

    public PantallaInicio pantallaInicio;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;

    @Override
    public void create() {
        GestorRecursos.cargarImagenes();
        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();
        pantallaInicio = new PantallaInicio(this);
        //partida = new Partida(this);
        pantallaGameOver = new PantallaGameOver(this);
        pantallaAjustes = new PantallaAjustes(this);
        firebaseHelper=new FirebaseHelper();
        this.setScreen(pantallaInicio);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        super.render();
        partidaTerminada(partida);
    }

    private boolean partidaTerminada(Partida party){
        if(party==null){
            partida = new Partida(this);
            return true;
        }else return false;
    }
}
