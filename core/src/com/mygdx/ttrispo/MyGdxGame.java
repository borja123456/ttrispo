package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {

    public PantallaInicio pantallaInicio;
    public static PantallaGameOver pantallaGameOver;
    public static FirebaseHelper firebaseHelper;
    public Partida partida;

    @Override
    public void create() {
        GestorRecursos.cargarImagenes();
        partida = new Partida(this);
        pantallaInicio = new PantallaInicio(this, partida);
        firebaseHelper=new FirebaseHelper();
        pantallaGameOver = new PantallaGameOver(this, partida);
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
