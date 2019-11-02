package com.mygdx.ttrispo;

import com.badlogic.gdx.Game;
import com.mygdx.ttrispo.BaseDeDatos.FirebaseHelper;
import com.badlogic.gdx.Gdx;
import com.mygdx.ttrispo.Gestores.GestorRecursos;
import com.mygdx.ttrispo.Pantalla.PantallaAjustes;
import com.mygdx.ttrispo.Pantalla.PantallaGameOver;
import com.mygdx.ttrispo.Pantalla.PantallaInicio;

public class MyGdxGame extends Game {
    public static float ratioPixelesHeight, ratioPixelesWidth;

    public PantallaInicio pantallaInicio;
    public PantallaGameOver pantallaGameOver;
    public PantallaAjustes pantallaAjustes;
    public static FirebaseHelper firebaseHelper;

    @Override
    public void create() {
        GestorRecursos.cargarImagenes();
        ratioPixelesHeight = (float) Gdx.graphics.getHeight()/GestorRecursos.get("background.jpeg").getHeight();
        ratioPixelesWidth = (float) Gdx.graphics.getWidth()/GestorRecursos.get("background.jpeg").getWidth();    //pixeles = pantallaMovil/background

        pantallaInicio = new PantallaInicio(this);
        pantallaAjustes = new PantallaAjustes(this);
        pantallaGameOver = new PantallaGameOver(this);
        firebaseHelper=new FirebaseHelper();
        this.setScreen(pantallaInicio);
    }

    @Override
    public void dispose() {
        GestorRecursos.limpiarAssets();
    }

    @Override
    public void render() {
        super.render();
    }
}
