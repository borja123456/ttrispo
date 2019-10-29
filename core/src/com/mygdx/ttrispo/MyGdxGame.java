package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {

    public PantallaInicio pantallaInicio;
    //public Partida partida;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;


    @Override
    public void create() {
        GestorRecursos.cargarImagenes();
        pantallaInicio = new PantallaInicio(this);
        //partida = new Partida(this);
        pantallaGameOver = new PantallaGameOver(this);
        pantallaAjustes = new PantallaAjustes(this);
        this.setScreen(pantallaAjustes);
    }

    @Override
    public void dispose() {
//        batch.dispose();
//        img.dispose();
    }




}
