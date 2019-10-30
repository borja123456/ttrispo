package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;
import com.mygdx.ttrispo.Pantalla.Partida;

public class MyGdxGame extends Game {

    public PantallaInicio pantallaInicio;

    public static FirebaseHelper firebaseHelper;
    public Partida partida;

    //public Partida partida;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;


    @Override
    public void create() {
        GestorRecursos.cargarImagenes();

        partida = new Partida(this);
        pantallaInicio = new PantallaInicio(this, partida);
        firebaseHelper=new FirebaseHelper();
        pantallaGameOver = new PantallaGameOver(this, partida);
        this.setScreen(pantallaInicio);

        //partida = new Partida(this);
        pantallaAjustes = new PantallaAjustes(this);
        this.setScreen(pantallaAjustes);
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
